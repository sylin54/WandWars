package com.ben.wandwars.spells.components;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.UUID;

public class ProjectileInf {
    private float speed;
    private int interval = 0;

    private int range;

    float offset = 0.1f;


    public ProjectileInf(float speed, int range, int interval) {
        this.speed = speed;
        this.interval = interval;
        this.range = range;
    }

    public ProjectileInf(float speed, int range) {
        this.speed = speed;
        this.range = range;
    }

    public float getSpeed() {
        return speed;
    }

    public int getInterval() {
        return interval;
    }

    public int getRange() {
        return range;
    }

    public float getOffset() {return offset;}
    public void setOffset(float offset) {this.offset = offset;}
}

