package com.sandra.car_rentals.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sandra.car_rentals.Model.Employees;
import com.sandra.car_rentals.Services.EmployeesServices;
import com.sandra.car_rentals.response.MessageResponse;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/employees")
public class EmployeesController {

    @Autowired
    private EmployeesServices employeesService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> saveEmployee(@RequestBody Employees employee) {
        MessageResponse messageResponse = employeesService.saveEmployee(employee);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employees>> getAllEmployees() {
        List<Employees> employeesList = employeesService.getAllEmployees();
        return ResponseEntity.ok(employeesList);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateEmployee(@RequestBody Employees employee) {
        MessageResponse messageResponse = employeesService.updateEmployee(employee);
        return ResponseEntity.ok(messageResponse);
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deleteEmployee(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String email = request.get("email");

        MessageResponse messageResponse = employeesService.deleteEmployee(name, email);
        return ResponseEntity.ok(messageResponse);
    }

}
