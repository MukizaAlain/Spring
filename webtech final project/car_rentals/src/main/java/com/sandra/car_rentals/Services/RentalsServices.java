package com.sandra.car_rentals.Services;

import com.sandra.car_rentals.Model.Rentals;
import com.sandra.car_rentals.Repository.RentalsRepository;
import com.sandra.car_rentals.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RentalsServices {
    @Autowired
    private RentalsRepository rentalsRepository;

    public MessageResponse saveRental(Rentals rental) {
        // Check if rental details are valid (you can add more validation logic as
        // needed)

        // Save the rental
        rentalsRepository.save(rental);
        return new MessageResponse("Rental saved successfully");
    }

    public List<Rentals> getAllRentals() {
        return rentalsRepository.findAll();
    }

    public MessageResponse updateRental(UUID rentalId, Rentals updatedRental) {
        // Check if the rental exists
        if (rentalsRepository.existsById(rentalId)) {
            // Find the existing rental
            Rentals existingRental = rentalsRepository.findById(rentalId).get();

            // Update the existing rental with the new values
            existingRental.setCar(updatedRental.getCar());
            existingRental.setName(updatedRental.getName());
            existingRental.setBranch(updatedRental.getBranch());
            existingRental.setRentalStartDate(updatedRental.getRentalStartDate());
            existingRental.setRentalEndDate(updatedRental.getRentalEndDate());

            // Save the updated rental
            rentalsRepository.save(existingRental);
            return new MessageResponse("Rental updated successfully");
        } else {
            return new MessageResponse("Rental not found");
        }
    }

    public MessageResponse deleteRental(UUID rentalId) {
        // Check if the rental exists
        if (rentalsRepository.existsById(rentalId)) {
            // Delete the rental
            rentalsRepository.deleteById(rentalId);
            return new MessageResponse("Rental deleted successfully");
        } else {
            return new MessageResponse("Rental not found");
        }
    }

}
