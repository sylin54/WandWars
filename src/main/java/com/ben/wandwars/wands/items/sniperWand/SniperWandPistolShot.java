package com.ben.wandwars.wands.items.sniperWand;

import com.ben.wandwars.spell.templates.ShieldSpellInfo;
import com.ben.wandwars.spell.templates.Spell;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

public class SniperWandPistolShot extends Spell {

    private LivingEntity victim = null;

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
    }

    @Override
    public void onBlockHit(Block block, Location lastLocation) {
        location.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, location, 0, 0, 0,0);

    }

    @Override
    public void onEntityHit(LivingEntity entity) {

    }

    @Override
    public void onOutOfRange() {

    }

    @Override
    protected void onTick() {

    }

    @Override
    public int tickInterval() {
        return 0;
    }

    @Override
    public int getRange() {
        return 0;
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
