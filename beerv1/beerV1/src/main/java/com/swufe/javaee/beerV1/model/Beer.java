package com.swufe.javaee.beerV1.model;

public class Beer {
    private String brand;
    private int year_of_birth;
    private String country;

    public Beer() {

    }

    public Beer(String a, int b, String c) {
        brand = a;
        year_of_birth = b;
        country = c;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void showInfo() {
        System.out.printf("Brand:%s\nYear of birth:%d\nCountry:%s", brand, year_of_birth, country);
    }

    public String toString(){
        return "Brand:"+brand+"Year of birth:"+year_of_birth+"Country:"+country;
    }
    public String showbrand(){
        return brand;
    }
    public int showyear(){
        return year_of_birth;
    }
    public String showcountry(){
        return country;
    }
}
