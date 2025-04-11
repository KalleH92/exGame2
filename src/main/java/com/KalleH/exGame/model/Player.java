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

    @Column(name="factories")
    private Integer factories = 0;

    @Column(name="workers")
    private Integer workers = 0;

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

    public Integer getFactories() {
        return factories;
    }

    public void setFactories(Integer factories) {
        this.factories = factories;
    }

    public Integer getWorkers() {
        return workers;
    }

    public void setWorkers(Integer workers) {
        this.workers = workers;
    }
}
