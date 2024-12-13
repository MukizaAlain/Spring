package com.sandra.car_rentals.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sandra.car_rentals.Model.AuthenticationResponse;
import com.sandra.car_rentals.Model.LoginResponse;
import com.sandra.car_rentals.Model.User;
import com.sandra.car_rentals.Services.AuthenticationService;
import com.sandra.car_rentals.Services.UserServices;

@RestController
@RequestMapping(value = "/users")

public class UserController {

    @Autowired
    private final UserServices userService;
    private final AuthenticationService authService;

    public UserController(AuthenticationService authService, UserServices userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User request) {
        AuthenticationResponse authResponse = authService.authenticate(request);
        Optional<User> user = userService.findByUsername(request.getUsername());
        LoginResponse loginResponse = new LoginResponse(authResponse, user.get());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            // Your reset password logic here
            return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Credentials", "true")
                .body(Map.of("message", "Reset link sent successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Credentials", "true")
                .body(Map.of("error", "Error sending reset link"));
        }
    }
}