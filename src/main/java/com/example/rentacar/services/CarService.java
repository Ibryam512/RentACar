package com.example.rentacar.services;

import com.example.rentacar.entities.Car;
import com.example.rentacar.requests.CreateCarRequest;
import com.example.rentacar.requests.CreateOfferRequest;
import com.example.rentacar.requests.UpdateCarRequest;
import com.example.rentacar.responses.Response;

import java.util.List;

public interface CarService {
    Response<List<Car>> getCars();
    Response<List<Car>> getCarsByLocation(String location);
    Response<Car> getCarById(int id);
    Response<Car> createCar(CreateCarRequest request);
    Response<Car> updateCar(UpdateCarRequest request);
    Response<Boolean> deleteCarById(int id);
}
