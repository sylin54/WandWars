package com.ben.wandwars.helpers.particles;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class ParticleHelper {
    public static void spawnParticle(Location location, Vector direction, Particle particle) {
        location.getWorld().spawnParticle(particle, location, 0, direction.getX(), direction.getY(), direction.getZ());
    }

    public static void spawnParticle(Location location, Particle particle) {
        location.getWorld().spawnParticle(particle, location, 0, 0, 0 ,0 );
    }

    public static void spawnParticles(int count, double offset, Location location, Vector direction, Particle particle) {
        for(int i = 0; i < count; i++) {

        }
    }

    public static void spawnParticles(int count, double offset, Location location, Particle particle) {

    }




}
