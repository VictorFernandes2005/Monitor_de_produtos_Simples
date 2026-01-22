package com.victorfernandes2005.Scrapper.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.victorfernandes2005.Scrapper.repository.ProductRepository;

@Service
public class ProductServiceFactory {

    private static ProductRepository repository;

    public ProductServiceFactory(ProductRepository repository){ProductServiceFactory.repository = repository;}

    /**
     * 
    */
    public static ProductService getService(String service){
        service = service.toLowerCase();
        switch(service){
            case "magazine":
                return new MagaluProductService(repository);
            default:
                return new MagaluProductService(repository);
                
        }
    }

}
