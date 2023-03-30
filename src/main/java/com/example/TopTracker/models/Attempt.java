package com.example.TopTracker.models;

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

    private Long user_id;
    @ManyToOne
    private Attempt attempts;

    public Attempt() {
    }

    public Attempt(Long id, boolean send, String notes, String video, Long user_id, Attempt attempts) {
        this.id = id;
        this.send = send;
        this.notes = notes;
        this.video = video;
        this.user_id = user_id;
        this.attempts = attempts;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Attempt getAttempts() {
        return attempts;
    }

    public void setAttempts(Attempt attempt) {
        this.attempts = attempt;
    }
}
