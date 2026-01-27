package com.victorfernandes2005.Scrapper.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.victorfernandes2005.Scrapper.repository.ProductRepository;

@Service
public class ProductServiceFactory {

    private ProductRepository repository;

    public ProductServiceFactory(ProductRepository repository){this.repository = repository;}

    /**
     * Retorna uma nova instacia de um objeto derivado do ProductService, dependendo do serviço especificado.
     * @param service
     * @return newService
    */
    public ProductService getService(String service){
        service = service.toLowerCase();
        switch(service){
            case "magazine":
                return new MagaluProductService(repository);
            default:
                return new MagaluProductService(repository);
                
        }
    }

}
