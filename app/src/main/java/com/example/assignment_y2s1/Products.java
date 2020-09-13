package com.example.assignment_y2s1;

public class Products {

    String name;
    String imageUrl;
    String price;
    String quantity;
    private  String prodID;
    private String NameProd;
    private String PriceProd;
    private int Quantity;
    private String ImageProd;
    public Products() {
    }

    public Products(String name, String imageUrl, String price, String quantity, String prodID, String nameProd, String priceProd, int quantity1, String imageProd) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.prodID = prodID;
        NameProd = nameProd;
        PriceProd = priceProd;
        Quantity = quantity1;
        ImageProd = imageProd;
    }

    //    public Products(String name, String imageUrl, String price, String prodID, String nameProd, float priceProd, int quantity, String imageProd) {
//        this.name = name;
//        this.quantity = quantity;
//        this.imageUrl = imageUrl;
//        this.price = price;
//        this.prodID = prodID;
//        NameProd = nameProd;
//        PriceProd = priceProd;
//        Quantity = quantity;
//        ImageProd = imageProd;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getImageProd() {
        return ImageProd;
    }

    public void setImageProd(String imageProd) {
        ImageProd = imageProd;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProdID() {
        return prodID;
    }

    public void setProdID(String prodID) {
        this.prodID = prodID;
    }

    public String getNameProd() {
        return NameProd;
    }

    public void setNameProd(String nameProd) {
        NameProd = nameProd;
    }

    public String getPriceProd() {
        return PriceProd;
    }

    public void setPriceProd(String priceProd) {
        PriceProd = priceProd;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public String getProdID() {
//        return prodID;
//    }
//
//    public void setProdID(String prodID) {
//        this.prodID = prodID;
//    }
//
//    public String getNameProd() {
//        return NameProd;
//    }
//
//    public void setNameProd(String nameProd) {
//        NameProd = nameProd;
//    }
//
//    public float getPriceProd() {
//        return PriceProd;
//    }
//
//    public void setPriceProd(float priceProd) {
//        PriceProd = priceProd;
//    }
//
//    public int getQuantity() {
//        return Quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        Quantity = quantity;
//    }
//
//    public String getImageProd() {
//        return ImageProd;
//    }
//
//    public void setImageProd(String imageProd) {
//        ImageProd = imageProd;
//    }
}
