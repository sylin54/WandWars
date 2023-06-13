package com.ben.wandwars.hitBoxes;

import org.bukkit.util.Vector;

public class HitPoint {
    private Vector location;
    private double radius;

    public HitPoint(Vector location, double radius) {
        this.location = location;
        this.radius = radius;
    }

    public Vector getLocation() {
        return location;
    }

    public double getRadius() {
        return radius;
    }


}
