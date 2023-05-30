package com.example.TopTracker.service;

import com.example.TopTracker.JwtUtils.JwtUtils;
import com.example.TopTracker.dto.UserCredentialsDto;
import com.example.TopTracker.dto.UserDto;
import com.example.TopTracker.exeption.ResourceNotFoundException;
import com.example.TopTracker.models.FileDocument;
import com.example.TopTracker.models.Role;
import com.example.TopTracker.models.User;
import com.example.TopTracker.repository.AttemptRepository;
import com.example.TopTracker.repository.RoleRepository;
import com.example.TopTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final AttemptRepository attemptRepository;

    @Autowired
    private JwtUtils jwtUtils;


    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, AttemptRepository attemptRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.attemptRepository = attemptRepository;
    }


    public UserDto createUser(UserDto userDto) {
        User u = new User();
        UserDto userDTO = new UserDto();

        u.setUsername(userDto.username);
        u.setPassword(encoder.encode(userDto.password));

        List<Role> roles = new ArrayList<>();
            for (String role : userDto.roles) {
                Optional<Role> or = Optional.ofNullable(roleRepository.findByRolename(role));

                roles.add(or.get());
            }

            u.setRoles(roles);


        User user = userRepository.save(u);
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());


        if (user.getRoles() != null) {
            userDTO.setRoles(userDto.getRoles());
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
                userDto.setRoles(userDto.getRoles());
            }

            if (u.getAttempts() != null) {
                userDto.setAttempts(u.getAttempts());
            }

            if (u.getLogbook() != null) {
                userDto.setLogbook_id(u.getLogbook().getId());
            }
            users.add(userDto);
        }

        return users;
    }

    public UserDto getMe(String authHeader) {
        User u = userRepository.findByUsername(jwtUtils.extractUsernameFromToken(authHeader));
        UserDto userDto = new UserDto();

        userDto.userId = u.getUserId();
        userDto.username = u.getUsername();
        userDto.password = u.getPassword();

        if (u.getRoles() != null) {
            userDto.setRoles(userDto.getRoles());
        }

        if (u.getAttempts() != null) {
            userDto.setAttempts(u.getAttempts());
        }

        if (u.getLogbook() != null) {
            userDto.setLogbook_id(u.getLogbook().getId());
        }

        if (u.getFileDocuments() != null) {
            List<byte[]> bytes = new ArrayList<>();
            List<FileDocument> documents = u.getFileDocuments();
            for (FileDocument f : documents) {
                f.getDocFile();
                bytes.add(f.getDocFile());
            }
            userDto.setDocFiles(bytes);
        }

        return userDto;
    }

    public UserDto getUserById(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserDto userDto = new UserDto();

        userDto.userId = u.getUserId();
        userDto.username = u.getUsername();
        userDto.password = u.getPassword();

        if (u.getRoles() != null) {
            userDto.setRoles(userDto.getRoles());
        }

        if (u.getAttempts() != null) {
            userDto.setAttempts(u.getAttempts());
        }

        if (u.getLogbook() != null) {
            userDto.setLogbook_id(u.getLogbook().getId());
        }

        return userDto;
    }

    public List<UserCredentialsDto> getAllUserCredentials(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        return users.stream()
                .map(user -> new UserCredentialsDto(user.getUserId(), user.getUsername(), user.getRoles().stream().map(Role::getRolename).collect(Collectors.toList())))
                .collect(Collectors.toList());

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

    public UserDto updateMe(UserDto userDto, String authHeader) {
        User u = userRepository.findByUsername(jwtUtils.extractUsernameFromToken(authHeader));

        u.setUsername(userDto.getUsername());
        u.setPassword(userDto.getPassword());

        userRepository.save(u);
        userDto.userId = u.getUserId();
        userRepository.save(u);
        return userDto;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
