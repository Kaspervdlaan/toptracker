package com.example.TopTracker.service;

import com.example.TopTracker.dto.UserDto;
import com.example.TopTracker.exeption.ResourceNotFoundException;
import com.example.TopTracker.models.Role;
import com.example.TopTracker.models.User;
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

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public UserDto createUser(UserDto userDto) {
        User u = new User();
        UserDto userDTO = new UserDto();

        u.setFirstName(userDto.firstName);
        u.setLastName(userDto.lastName);
        u.setDob(userDto.dob);
        u.setEmail(userDto.email);
        u.setUsername(userDto.username);
        u.setPassword(userDto.password);

        if (userDto.role_id != null) {
            Optional<Role> roleOptional = roleRepository.findById(String.valueOf(userDto.role_id));

            if (roleOptional.isPresent()) {
                u.setRole(roleOptional.get());
            }
        }

        User user = userRepository.save(u);
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setDob(user.getDob());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());

        if (user.getRole() != null) {
            userDTO.setRole_id(user.getRole().getId());
        }

        userDTO.setId(user.getId());

        return userDTO;
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        List<User> userList = userRepository.findAll();


        for (User u : userList) {
            UserDto userDto = new UserDto();
            userDto.id = u.getId();
            userDto.firstName = u.getFirstName();
            userDto.lastName = u.getLastName();
            userDto.dob = u.getDob();
            userDto.email = u.getEmail();
            userDto.username = u.getUsername();
            userDto.password = u.getPassword();

            if (u.getRole() != null) {
                userDto.setRole_id(u.getRole().getId());
            }
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

        if (u.getRole() != null) {
            userDto.setRole_id(u.getRole().getId());
        }

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
