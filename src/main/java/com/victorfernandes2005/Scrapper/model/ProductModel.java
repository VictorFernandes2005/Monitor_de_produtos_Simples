package com.victorfernandes2005.Scrapper.model;



public class ProductModel {

    private Long id;
    private Double price;
    private String name;
    private String imgPath;

    public ProductModel(){}

    public ProductModel(Long id, Double price, String name, String imgPath){
        this.id = id;
        this.price = price;
        this.name = name;
        this.imgPath = imgPath;
    }

    public Long getId(){return id;}
    public Double getPrice(){return price;}
    public String getName(){return name;}
    public String getImgPath(){return imgPath;}

    public void setId(Long id){this.id = id;}
    public void setPrice(Double price){this.price = price;}
    public void setName(String name){this.name = name;}
    public void setImgPath(String imgPath){this.imgPath = imgPath;}

}
