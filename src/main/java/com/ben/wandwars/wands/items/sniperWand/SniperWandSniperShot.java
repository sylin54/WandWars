package com.ben.wandwars.wands.items.sniperWand;

import com.ben.wandwars.spell.templates.ShieldSpellInfo;
import com.ben.wandwars.spell.templates.Spell;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

public class SniperWandSniperShot extends Spell {
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
