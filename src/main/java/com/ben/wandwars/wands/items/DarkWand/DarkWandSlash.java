package com.ben.wandwars.wands.items.DarkWand;

import com.ben.wandwars.spell.templates.ShieldSpellInfo;
import com.ben.wandwars.spell.templates.Spell;
import com.ben.wandwars.wands.items.RaySpellEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class DarkWandSlash extends Spell {

    private int windup;
    private Player player;
    private Vector offset;

    public DarkWandSlash(int windup) {
        this.windup = windup;
    }
    @Override
    public boolean interrupts() {
        return false;
    }

    @Override
    public void onShieldHit(ShieldSpellInfo shieldSpell) {

    }

    @Override
    public void onSpellHit(Spell spell) {

    }

    @Override
    public void onBlockHit(Block block, Location lastLocation) {

    }

    @Override
    public void onEntityHit(LivingEntity entities) {

    }

    @Override
    public void onOutOfRange() {

    }

    @Override
    protected void onTick() {

        player.setVelocity(offset);

        if(getTicks() > windup) {
            RaySpellEffect raySpellEffect = new RaySpellEffect(10, Particle.SMOKE_NORMAL, 100, 0.1);
            raySpellEffect.cast(player);

            delete();
        }
    }

    @Override
    public void onCast() {
        isCharging = true;
        player = getPlayerCaster();
        offset = player.getLocation().getDirection().multiply(1.1);
    }

    @Override
    public int tickInterval() {
        return 1;
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
