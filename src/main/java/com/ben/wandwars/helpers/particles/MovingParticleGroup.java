package com.ben.wandwars.helpers.particles;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/*
creation:
    a list of moving particles, and an offset to display them on
 */

public class MovingParticleGroup {

    Random random = new Random();
    Location lastLocation;

    double offset;

    HashMap<Vector, MovingParticle> particleMap = new HashMap<>();


    List<MovingParticle> movingParticleTypes = new ArrayList<>();

    public MovingParticleGroup(double offset, MovingParticle[] movingParticles ) {
        this.offset = offset;

        for(MovingParticle movingParticle : movingParticles) {
            particleMap.put(new Vector(0, 0, 0), movingParticle);
        }

        shuffleParticles();

    }

    public MovingParticleGroup(Particle particle) {
        this.offset = 0;

        particleMap.put(new Vector(0, 0 , 0), new MovingParticle(particle));
    }

    public MovingParticleGroup() {
        this.offset = 0;
    }

    public void shuffleParticles() {

        List<MovingParticle> particles = new ArrayList<>();

        particles.addAll(particleMap.values());

        particleMap.clear();

        for(MovingParticle movingParticle : particles) {
//            double offsetX = random.nextDouble(offset * 2);
//            offsetX -= offset;
//
//            double offsetY = random.nextDouble(offset * 2);
//            offsetY -= offset;
//
//            double offsetZ = random.nextDouble(offset * 2);
//            offsetZ -= offset;
//
//            particleMap.put(new Vector(offsetX, offsetY, offsetZ), movingParticle);
        }
    }


    public void spawn(Location location) {
        lastLocation = location;

        for(Vector vector : particleMap.keySet()) {
            Location particleLocation = lastLocation.add(vector);

            particleMap.get(vector).spawn(particleLocation);
        }
    }

    public Location getLastLocation() {return lastLocation;}


    public int getCount() {
        return particleMap.size();
    }

    public void addParticle(MovingParticle movingParticle) {
        particleMap.put(new Vector(0, 0, 0), movingParticle);
    }

    public void addParticle(MovingParticle movingParticle, Vector coordinate) {
        particleMap.put(coordinate, movingParticle);
    }

    public void removeParticles(Particle particleType) {
        for(Vector vector : particleMap.keySet()) {
            if(particleType == particleMap.get(vector).getParticle()) {
                particleMap.remove(vector);
            }
        }
    }

    public void setDirections(Vector direction) {
        for(MovingParticle movingParticle : particleMap.values()) {
            movingParticle.setDirection(direction);
        }
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

}
