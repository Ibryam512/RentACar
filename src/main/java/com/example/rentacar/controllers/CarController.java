package com.example.rentacar.controllers;

import com.example.rentacar.entities.Car;
import com.example.rentacar.requests.CreateCarRequest;
import com.example.rentacar.requests.UpdateCarRequest;
import com.example.rentacar.responses.Response;
import com.example.rentacar.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Car>>> getAllCars() {
        var cars = carService.getCars();

        return new ResponseEntity<>(cars, cars.statusCode());
    }

    @GetMapping("search")
    public ResponseEntity<Response<List<Car>>> getCarsByLocation(@RequestParam String location) {
        var cars = carService.getCarsByLocation(location);

        return new ResponseEntity<>(cars, cars.statusCode());
    }

    @GetMapping("{id}")
    public ResponseEntity<Response<Car>> getCarById(@PathVariable int id) {
        var car = carService.getCarById(id);

        return new ResponseEntity<>(car, car.statusCode());
    }

    @PostMapping
    public ResponseEntity<Response<Car>> createCar(@RequestBody CreateCarRequest request) {
        var car = carService.createCar(request);

        return new ResponseEntity<>(car, car.statusCode());
    }

    @PutMapping
    public ResponseEntity<Response<Car>> updateCar(@RequestBody UpdateCarRequest request) {
        var car = carService.updateCar(request);

        return new ResponseEntity<>(car, car.statusCode());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response<Boolean>> deleteCar(@PathVariable int id) {
        var deleted = carService.deleteCarById(id);

        return new ResponseEntity<>(deleted, deleted.statusCode());
    }
}
