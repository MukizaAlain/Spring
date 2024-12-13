package com.sandra.car_rentals.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sandra.car_rentals.Model.Employees;
import com.sandra.car_rentals.Repository.EmployeesRepository;
import com.sandra.car_rentals.response.MessageResponse;

@Service
public class EmployeesServices {

    @Autowired
    private EmployeesRepository employeesRepository;

    public MessageResponse saveEmployee(Employees employee) {
        // Check if an employee with the same email already exists
        if (employeesRepository.existsByEmail(employee.getEmail())) {
            return new MessageResponse("Email is already taken");
        }

        // Check if an employee with the same name and email already exists
        if (!employeesRepository.existsByNameAndEmail(employee.getName(), employee.getEmail())) {
            employeesRepository.save(employee);
            return new MessageResponse("Employee saved successfully");
        } else {
            return new MessageResponse("Employee already exists");
        }
    }

    public List<Employees> getAllEmployees() {
        return employeesRepository.findAll();
    }

    public MessageResponse updateEmployee(Employees employee) {
        // Check if the employee exists based on name and email
        if (employeesRepository.existsByNameAndEmail(employee.getName(), employee.getEmail())) {
            // Find the existing employee based on name and email
            Employees existingEmployee = employeesRepository.findByNameAndEmail(
                    employee.getName(), employee.getEmail());

            // Update the existing employee with the new values
            existingEmployee.setPhoneNumber(employee.getPhoneNumber());
            existingEmployee.setRole(employee.getRole());
            // Update other fields as needed

            // Save the updated employee
            employeesRepository.save(existingEmployee);
            return new MessageResponse("Employee updated successfully");
        } else {
            return new MessageResponse("Employee not found");
        }
    }

    public MessageResponse deleteEmployee(String name, String email) {
        // Find the employee based on name and email
        Employees employee = employeesRepository.findByNameAndEmail(name, email);

        if (employee != null) {
            // Delete the employee
            employeesRepository.delete(employee);
            return new MessageResponse("Employee deleted successfully");
        } else {
            return new MessageResponse("Employee not found");
        }
    }

}
