package com.example.TopTracker.dto;


public class BlockDto {
    public Long id;
    public String blockName;
    public String stoneType;

    public Long area_id;

    public BlockDto() {

    }

    public BlockDto(Long id, String blockName, String stoneType, Long area_id) {
        this.id = id;
        this.blockName = blockName;
        this.stoneType = stoneType;
        this.area_id = area_id;
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

    public Long getArea_id() {
        return area_id;
    }

    public void setArea_id(Long area_id) {
        this.area_id = area_id;
    }
}
