package com.example.TopTracker.service;

import com.example.TopTracker.dto.UserDto;
import com.example.TopTracker.models.User;
import com.example.TopTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    public UserService(UserRepository userRepo) { this.userRepo = userRepo; }


    public User createUser(UserDto userDto) {
        User u = new User();
        u.setFirstName(userDto.firstName);
        u.setLastName(userDto.lastName);
        u.setDob(userDto.dob);
        u.setEmail(userDto.email);
        u.setUsername(userDto.username);
        u.setPassword(userDto.password);
        userRepo.save(u);

        u.getId();

        userRepo.save(u);

        return u;
    }

    public UserDto getUserById(Long id) {
        User u = userRepo.findById(id).orElseThrow(() -> new RuntimeException("ppop"));
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

    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }
}
