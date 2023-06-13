package com.ben.wandwars.wands.items.sniperWand;

import com.ben.wandwars.spell.templates.ShieldSpellInfo;
import com.ben.wandwars.spell.templates.Spell;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class SniperWandPistolShot extends Spell {

    private LivingEntity target = null;

    private float speed = 1.7f;
    private float homingAmount = 0.2f;
    @Override
    public boolean interrupts() {
        return false;
    }

    @Override
    public void onShieldHit(ShieldSpellInfo shieldSpell) {
        delete();
    }

    @Override
    public void onSpellHit(Spell spell) {
        location.getWorld().createExplosion(location, 1, false, false);
        delete();
    }

    @Override
    public void onBlockHit(Block block, Location lastLocation) {
        location.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, location, 0, 0, 0,0);
        delete();
    }

    @Override
    public void onEntityHit(LivingEntity entity) {
        entity.damage(3);
        delete();
    }

    @Override
    public void onOutOfRange() {
        delete();
    }

    @Override
    protected void onTick() {


        if(getTicks() == 0) {
            getTarget();
            System.out.println(target);
        }

        if(target != null) {
            Vector victimLoc = target.getEyeLocation().toVector().clone();

            float nonHomingAmount = 1 - homingAmount;

            spellDir.multiply(nonHomingAmount);

            spellDir.add(victimLoc.subtract(location.toVector()).normalize().multiply(homingAmount));


            spellDir.normalize();
            spellDir.multiply(speed);

            location.add(spellDir);
        } else {
            double lowestDistance = 100;

            for(Entity entity : location.getWorld().getNearbyEntities(location, 10, 10, 10)) {
                if(entity instanceof LivingEntity && !entity.getUniqueId().equals(getCaster()) && entity.getType()!= EntityType.ARMOR_STAND) {
                    LivingEntity potentialVictim = (LivingEntity) entity;

                    //gets the distance and if the distance is lower than the lowest distance make the victim this entity
                    double distance = potentialVictim.getEyeLocation().toVector().distance(location.toVector());

                    if(distance < lowestDistance) {
                        target = potentialVictim;
                    }
                }
            }

            spellDir.normalize();
            spellDir.multiply(speed);

            location.add(spellDir);
        }

        location.getWorld().spawnParticle(Particle.CRIT, location.getX(), location.getY(),  location.getZ(), 0, 0, 0, 0);

    }

    @Override
    public void onCast() {

    }

    @Override
    public int tickInterval() {
        return 1;
    }

    @Override
    public int getRange() {
        return 18;
    }

    @Override
    public void onInterruption(Spell interrupter) {

    }

    @Override
    public void onInterruptionHit(Spell interrupted) {
        Bukkit.getPlayer(getCaster()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 1));
    }

    @Override
    public double getRadius() {
        return 0.25;
    }

    private void getTarget() {

        Vector direction = spellDir.clone();

        direction.normalize();
        direction.multiply(speed);


        Location particleLocation = location.clone();

        double lowestDistance = 1000;
        for(int i = 0; i < getRange() * 2; i++) {

            particleLocation.add(direction);

            for(Entity entity : particleLocation.getWorld().getNearbyEntities(particleLocation, 10, 10, 10)) {
                if(entity instanceof LivingEntity) {
                    double distance = particleLocation.distance(((LivingEntity) entity).getEyeLocation());

                    if(distance < lowestDistance && entity.getUniqueId() != getCaster()) {

                        lowestDistance = distance;
                        target = (LivingEntity) entity;
                    }
                }
            }

        }
    }
}
