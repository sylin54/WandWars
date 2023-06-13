package com.ben.wandwars.wands.items.sniperWand;

import com.ben.wandwars.spell.templates.ShieldSpellInfo;
import com.ben.wandwars.spell.templates.Spell;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class SniperWandJump extends Spell {

    private double forwardSpeed;
    private double upwardSpeed;

    public SniperWandJump(double forwardSpeed, double upwardSpeed) {
        this.forwardSpeed = forwardSpeed;
        this.upwardSpeed = upwardSpeed;
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

    }

    @Override
    public void onCast() {

        Player player = Bukkit.getPlayer(getCaster());

        Vector velocity = spellDir;

        velocity.multiply(forwardSpeed);
        velocity.setY(upwardSpeed);

        player.setVelocity(velocity);

        //do particles

        delete();
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
