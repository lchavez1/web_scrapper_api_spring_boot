package com.luis.web_scrapper_spring_boot_react.controller;

import com.luis.web_scrapper_spring_boot_react.model.Offer;
import com.luis.web_scrapper_spring_boot_react.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping("/offers")
    public List<Offer> getAllOffers() {
        return offerService.getAllOfers();
    }
}
