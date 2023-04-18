package com.example.TopTracker.dto;

import com.example.TopTracker.models.User;

import java.util.List;

public class RoleDto {
    public String rolename;

    public List<User> users;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
