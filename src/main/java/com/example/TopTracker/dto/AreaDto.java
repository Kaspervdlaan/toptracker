package com.example.TopTracker.dto;

import com.example.TopTracker.models.Block;

import java.util.List;

public class AreaDto {
    public Long id;
    public String name;
    public String address;
    public String description;


    public List<Long> blocks;

    public AreaDto() {  }

    public AreaDto(Long id, String name, String address, String description, List<Long> blocks) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.blocks = blocks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Long> blocks) {
        this.blocks = blocks;
    }
}
