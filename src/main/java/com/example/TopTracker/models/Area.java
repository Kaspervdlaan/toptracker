package com.example.TopTracker.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "areas")
public class Area {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String description;

    @OneToMany(mappedBy = "area")
    private List<Block> blocks;

    public Area() {}

    public Area(Long id, String name, String address, String description, List<Block> blocks) {
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

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
}
