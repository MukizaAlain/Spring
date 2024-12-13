package com.sandra.car_rentals.Controller;

import com.sandra.car_rentals.Model.Rentals;
import com.sandra.car_rentals.Services.RentalsServices;
import com.sandra.car_rentals.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rentals")
public class RentalsController {

    @Autowired
    private RentalsServices rentalsService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> saveRental(@RequestBody Rentals rental) {
        MessageResponse response = rentalsService.saveRental(rental);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Rentals>> getAllRentals() {
        List<Rentals> rentals = rentalsService.getAllRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @PutMapping("/{rentalId}")
    public ResponseEntity<MessageResponse> updateRental(@PathVariable UUID rentalId,
            @RequestBody Rentals updatedRental) {
        MessageResponse response = rentalsService.updateRental(rentalId, updatedRental);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{rentalId}")
    public ResponseEntity<MessageResponse> deleteRental(@PathVariable UUID rentalId) {
        MessageResponse response = rentalsService.deleteRental(rentalId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
