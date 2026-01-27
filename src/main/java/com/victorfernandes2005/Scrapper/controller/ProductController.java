package com.victorfernandes2005.Scrapper.controller;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.victorfernandes2005.Scrapper.model.ProductModel;
import com.victorfernandes2005.Scrapper.service.ProductService;
import com.victorfernandes2005.Scrapper.service.ProductServiceFactory;

import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class ProductController {

    private ProductServiceFactory sf;
    private ProductService service;
    
    public ProductController(ProductServiceFactory sf){
        this.sf = sf;
        this.service = sf.getService("magazine");
    }

    private WebDriver getWebDriver(String web){
        web = web.toLowerCase();
        if(web.contains("firefox")){return new FirefoxDriver();}
        if(web.contains("chrome")){return new ChromeDriver();}
        return new FirefoxDriver();
    }

    @GetMapping("products")
    public String viewProductsGet(Model model){
        Iterable<ProductModel> products = service.findAll();
        model.addAttribute("products",products);
        return "products/get_products";
    }

    @GetMapping("products/add")
    public String addPageGet(Model model){
        ProductModel product = new ProductModel();
        model.addAttribute("product",product);
        return "products/add_new_product";
    }

    @PostMapping("products/makeProductAction")
    public String makeProductAction(Model model, HttpServletRequest request){
        String url = request.getParameter("productURL");
        if(url.isBlank()){
            model.addAttribute("error", "Valor Url em branco!");
        }
        else{
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            String browser = userAgent.getBrowser().getName();

            ProductModel newProduct = service.makeProduct(new FirefoxDriver(), url);
            model.addAttribute("newProduct", newProduct);
            
        }
        return "products/add_new_product";

    }

    @PostMapping("products/saveProductAction")
    public String addPagePost(@ModelAttribute("newProduct") ProductModel newProduct){
        service.save(newProduct);
        return "redirect:/products";
    }
}
