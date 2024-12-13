package com.sandra.car_rentals.Controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.sandra.car_rentals.Model.Cars;
import com.sandra.car_rentals.Services.CarsServices;
import com.sandra.car_rentals.response.MessageResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/cars")
public class CarsController {
    @Autowired
    private CarsServices carsService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> saveCar(@RequestBody Cars car) {
        MessageResponse messageResponse = carsService.saveCars(car);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cars>> getAllCars() {
        List<Cars> carsList = carsService.getAllCars();
        return ResponseEntity.ok(carsList);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateCar(@RequestBody Cars car) {
        MessageResponse messageResponse = carsService.updateCars(car);
        return ResponseEntity.ok(messageResponse);
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deleteCar(@RequestBody Map<String, String> request) {
        // Parse the UUID from the request body
        UUID carId = UUID.fromString(request.get("carId"));

        // Call the service to delete the car and get the response
        MessageResponse messageResponse = carsService.deleteCars(carId);

        // Return the response entity
        return ResponseEntity.ok(messageResponse);
    }
}
