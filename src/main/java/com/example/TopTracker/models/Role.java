package com.example.TopTracker.models;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String rolename;

    @OneToMany(mappedBy = "roles")
    private List<User> users;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
