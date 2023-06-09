package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.RoleDto;
import com.example.TopTracker.models.Role;
import com.example.TopTracker.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {

        RoleDto r = roleService.createRole(roleDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(r.getRolename()).toUri();

        return ResponseEntity.created(uri).body(r);
    }

}
