package com.example.rentacar.controllers;

import com.example.rentacar.entities.Car;
import com.example.rentacar.requests.CreateCarRequest;
import com.example.rentacar.requests.UpdateCarRequest;
import com.example.rentacar.responses.Response;
import com.example.rentacar.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public Response<List<Car>> getAllCars() {
        return carService.getCars();
    }

    @GetMapping("search")
    public Response<List<Car>> getCarsByLocation(@RequestParam String location) {
        return carService.getCarsByLocation(location);
    }

    @GetMapping("{id}")
    public Response<Car> getCarById(@PathVariable int id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public Response<Car> createCar(@RequestBody CreateCarRequest request) {
        return carService.createCar(request);
    }

    @PutMapping
    public Response<Car> updateCar(@RequestBody UpdateCarRequest request) {
        return carService.updateCar(request);
    }

    @DeleteMapping("{id}")
    public Response<Boolean> deleteCar(@PathVariable int id) {
        return carService.deleteCarById(id);
    }
}
