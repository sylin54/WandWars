package com.ben.wandwars.wands.listeners.dash;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Random;

public class DashUtil {
    public static void dash(Player player) {
        player.setVelocity(player.getLocation().getDirection().normalize().multiply(1.5));
        player.setVelocity(player.getVelocity().add(new Vector(0, 0.1, 0)));
        player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 3, 1);
    }

    public static void spawnParticles(int particles, Location location ) {
        Random random = new Random();
        World world = location.getWorld();

        for(int i = 0; i < particles; i++) {
            int x = random.nextInt(1);
            x += (location.getX() - 0.5);

            int y = random.nextInt(2);
            y += (location.getY() - 1);

            int z = random.nextInt(1);
            z += (location.getZ() - 0.5);

            world.spawnParticle(Particle.REDSTONE, new Location(world, x, y, z), 1, new Particle.DustOptions(Color.WHITE, 3));
        }
    }
}
