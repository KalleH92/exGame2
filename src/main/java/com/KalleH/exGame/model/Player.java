package com.KalleH.exGame.model;


import jakarta.persistence.*;

@Entity

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Player() {}
    public Player(long id) {
        this.id = id;
    }
    @Column(name ="name")
    private String name;
    @Column(name="pts")
    private Integer pts;

    public Player (long id, String name) {
        this.id = id;
        this.name = name;
        this.pts = pts;

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

    public Integer getPts() {
        return pts;
    }

    public void setPts(Integer pts) {
        this.pts = pts;
    }
}
