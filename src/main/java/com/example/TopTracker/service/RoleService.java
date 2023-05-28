package com.example.TopTracker.service;

import com.example.TopTracker.dto.RoleDto;
import com.example.TopTracker.models.Role;
import com.example.TopTracker.repository.RoleRepository;
import com.example.TopTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;

    }

    public RoleDto createRole(RoleDto roleDto) {
        Role r = new Role();
        RoleDto roleDTO = new RoleDto();

        r.setRolename(roleDto.rolename);

        Role role = roleRepository.save(r);
        roleDTO.setRolename(role.getRolename());


        return roleDTO;
    }
}
