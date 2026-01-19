package com.victorfernandes2005.Scrapper.service;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.victorfernandes2005.Scrapper.model.ProductModel;

public abstract class ProductService {

    WebDriver driver;

    public ProductService(WebDriver driver){ this.driver = driver;}

    /**
     * Retorna as chaves necessárias para que o Selenium possa acessar
     * os elementos HTML corretamente. Depende do site que ela estiver acessando.
     * @param url URL do produto na loja online
     * @return driverConfig
    */
    private HashMap<String,String> getDriverConfig(String url){return null;}

    /**
     * Retorna o valor de price, corretamente tratado e convertido para Double.
     * A forma de tratamento depende do site que ela estiver acessando.
     * @param el WebElement do price.
     * @return price
    */
    private Double convertPriceWebElement(WebElement el){
        return 0.0;
    }

    /**
     * Cria e retorna um produto a partir da URL inserida.
     * @param url
     * @return product
    */
    public ProductModel makeProduct(String url){
        ProductModel p = new ProductModel();

        HashMap<String,String> keys = getDriverConfig(url);
        driver.get(url);

        String pName = driver.findElement(By.cssSelector(keys.get("name"))).getText();
        Double pPrice = convertPriceWebElement(driver.findElement(By.cssSelector(keys.get("price"))));
        String pImgPath = driver.findElement(By.cssSelector(keys.get("imgPath"))).getAttribute("url");
        

        p.setName(pName);
        p.setPrice(pPrice);
        p.setImgPath(pImgPath);

        return p;
    }
}
