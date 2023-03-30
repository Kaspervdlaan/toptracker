package com.example.TopTracker.service;

import com.example.TopTracker.dto.RoleDto;
import com.example.TopTracker.models.Role;
import com.example.TopTracker.repository.RoleRepository;
import com.example.TopTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public RoleDto createRole(RoleDto roleDto) {
        Role r = new Role();
        RoleDto roleDTO = new RoleDto();

        r.setRoleName(roleDto.roleName);

        Role role = roleRepository.save(r);
        roleDTO.setRoleName(role.getRoleName());

        roleDTO.setId(role.getId());

        return roleDTO;
    }
}
