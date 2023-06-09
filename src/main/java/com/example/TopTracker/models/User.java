package com.example.TopTracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<Role> roles;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Logbook logbook;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Attempt> attempts;

    @OneToMany(mappedBy = "user")
    private List<FileDocument> fileDocuments;

    public List<FileDocument> getFileDocuments() {
        return fileDocuments;
    }

    public void setFileDocuments(List<FileDocument> fileDocuments) {
        this.fileDocuments = fileDocuments;
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

    public Logbook getLogbook() {
        return logbook;
    }

    public void setLogbook(Logbook logbook) {
        this.logbook = logbook;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
