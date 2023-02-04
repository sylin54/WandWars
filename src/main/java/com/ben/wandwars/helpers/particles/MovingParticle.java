package com.ben.wandwars.helpers.particles;


import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class MovingParticle {


    Vector direction = new Vector(0, 0, 0);

    Particle particle;

    public MovingParticle(Particle particle) {
        this.particle = particle;
    }

    public MovingParticle(Particle particle, Vector direction , double speed) {
        direction.normalize();

        this.particle = particle;

        this.direction = direction;
        direction = direction.multiply(speed);
    }

    public MovingParticle(Particle particle, Vector speed) {
        this.particle = particle;

        this.direction = speed;
    }



    public void spawn(Location location) {
        location.getWorld().spawnParticle(particle, location, 0, direction.getX(), direction.getY(), direction.getZ());
    }



    public Particle getParticle() {return particle;}
    public void setParticle(Particle particle) {this.particle = particle;}

    public Vector getDirection(Vector direction) {return direction;}

    public void setDirection(Vector direction) {this.direction = direction;}
    public void setDirection(Vector direction, double speed) {
        direction.normalize();

        this.direction = direction.multiply(speed);
    }

}
