package com.luis.web_scrapper_spring_boot_react.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Offer {

    private String title;
    private String price;
    private String url;
    private String image;

    public Offer(String title, String price, String url, String image) {
        this.title = title;
        this.price = price;
        this.url = url;
        this.image = image;
    }


}
