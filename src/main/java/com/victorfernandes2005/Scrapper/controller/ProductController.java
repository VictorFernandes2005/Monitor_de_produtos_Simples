package com.victorfernandes2005.Scrapper.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.victorfernandes2005.Scrapper.model.ProductModel;
import com.victorfernandes2005.Scrapper.service.ProductService;
import com.victorfernandes2005.Scrapper.service.ProductServiceFactory;


@Controller
public class ProductController {

    private ProductService service;
    
    public ProductController(){this.service = ProductServiceFactory.getService("magazine");}

    @GetMapping("products/")
    public String viewProductsGet(Model model){
        return "products/get_products";
    }

    @GetMapping("products/add")
    public String addPageGet(Model model){
        ProductModel product = new ProductModel();
        model.addAttribute("product",product);
        return "products/add_new_product";
    }

    @PostMapping("products/add")
    public String addPagePost(@RequestBody ProductModel product, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("product",product);
            model.addAttribute("error",result.getAllErrors());
            return "products/add_new_product";
        }
        return "products/get_products";
    }
}
