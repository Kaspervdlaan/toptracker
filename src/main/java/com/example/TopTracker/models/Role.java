package com.example.TopTracker.models;
import jakarta.persistence.*;

import java.util.Collection;
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String rolename;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role(String rolename, Collection<User> users) {
        this.rolename = rolename;
        this.users = users;
    }

    public Role() {

    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
