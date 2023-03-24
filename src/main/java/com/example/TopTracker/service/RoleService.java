package com.example.TopTracker.service;

import com.example.TopTracker.dto.RoleDto;
import com.example.TopTracker.models.Role;
import com.example.TopTracker.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository userRepo;

    public RoleService(RoleRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Role addRole(RoleDto roleDto) {
        Role r = new Role();
        r.setRoleName(roleDto.roleName);
        userRepo.save(r);
        r.getId();
        userRepo.save(r);

        return r;
    }


}
