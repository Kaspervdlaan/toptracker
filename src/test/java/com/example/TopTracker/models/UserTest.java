package com.example.TopTracker.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldReturnUserId() {
        User user = new User();
        Long id = 1L;

        user.setUserId(id);

        assertEquals(id, user.getUserId());
    }

    @Test
    void shouldReturnUsername() {
        User user = new User();
        String result = "Kasper";

        user.setUsername(result);

        assertEquals(result, user.getUsername());
    }

    @Test
    void shouldReturnUserpassword() {
        User user = new User();
        String result = "123456";

        user.setUsername(result);

        assertEquals(result, user.getPassword());
    }

    @Test
    void shouldReturnUserRoles() {
        User u = new User();
        List<Role> roles = new ArrayList<>();
        roles.add(new Role());
        roles.add(new Role());

        u.setRoles(roles);

        assertEquals(roles, u.getRoles());
    }

    @Test
    void shouldReturnUserLogbook() {
        User u = new User();
        Logbook l = new Logbook();

        u.setLogbook(l);

        assertEquals(l, u.getLogbook());
    }

    @Test
    void shouldReturnUserAttempts() {
        User u = new User();
        List<Attempt> attempts = new ArrayList<>();
        attempts.add(new Attempt());
        attempts.add(new Attempt());
        attempts.add(new Attempt());

        u.setAttempts(attempts);

        assertEquals(attempts, u.getAttempts());
    }
}