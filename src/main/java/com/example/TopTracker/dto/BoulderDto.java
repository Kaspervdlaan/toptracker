package com.example.TopTracker.dto;

public class BoulderDto {
    public Long id;
    public String boulderName;
    public String boulderGrade;
    public String holdType;
    public String boulderNotes;

    public String boulderImage; // needs updating to MultipartFile

    public Long block_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoulderName() {
        return boulderName;
    }

    public void setBoulderName(String boulderName) {
        this.boulderName = boulderName;
    }

    public String getBoulderGrade() {
        return boulderGrade;
    }

    public void setBoulderGrade(String boulderGrade) {
        this.boulderGrade = boulderGrade;
    }

    public String getHoldType() {
        return holdType;
    }

    public void setHoldType(String holdType) {
        this.holdType = holdType;
    }

    public String getBoulderNotes() {
        return boulderNotes;
    }

    public void setBoulderNotes(String boulderNotes) {
        this.boulderNotes = boulderNotes;
    }

    public String getBoulderImage() {
        return boulderImage;
    }

    public void setBoulderImage(String boulderImage) {
        this.boulderImage = boulderImage;
    }

    public Long getBlock_id() {
        return block_id;
    }

    public void setBlock_id(Long block_id) {
        this.block_id = block_id;
    }
}
