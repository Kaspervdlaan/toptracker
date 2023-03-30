package com.example.TopTracker.dto;

import com.example.TopTracker.models.User;

import java.util.List;

public class RoleDto {
    public Long id;
    public String roleName;

    public List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
