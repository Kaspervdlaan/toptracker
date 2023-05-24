package com.example.TopTracker.dto;

import com.example.TopTracker.models.User;

public class AttemptDto {
    public Long id;
    public boolean send;
    public String notes;
    public String video; // This needs to be updated to MultipartFile

    public Long user_id;

    public Long boulder_id;

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

    public Long getBoulder_id() {
        return boulder_id;
    }

    public void setBoulder_id(Long boulder_id) {
        this.boulder_id = boulder_id;
    }


}
