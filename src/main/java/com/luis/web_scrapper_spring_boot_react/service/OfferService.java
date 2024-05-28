package com.luis.web_scrapper_spring_boot_react.service;

import com.luis.web_scrapper_spring_boot_react.model.Offer;
import org.jsoup.Jsoup;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService implements IOfferService{

    @Override
    public List<Offer> getAllOfers() {
        List<Offer> offers = new ArrayList<>();
        getHTML("https://www.mercadolibre.com.mx/ofertas#nav-header").forEach(
                offer -> offers.add(new Offer(
                        offer.select("p.promotion-item__title").toString().split(">")[1].split("<")[0],
                        offer.select("span.andes-money-amount__fraction").toString().split(">")[1].split("<")[0],
                        offer.select("a.promotion-item__link-container").toString().split("href=")[1].split("\"")[1],
                        offer.select("a.promotion-item__link-container").toString().split("href=")[1].split("\"")[1]
                ))
        );
        return offers;
    }

    public Elements getHTML(String url) {
        Elements offers = null;
        try {
            offers = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get().select("li.promotion-item");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return offers;
    }
}
