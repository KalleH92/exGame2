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

    @Column(name="egglingCount")
    private Integer egglingCount = 0;

    @Column(name="sharpFangCount")
    private Integer sharpFangCount = 0;

    @Column(name="hatchSpeedCount")
    private Integer hatchSpeedCount = 0;

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

    public Integer getEgglingCount() {
        return egglingCount;
    }

    public void setEgglingCount(Integer egglingCount) {
        this.egglingCount = egglingCount;
    }

    public Integer getSharpFangCount() {return sharpFangCount;}

    public void setSharpFangCount(Integer sharpFangCount) {this.sharpFangCount = sharpFangCount;}

    public Integer getHatchSpeedCount() {return hatchSpeedCount;}

    public void setHatchSpeedCount(Integer hatchSpeedCount) {this.hatchSpeedCount = hatchSpeedCount;}
}


