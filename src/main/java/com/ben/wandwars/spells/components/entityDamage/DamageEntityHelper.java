package com.ben.wandwars.spells.components.entityDamage;

import com.ben.wandwars.DeathMessageHandling;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DamageEntityHelper {
    List<UUID> hitUUIDs = new ArrayList<>();

    float boundingBox;
    OnHitEffect onHitEffect;

    public DamageEntityHelper(float boundingBox, OnHitEffect onHitEffect) {
        this.boundingBox = boundingBox;
        this.onHitEffect = onHitEffect;
    }

    //damages entities, returns a list of the
    public boolean damageEntities(Location location, List<UUID> invincibles, UUID damager) {

        hitUUIDs.clear();

        boolean isSuccess = false;

        for(Entity entity  : location.getWorld().getNearbyEntities(location, boundingBox, boundingBox, boundingBox)) {
            if(entity instanceof LivingEntity && !invincibles.contains(entity.getUniqueId())) {
                LivingEntity livingEntity = (LivingEntity) entity;

                onHitEffect.hit(livingEntity);

                hitUUIDs.add(livingEntity.getUniqueId());
                isSuccess = true;

                //the death message logic
                if(livingEntity instanceof Player) {
                    DeathMessageHandling.getInstance().setLastKiller(livingEntity.getUniqueId(), damager);
                }
            }
        }

        return isSuccess;
    }


    public List<UUID> getHitUUIDs() {
        return hitUUIDs;
    }
}
