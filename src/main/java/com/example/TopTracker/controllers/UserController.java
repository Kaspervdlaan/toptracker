package com.example.TopTracker.controllers;

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
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {

        Long id = userService.createUser(userDto);
        userDto.id = id;
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + id).toUriString());

        return ResponseEntity.created(uri).body(id);
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth.getPrincipal() instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) auth.getPrincipal();
//
//        }

        List<UserDto> userDtos = userService.getAllUsers();

        return ResponseEntity.ok().body(userDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);

        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto userDTO = userService.updateUser(id, userDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(userDTO).toUri();

        return ResponseEntity.created(uri).body(userDTO);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
