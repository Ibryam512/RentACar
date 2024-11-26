package com.example.rentacar.services;

import com.example.rentacar.entities.Offer;
import com.example.rentacar.requests.AcceptOfferRequest;
import com.example.rentacar.requests.CreateOfferRequest;
import com.example.rentacar.responses.Response;

import java.util.List;

public interface OfferService {
    Response<List<Offer>> getOffers();
    Response<Offer> getOfferById(int id);
    Response<Offer> createOffer(CreateOfferRequest request);
    Response<Offer> acceptOffer(AcceptOfferRequest request);
    Response<Boolean> deleteOffer(int id);
}
