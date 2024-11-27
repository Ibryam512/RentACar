package com.example.rentacar.controllers;

import com.example.rentacar.entities.Offer;
import com.example.rentacar.requests.AcceptOfferRequest;
import com.example.rentacar.requests.CreateOfferRequest;
import com.example.rentacar.responses.Response;
import com.example.rentacar.services.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/offers")
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    // Get all offers
    @GetMapping
    public ResponseEntity<Response<List<Offer>>> getAllOffers() {
        var offers = offerService.getOffers();

        return new ResponseEntity<>(offers, offers.statusCode());
    }

    // Get offer by ID
    @GetMapping("{id}")
    public ResponseEntity<Response<Offer>> getOfferById(@PathVariable int id) {
        var offer = offerService.getOfferById(id);

        return new ResponseEntity<>(offer, offer.statusCode());
    }

    // Create a new offer
    @PostMapping
    public ResponseEntity<Response<Offer>> createOffer(@RequestBody CreateOfferRequest request) {
        var offer = offerService.createOffer(request);

        return new ResponseEntity<>(offer, offer.statusCode());
    }

    // Accept or reject an offer
    @PutMapping("accept")
    public ResponseEntity<Response<Offer>> acceptOffer(@RequestBody AcceptOfferRequest request) {
        var offer = offerService.acceptOffer(request);

        return new ResponseEntity<>(offer, offer.statusCode());
    }

    // Delete an offer
    @DeleteMapping("{id}")
    public ResponseEntity<Response<Boolean>> deleteOffer(@PathVariable int id) {
        var deleted = offerService.deleteOffer(id);

        return new ResponseEntity<>(deleted, deleted.statusCode());
    }
}
