package com.example.TopTracker.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "attempts")
public class Attempt {
    @Id
    @GeneratedValue
    private Long id;
    private boolean send;
    private String notes;
    private String video; // This needs to be updated to MultipartFile

    public Attempt() {
    }

    public Attempt(Long id, boolean send, String notes, String video) {
        this.id = id;
        this.send = send;
        this.notes = notes;
        this.video = video;
    }

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
}
