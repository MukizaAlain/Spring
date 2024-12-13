package com.sandra.car_rentals.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandra.car_rentals.Model.Customers;
import com.sandra.car_rentals.Repository.CustomersRepository;
import com.sandra.car_rentals.response.MessageResponse;

@Service
public class CustomersServices {

    @Autowired
    private CustomersRepository customersRepository;

    public MessageResponse saveCustomer(Customers customer) {


        if (customersRepository.existsByEmail(customer.getEmail())) {
            return new MessageResponse("Email is already taken");
        }
    
        // Check if a customer with the same name, email, and address already exists
        if (!customersRepository.existsByNameAndEmail(customer.getName(), customer.getEmail())) {
            customersRepository.save(customer);
            return new MessageResponse("Customer saved successfully");
        } else {
            return new MessageResponse("Customer already exists");
        }
    }

    public List<Customers> getAllCustomers() {
        return customersRepository.findAll();
    }

    public MessageResponse updateCustomer(Customers customer) {
        // Check if the customer exists based on name, email, and address
        if (customersRepository.existsByNameAndEmail(customer.getName(), customer.getEmail())) {
            // Find the existing customer based on name, email
            Customers existingCustomer = customersRepository.findByNameAndEmail(
                    customer.getName(), customer.getEmail());

            // Update the existing customer with the new values
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            existingCustomer.setAddress(customer.getAddress());

            // Save the updated customer
            customersRepository.save(existingCustomer);
            return new MessageResponse("Customer updated successfully");
        } else {
            return new MessageResponse("Customer not found");
        }
    }

    public MessageResponse deleteCustomer(String name, String email) {
        // Find the customer based on name, email
        Customers customer = customersRepository.findByNameAndEmail(name, email);

        if (customer != null) {
            // Delete the customer
            customersRepository.delete(customer);
            return new MessageResponse("Customer deleted successfully");
        } else {
            return new MessageResponse("Customer not found");
        }
    }

}
