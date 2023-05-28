package com.example.TopTracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
@Table(name = "attempts")
public class Attempt {
    @Id
    @GeneratedValue
    private Long id;
    private boolean send;
    private String notes;
    private String video; // This needs to be updated to MultipartFile

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "logbook_id")
    @JsonIgnore
    private Logbook logbook;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "boulder_id")
    @JsonIgnore
    private Boulder boulder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }


    public Boulder getBoulder() {
        return boulder;
    }

    public void setBoulder(Boulder boulder) {
        this.boulder = boulder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Logbook getLogbook() {
        return logbook;
    }

    public void setLogbook(Logbook logbook) {
        this.logbook = logbook;
    }
}
