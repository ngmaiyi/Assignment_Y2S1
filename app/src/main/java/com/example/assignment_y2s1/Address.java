package com.example.assignment_y2s1;

public class Address {
    private String address, postcode, city;

    public Address() {
    }

    public Address(String address, String postcode, String city) {
        this.address = address;
        this.postcode = postcode;
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
