package com.ben.wandwars.util;

import com.ben.wandwars.spell.managers.SpellManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

//A cirle that is always scanned at an offset from the given location. This is mainly intended for the hitsystem. reccomended to use something else instead for most purposes.
public class OffsetPoint {
    Vector offset;
    double radius;

    SpellManager spellManager = SpellManager.getInstance();

    public OffsetPoint(Vector offset, double radius) {
        this.offset = offset;
        this.radius = radius;
    }

    public List<LivingEntity> getNearbyEntities(Location location) {

        location = location.add(offset);

        List<Entity> entities = (List<Entity>) location.getWorld().getNearbyEntities(location, radius, radius, radius);

        List<LivingEntity> returnValue = new ArrayList<>();
        for(Entity entity : entities) {
            if(entity instanceof LivingEntity) {
                returnValue.add((LivingEntity) entity);
            }
        }
        return returnValue;
    }

    public boolean hasNearbyEntities(Location location) {

        location = location.add(offset);

        List<Entity> entities = (List<Entity>) location.getWorld().getNearbyEntities(location, radius, radius, radius);

        if(entities.size() == 0) {
            return false;
        }

        for(Entity entity : entities) {
            if(entity instanceof LivingEntity) {
                return true;
            }
        }

        return false;
    }

    public boolean hasBlock(Location location) {
        location = location.add(offset);

        return location.getBlock().getType().isAir();
    }

    public Block getBlock(Location location) {
        location = location.add(offset);

        return location.getBlock();
    }

//    public boolean intersectsWithSpell(Location location, Spell parentSpell)
//    {
//        for(Spell spell : spellManager.getSpells()) {
//            if(spell == parentSpell) {
//                continue;
//            }
//
//            double distance = spell.getLocation().distance(location);
//
//            if(distance )
//        }
//    }

//    public List<Spell> getIntersectingSpells(Location location) {
//    }
}
