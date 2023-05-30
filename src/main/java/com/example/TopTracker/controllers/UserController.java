package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.UserCredentialsDto;
import com.example.TopTracker.dto.UserDto;
import com.example.TopTracker.models.User;
import com.example.TopTracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.Authenticator;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        UserDto u = userService.createUser(userDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(userDto).toUri();

        return ResponseEntity.created(uri).body(u);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtos = userService.getAllUsers();

        return ResponseEntity.ok().body(userDtos);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long id) {
        UserDto userDto = userService.getUserById(id);

        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe(@RequestHeader("Authorization") String authHeader) {
        UserDto userDto = userService.getMe(authHeader);
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/credentials")
    public List<UserCredentialsDto> getAllUserCredentials() {
        return userService.getAllUserCredentials();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable("userId") Long id, @RequestBody UserDto userDto) {
        UserDto userDTO = userService.updateUser(id, userDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(userDTO).toUri();

        return ResponseEntity.created(uri).body(userDTO);
    }

    @PutMapping("/update/me")
    public ResponseEntity<Object> updateUser(@RequestHeader("Authorization") String authHeader, @RequestBody UserDto userDto) {
        UserDto userDTO = userService.updateMe(userDto, authHeader);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(userDTO).toUri();

        return ResponseEntity.created(uri).body(userDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("userId") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
