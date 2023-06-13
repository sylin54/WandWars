package com.ben.wandwars.wands.items.DarkWand;

import com.ben.wandwars.spell.templates.ShieldSpellInfo;
import com.ben.wandwars.spell.templates.Spell;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class DarkWandPull extends Spell{
    private int windupTicks;

    private Vector offset;
    private int speed;


    public DarkWandPull(int windupTicks, int speed) {
        this.windupTicks = windupTicks;
        this.speed = speed;
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
        victim.setVelocity(spellDir.clone().multiply(-1));
    }

    @Override
    public void onOutOfRange() {
        delete();
    }

    @Override
    protected void onTick() {

        if(getTicks() == windupTicks) {
            isCharging = false;
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
        return 0;
    }

    @Override
    public int getRange() {
        return 40;
    }

    @Override
    public void onInterruption(Spell interrupter) {

        Player player = getPlayerCaster();

        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5 * 20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 1));
    }

    @Override
    public void onInterruptionHit(Spell interrupted) {
        interrupted.delete();
    }

    @Override
    public double getRadius() {
        return 0;
    }
}
