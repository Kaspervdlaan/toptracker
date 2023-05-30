package com.example.TopTracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "boulders")
public class Boulder {
    @Id
    @GeneratedValue
    private Long id;
    private String boulderName;
    private String boulderGrade;
    private String holdType;
    private String boulderNotes;

    private String boulderImage;

    @OneToMany(mappedBy = "boulder", cascade = CascadeType.ALL)
    private List<Attempt> attempts;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "block_id")
    @JsonIgnore
    private Block block;

    public Boulder() {
    }

    public Boulder(Long id) {
        this.id = id;
    }

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

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Attempt> attempts) {
        this.attempts = attempts;
    }
}
