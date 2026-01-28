package com.victorfernandes2005.Scrapper.service;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.stereotype.Service;
import com.victorfernandes2005.Scrapper.model.ProductModel;
import com.victorfernandes2005.Scrapper.repository.ProductRepository;

@Service
public abstract class ProductService {

    private ProductRepository repository;


    public ProductService(ProductRepository repository){ this.repository = repository;}

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

        FirefoxOptions ffop = new FirefoxOptions();
        ffop.addArguments("--headlless");
        ffop.setPageLoadStrategy(PageLoadStrategy.EAGER);
        
        FirefoxDriver driver = new FirefoxDriver(ffop);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            

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

    /**
     * Retorna um Iterable com todos os ProductModel encontrados no banco de dados.
     * @return products 
    */
    public Iterable<ProductModel> findAll(){
        return repository.findAll();
    }

    /**
     * Salva o ProductModel no banco de dados e em seguida o retorna.
     * @param product
     * @return savedProduct
    */
    public ProductModel save(ProductModel product){
        return repository.save(product);
    }
    
}
