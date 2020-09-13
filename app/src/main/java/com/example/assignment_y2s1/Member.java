package com.example.assignment_y2s1;

public class Member {
    private  String prodID;
    private String NameProd;
    private int PriceProd;
    private int Quantity;
    private String ImageProd;
    private String Comment;
    public Member(){}

    public Member(String prodID, String nameProd, int priceProd, int quantity, String imageProd, String comment) {
        this.prodID = prodID;
        NameProd = nameProd;
        PriceProd = priceProd;
        Quantity = quantity;
        ImageProd = imageProd;
        Comment = comment;
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

    public int getPriceProd() {
        return PriceProd;
    }

    public void setPriceProd(int priceProd) {
        PriceProd = priceProd;
    }

    public int getQuantity() {
        return Quantity;
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

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
