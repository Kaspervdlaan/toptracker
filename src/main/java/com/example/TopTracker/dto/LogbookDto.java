package com.example.TopTracker.dto;

import com.example.TopTracker.models.Attempt;
import com.example.TopTracker.models.User;

import java.util.List;

public class LogbookDto {
    public Long id;
    public Long user_id;

    public List<Attempt> attempts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Attempt> attempts) {
        this.attempts = attempts;
    }
}
