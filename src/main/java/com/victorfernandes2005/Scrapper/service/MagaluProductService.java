package com.victorfernandes2005.Scrapper.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

public class MagaluProductService extends ProductService{

    public MagaluProductService(WebDriver driver){super(driver);}

    @Override
    protected HashMap<String,String> getDriverConfig(){
        HashMap<String,String> hm = new HashMap<>();
        hm.put("name", "[data-testid='heading']");
        hm.put("price", "[data-testid='price-value-integer']");
        hm.put("priceDecimal", "[data-testid='price-value-split-cents-fraction']");
        hm.put("imgPath", "[data-testid='image']");

        return hm;
    }

    @Override
    protected Double convertPriceWebElement(WebElement price, WebElement decimal){
        Double integerPrice = Double.valueOf(price.getText());
        Double decimalPrice = Double.valueOf(decimal.getText())/100;
        return integerPrice + decimalPrice;
    }
}
