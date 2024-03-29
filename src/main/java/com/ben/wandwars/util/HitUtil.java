package com.ben.wandwars.util;


import com.ben.wandwars.spell.managers.SpellManager;
import com.ben.wandwars.spell.templates.Spell;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class HitUtil {
    private static SpellManager spellManager = SpellManager.getInstance();
    public static boolean intersectsWithBlock(Spell spell) {
        return !spell.getLocation().getBlock().getType().isAir();
    }

    public static Block getIntersectingBlock(Spell spell) {
        return spell.getLocation().getBlock();
    }

    public static boolean intersectsEntity(Spell spell){

        List<Entity> entities = (List<Entity>) spell.getLocation().getWorld().getNearbyEntities(spell.getLocation(), spell.getRadius(), spell.getRadius(), spell.getRadius());

        for(Entity entity : entities) {
            if(entity instanceof LivingEntity) {
                return true;
            }
        }

        return false;
    }

    public static List<LivingEntity> getEntities(Spell spell) {
        List<Entity> entities = (List<Entity>) spell.getLocation().getWorld().getNearbyEntities(spell.getLocation(), spell.getRadius(), spell.getRadius(), spell.getRadius());
        List<LivingEntity> returnValue = new ArrayList<>();

        for(Entity entity : entities) {
            if(entity instanceof LivingEntity) {
                returnValue.add((LivingEntity) entity);
            }
        }

        return returnValue;
    }

}
