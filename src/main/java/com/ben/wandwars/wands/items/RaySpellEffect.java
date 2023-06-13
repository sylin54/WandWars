package com.ben.wandwars.wands.items;

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

public class RaySpellEffect extends Spell{

    private int damage;
    private Particle particle;
    private int range;
    private double speed;

    Vector offset;

    public RaySpellEffect(int damage, Particle particle, int range, double speed) {
        this.damage = damage;
        this.particle = particle;
        this.range = range;
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
        location.add(offset);

        location.getWorld().spawnParticle(Particle.SMOKE_NORMAL, location, 0, 0, 0, 0);
    }

    @Override
    public void onCast() {
        offset = spellDir.clone().multiply(speed);
    }

    @Override
    public int tickInterval() {
        return 0;
    }

    @Override
    public int getRange() {
        return range;
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
