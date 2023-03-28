package com.example.TopTracker.service;

import com.example.TopTracker.dto.UserDto;
import com.example.TopTracker.exeption.ResourceNotFoundException;
import com.example.TopTracker.models.User;
import com.example.TopTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepo) { this.userRepository = userRepo; }


    public User createUser(UserDto userDto) {
        User u = new User();

        u.setFirstName(userDto.firstName);
        u.setLastName(userDto.lastName);
        u.setDob(userDto.dob);
        u.setEmail(userDto.email);
        u.setUsername(userDto.username);
        u.setPassword(userDto.password);

        userRepository.save(u);

        return u;
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        UserDto userDto = new UserDto();
        for (User u : userList) {

            userDto.id = u.getId();
            userDto.firstName = u.getFirstName();
            userDto.lastName = u.getLastName();
            userDto.dob = u.getDob();
            userDto.email = u.getEmail();
            userDto.username = u.getUsername();
            userDto.password = u.getPassword();
            users.add(userDto);
        }

        return users;
    }

    public UserDto getUserById(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserDto userDto = new UserDto();

        userDto.id = u.getId();
        userDto.firstName = u.getFirstName();
        userDto.lastName = u.getLastName();
        userDto.dob = u.getDob();
        userDto.email = u.getEmail();
        userDto.username = u.getUsername();
        userDto.password = u.getPassword();

        return userDto;
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User u = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        u.setFirstName(userDto.getFirstName());
        u.setLastName(userDto.getLastName());
        u.setEmail(userDto.getEmail());
        u.setDob(userDto.getDob());
        u.setUsername(userDto.getUsername());
        u.setPassword(userDto.getPassword());

        userRepository.save(u);
        userDto.id = u.getId();
        userRepository.save(u);
        return userDto;
    }

    public void deleteUserById(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(u);
    }
}
