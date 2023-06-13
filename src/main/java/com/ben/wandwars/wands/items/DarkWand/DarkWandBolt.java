package com.ben.wandwars.wands.items.DarkWand;

import com.ben.wandwars.spell.templates.ShieldSpellInfo;
import com.ben.wandwars.spell.templates.Spell;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import javax.swing.plaf.ViewportUI;

public class DarkWandBolt extends Spell {

    private int windupTicks;
    private int effectTicks;
    private Vector offset;
    private int speed;

    private int damage;

    public DarkWandBolt(int windupTicks, int effectTicks, int speed, int damage) {
        this.windupTicks = windupTicks;
        this.effectTicks = effectTicks;
        this.speed = speed;
        this.damage = damage;
    }

    @Override
    public boolean interrupts() {
        return true;
    }

    @Override
    public void onShieldHit(ShieldSpellInfo shieldSpell) {
        delete();
    }

    @Override
    public void onSpellHit(Spell spell) {

    }

    @Override
    public void onBlockHit(Block block, Location lastLocation) {
        delete();
    }

    @Override
    public void onEntityHit(LivingEntity victim) {
        victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, effectTicks, 1));
        victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, effectTicks, 1));
    }

    @Override
    public void onOutOfRange() {
        delete();
    }

    @Override
    protected void onTick() {

        if(getTicks() == windupTicks) {
            isCharging = false;
            getPlayerCaster().playSound(getPlayerCaster().getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);
        }

        if(getTicks() >= windupTicks) {
            location.add(offset);

            location.getWorld().spawnParticle(Particle.SMOKE_NORMAL, location, 0, 0, 0, 0);
        }
    }

    @Override
    public void onCast() {
        offset = spellDir.clone().multiply(speed);

        isCharging = true;
    }

    @Override
    public int tickInterval() {
        return 1;
    }

    @Override
    public int getRange() {
        return 40;
    }

    @Override
    public void onInterruption(Spell interrupter) {

        Player player = getPlayerCaster();

        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, effectTicks, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, effectTicks, 1));

        player.damage(damage);
    }

    @Override
    public void onInterruptionHit(Spell interrupted) {
        Player player = interrupted.getPlayerCaster();

        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, effectTicks, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, effectTicks, 1));

        player.damage(damage);
    }

    @Override
    public double getRadius() {
        return 0;
    }
}
