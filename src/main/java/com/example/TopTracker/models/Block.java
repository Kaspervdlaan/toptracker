package com.example.TopTracker.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "blocks")
public class Block {
    @Id
    @GeneratedValue
    private Long id;
    private String blockName;
    private String stoneType;

    @ManyToOne
    private Area area;

    @OneToMany(mappedBy = "block")
    private List<Boulder> boulders;

    public Block() {
    }

    public Block(Long id, String blockName, String stoneType, Area area, List<Boulder> boulders) {
        this.id = id;
        this.blockName = blockName;
        this.stoneType = stoneType;
        this.area = area;
        this.boulders = boulders;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Boulder> getBoulders() {
        return boulders;
    }

    public void setBoulders(List<Boulder> boulders) {
        this.boulders = boulders;
    }
}
