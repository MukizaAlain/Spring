package com.sandra.car_rentals.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandra.car_rentals.Model.User;
import com.sandra.car_rentals.Repository.UserRepository;
import com.sandra.car_rentals.response.MessageResponse;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public MessageResponse saveUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return new MessageResponse("Email is already taken");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            return new MessageResponse("Username is already taken");
        }

        if (!userRepository.existsByUsernameAndEmail(user.getUsername(), user.getEmail())) {
            userRepository.save(user);
            return new MessageResponse("Account Created successfully");
        } else {
            return new MessageResponse("User already exists");
        }
    }

    public MessageResponse loginUser(User user) {
        User userlogin = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (userlogin == null) {
            return new MessageResponse("Error: Invalid username or password");
        }
        return new MessageResponse("Login successful");
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
