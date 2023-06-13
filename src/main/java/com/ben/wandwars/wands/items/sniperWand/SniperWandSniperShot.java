package com.ben.wandwars.wands.items.sniperWand;

import com.ben.wandwars.Main;
import com.ben.wandwars.spell.templates.ShieldSpellInfo;
import com.ben.wandwars.spell.templates.Spell;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SniperWandSniperShot extends Spell {

    private double speed;
    private int range;
    private int chargeTime;
    private int damage;

    public SniperWandSniperShot(double speed, int range, int chargeTime, int damage) {
        this.speed = speed;
        this.range = range;
        this.chargeTime = chargeTime;
        this.damage = damage;
    }

    @Override
    public boolean interrupts() {
        return true;
    }

    @Override
    public void onShieldHit(ShieldSpellInfo shieldSpell) {
        if(shieldSpell.getShieldPower() > 5) {
            location.getWorld().createExplosion(location, 1, false, false);
            delete();
        }
    }

    @Override
    public void onSpellHit(Spell spell) {

    }

    @Override
    public void onBlockHit(Block block, Location lastLocation) {
        delete();
    }

    @Override
    public void onEntityHit(LivingEntity entities) {

    }

    @Override
    public void onOutOfRange() {

    }

    @Override
    protected void onTick() {
        if(getTicks() == chargeTime) {
            List<UUID> hitUUIDs = new ArrayList<>();
            hitUUIDs.add(getCaster());

            for (int i = 0; i < range; i++) {

                for (Entity entity : location.getWorld().getNearbyEntities(location, 1, 1, 1)) {

                    if (hitUUIDs.contains(entity.getUniqueId())) {
                        continue;
                    }

                    if (!(entity instanceof LivingEntity)) {
                        continue;
                    }

                    LivingEntity livingEntity = (LivingEntity) entity;



                    hitUUIDs.add(livingEntity.getUniqueId());

                }

                location.getWorld().spawnParticle(Particle.CRIT, 0, 0, 0, 0);

                location.add(spellDir);

                if (!Main.isTransparent(location.getBlock().getType())) {
                    break;
                }
            }
        }

        delete();
    }

    @Override
    public void onCast() {
        spellDir.multiply(speed);

        List<UUID> hitUUIDs = new ArrayList<>();
        hitUUIDs.add(getCaster());

        isCharging = true;

        for (int i = 0; i < range; i++) {

            for (Entity entity : location.getWorld().getNearbyEntities(location, 1, 1, 1)) {

                if (hitUUIDs.contains(entity.getUniqueId())) {
                    continue;
                }

                if (!(entity instanceof LivingEntity)) {
                    continue;
                }

                LivingEntity livingEntity = (LivingEntity) entity;

                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, chargeTime, 1));

                hitUUIDs.add(livingEntity.getUniqueId());

            }

            location.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, 0, 0, 0, 0);

            location.add(spellDir);

            if (!Main.isTransparent(location.getBlock().getType())) {
                break;
            }
        }
    }

    @Override
    public int tickInterval() {
        return 0;
    }

    @Override
    public int getRange() {
        return 100;
    }

    @Override
    public void onInterruption(Spell interrupter) {

    }

    @Override
    public void onInterruptionHit(Spell interrupted) {

    }

    @Override
    public double getRadius() {
        return 0;
    }
}
