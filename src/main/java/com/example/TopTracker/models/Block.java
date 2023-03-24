package com.example.TopTracker.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "blocks")
public class Block {
    @Id
    @GeneratedValue
    private Long id;
    private String blockName;
    private String stoneType;

    public Block() {
    }

    public Block(Long id, String blockName, String stoneType) {
        this.id = id;
        this.blockName = blockName;
        this.stoneType = stoneType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getStoneType() {
        return stoneType;
    }

    public void setStoneType(String stoneType) {
        this.stoneType = stoneType;
    }
}