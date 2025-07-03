package com.KalleH.exGame.model;


import jakarta.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name ="name")
    private String name;
    @Column(name="pts")
    private Integer pts = 0;

    @Column(name="ptsPerClick")
    private Integer ptsPerClick = 1;

    @Column(name="fangCount")
    private Integer fangCount = 0;

    @Column(name="egglings")
    private Integer egglings = 0;

    @Column(name="sharpFangs")
    private Integer sharpFangs = 0;

    @Column(name="hatchSpeed")
    private Integer hatchSpeed = 0;

    public Player() {}
    public Player(String name) {
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPts() {return pts; }

    public void setPts(Integer pts) {this.pts = pts;}

    public Integer getPtsPerClick() {
        return ptsPerClick;
    }

    public void setPtsPerClick(Integer ptsPerClick) {
        this.ptsPerClick = ptsPerClick;
    }

    public Integer getFangCount() {
        return fangCount;
    }

    public void setFangCount(Integer fangCount) {
        this.fangCount = fangCount;
    }

    public Integer getEgglings() {
        return egglings;
    }

    public void setEgglings(Integer egglings) {
        this.egglings = egglings;
    }

    public Integer getSharpFangs() {return sharpFangs;}

    public void setSharpFangs(Integer sharpFangs) {this.sharpFangs = sharpFangs;}

    public Integer getHatchSpeed() {return hatchSpeed;}

    public void setHatchSpeed(Integer hatchSpeed) {this.hatchSpeed = hatchSpeed;}
}


