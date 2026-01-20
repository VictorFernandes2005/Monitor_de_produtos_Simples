package com.victorfernandes2005.Scrapper.service;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorfernandes2005.Scrapper.model.ProductModel;
import com.victorfernandes2005.Scrapper.repository.ProductRepository;

@Service
public abstract class ProductService {

    private WebDriver driver;

    @Autowired
    protected ProductRepository repository;

    public ProductService(WebDriver driver){ this.driver = driver;}
    

    /**
     * Retorna as chaves necessárias para que o Selenium possa acessar
     * os elementos HTML corretamente. Depende do site que ela estiver acessando.
     * @return driverConfig
    */
    protected HashMap<String,String> getDriverConfig(){return null;}

    /**
     * Retorna o valor de price, corretamente tratado e convertido para Double.
     * A forma de tratamento depende do site que ela estiver acessando.
     * @param el WebElement do price.
     * @return price
    */
    protected Double convertPriceWebElement(WebElement price, WebElement decimal){
        return 0.0;
    }

    /**
     * Cria e retorna um produto a partir da URL inserida.
     * @param url
     * @return product
    */
    public ProductModel makeProduct(String url){
        ProductModel p = new ProductModel();

        HashMap<String,String> keys = getDriverConfig();
        driver.get(url);

        String pName = driver.findElement(By.cssSelector(keys.get("name"))).getText();
        String pImgPath = driver.findElement(By.cssSelector(keys.get("imgPath"))).getAttribute("src");

        WebElement pPrice = driver.findElement(By.cssSelector(keys.get("price")));
        WebElement pPriceDecimal = driver.findElement(By.cssSelector(keys.get("priceDecimal")));
        Double doublePrice = convertPriceWebElement(pPrice, pPriceDecimal);

        p.setName(pName);
        p.setPrice(doublePrice);
        p.setImgPath(pImgPath);

        driver.quit();

        return p;
    }
}
