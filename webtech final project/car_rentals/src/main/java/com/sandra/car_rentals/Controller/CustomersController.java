package com.sandra.car_rentals.Controller;

import java.util.List;
import java.util.Map;

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

import com.sandra.car_rentals.Model.Customers;
import com.sandra.car_rentals.Services.CustomersServices;
import com.sandra.car_rentals.response.MessageResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/customers")
public class CustomersController {

    @Autowired
    private CustomersServices customersService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> saveCustomer(@RequestBody Customers customer) {
        MessageResponse messageResponse = customersService.saveCustomer(customer);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customers>> getAllCustomers() {
        List<Customers> customersList = customersService.getAllCustomers();
        return ResponseEntity.ok(customersList);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateCustomer(@RequestBody Customers customer) {
        MessageResponse messageResponse = customersService.updateCustomer(customer);
        return ResponseEntity.ok(messageResponse);
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deleteCustomer(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String email = request.get("email");

        MessageResponse messageResponse = customersService.deleteCustomer(name, email);
        return ResponseEntity.ok(messageResponse);
    }

}
