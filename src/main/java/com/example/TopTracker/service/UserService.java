package com.example.TopTracker.service;

import com.example.TopTracker.dto.UserDto;
import com.example.TopTracker.exeption.ResourceNotFoundException;
import com.example.TopTracker.models.Role;
import com.example.TopTracker.models.User;
import com.example.TopTracker.repository.AttemptRepository;
import com.example.TopTracker.repository.RoleRepository;
import com.example.TopTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final AttemptRepository attemptRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, AttemptRepository attemptRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.attemptRepository = attemptRepository;
    }


    public UserDto createUser(UserDto userDto) {
        User u = new User();
        UserDto userDTO = new UserDto();

        u.setUsername(userDto.username);
        u.setPassword(userDto.password);

        if (userDto.role_id != null) {
            Optional<Role> roleOptional = roleRepository.findById(String.valueOf(userDto.role_id));

            if (roleOptional.isPresent()) {
                u.setRoles(roleOptional.get());
            }
        }

        User user = userRepository.save(u);
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());

        if (user.getRoles() != null) {
            userDTO.setRole_id(user.getRoles().getRolename());
        }

        if (user.getAttempts() != null) {
            userDTO.setUserId(user.getUserId());
        }

        userDTO.setUserId(user.getUserId());

        return userDTO;
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        List<User> userList = userRepository.findAll();


        for (User u : userList) {
            UserDto userDto = new UserDto();
            userDto.userId = u.getUserId();
            userDto.username = u.getUsername();
            userDto.password = u.getPassword();

            if (u.getRoles() != null) {
                userDto.setRole_id(u.getRoles().getRolename());
            }

            if (u.getAttempts() != null) {
                userDto.setAttempts(u.getAttempts());
            }
            users.add(userDto);
        }

        return users;
    }

    public UserDto getUserById(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserDto userDto = new UserDto();

        userDto.userId = u.getUserId();
        userDto.username = u.getUsername();
        userDto.password = u.getPassword();

        if (u.getRoles() != null) {
            userDto.setRole_id(u.getRoles().getRolename());
        }

        return userDto;
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User u = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        u.setUsername(userDto.getUsername());
        u.setPassword(userDto.getPassword());

        userRepository.save(u);
        userDto.userId = u.getUserId();
        userRepository.save(u);
        return userDto;
    }

    public void deleteUserById(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(u);
    }
}
