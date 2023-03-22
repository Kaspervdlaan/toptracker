package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.UserDto;
import com.example.TopTracker.models.User;
import com.example.TopTracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {

        User u = userService.createUser(userDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getUsername()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
