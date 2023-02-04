package com.ben.wandwars.spells.wandEffects;

import com.ben.wandwars.helpers.particles.MovingParticleGroup;
import com.ben.wandwars.spells.components.ProjectileInf;
import com.ben.wandwars.spells.components.ProjectileSpellComponent;
import com.ben.wandwars.spells.components.ProjectileSpellRun;
import com.ben.wandwars.spells.components.blockHit.BlockHitEffect;
import com.ben.wandwars.spells.components.entityDamage.OnHitEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.UUID;

public class HomingProjectileWandEffect extends WandEffect{

    double homingAmount;
    double homingRadius;

    public HomingProjectileWandEffect(OnHitEffect onHitEffect, BlockHitEffect blockHitEffect, ProjectileInf projectileInf, MovingParticleGroup movingParticleGroup, float homingAmount, double homingRadius) {
        super(onHitEffect, blockHitEffect, projectileInf, movingParticleGroup);
        this.homingAmount = homingAmount;
        this.homingRadius = homingRadius;
    }

    @Override
    public void cast(Vector direction, Location location, UUID casterUUID) {
        ProjectileSpellComponent projectileSpellComponent = createProjectileComponent(location, direction, casterUUID);



        projectileSpellComponent.cast(new ProjectileSpellRun() {

            World world = location.getWorld();
            LivingEntity victim = getTarget(direction, location, casterUUID);

            Vector changingDirection = direction.clone();
            double nonHomingAmount = 1 - homingAmount;


            @Override
            public void run() {
                if(victim != null) {
                    Vector victimLoc = victim.getLocation().toVector().add(new Vector(0, 1, 0)).clone();

                    projectileSpellComponent.getMovement().multiply(nonHomingAmount);

                    Vector direction = victimLoc.clone().subtract(projectileSpellComponent.getLocation().toVector()).normalize();


                    projectileSpellComponent.getMovement().add(direction.multiply(homingAmount));
                    projectileSpellComponent.getMovement().normalize();
                    projectileSpellComponent.getMovement().multiply(projectileInf.getSpeed());

                } else {
                    double lowestDistance = 1000;

                    for(Entity entity : world.getNearbyEntities(projectileSpellComponent.getLocation(), homingRadius, homingRadius, homingRadius)) {
                        if(entity instanceof LivingEntity && !projectileSpellComponent.getHitUUIDS().contains(entity.getUniqueId()) && entity.getType()!= EntityType.ARMOR_STAND) {
                            LivingEntity potentialVictim = (LivingEntity) entity;

                            //gets the distance and if the distance is lower than the lowest distance make the victim this entity
                            double distance = potentialVictim.getEyeLocation().toVector().distance(projectileSpellComponent.getLocation().toVector());

                            if(distance < lowestDistance) {
                                victim = potentialVictim;
                                lowestDistance = distance;
                            }
                        }
                    }
                }

            }
        });
    }

    private LivingEntity getTarget(Vector direction, Location particleLocation, UUID caster) {
        direction.normalize();
        direction.multiply(projectileInf.getSpeed());



        double lowestDistance = 1000;
        LivingEntity victim = null;
        for(int i = 0; i < projectileInf.getRange() * projectileInf.getSpeed(); i++) {

            particleLocation.add(direction);

            for(Entity entity : particleLocation.getWorld().getNearbyEntities(particleLocation, homingRadius, homingRadius, homingRadius)) {

                if(!onHitEffect.hitsCaster() && entity.getUniqueId() == caster) continue;

                if(entity instanceof LivingEntity) {
                    double distance = particleLocation.distance(((LivingEntity) entity).getEyeLocation());

                    if(distance < lowestDistance) {
                        lowestDistance = distance;
                        victim = (LivingEntity) entity;
                    }
                }
            }

        }
        return victim;

    }
}
