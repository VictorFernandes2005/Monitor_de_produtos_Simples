package com.victorfernandes2005.Scrapper.repository;

import org.springframework.data.repository.CrudRepository;

import com.victorfernandes2005.Scrapper.model.ProductModel;


public interface ProductRepository extends CrudRepository<ProductModel,Long>{

}
