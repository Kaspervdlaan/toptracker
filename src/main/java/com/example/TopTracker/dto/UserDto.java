package com.example.TopTracker.dto;

import com.example.TopTracker.models.Attempt;
import com.example.TopTracker.models.Logbook;
import com.example.TopTracker.models.Role;

import java.util.Collection;
import java.util.List;

public class UserDto {
    public Long userId;
    public String username;
    public String password;

    public String[] roles;

    public List<Attempt> attempts;

    public Long logbook_id;

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Attempt> attempts) {
        this.attempts = attempts;
    }

    public Long getLogbook_id() {
        return logbook_id;
    }

    public void setLogbook_id(Long logbook_id) {
        this.logbook_id = logbook_id;
    }
}
