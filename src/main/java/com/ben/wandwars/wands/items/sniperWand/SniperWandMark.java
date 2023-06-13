package com.ben.wandwars.wands.items.sniperWand;

import com.ben.wandwars.Main;
import com.ben.wandwars.spell.templates.ShieldSpellInfo;
import com.ben.wandwars.spell.templates.Spell;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SniperWandMark extends Spell {

    //Mark: shoots an invisible raycast, now damage, gives an effect that gives a damage multiple on the hit player, super high mana, super mana

    private double speed;
    private int range;
    private double gravity;

    public SniperWandMark(double speed, int range, double gravity) {
        this.speed = speed;
        this.range = range;
        this.gravity = gravity;
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
        List<UUID> hitUUIDs = new ArrayList<>();
        hitUUIDs.add(getCaster());


        location.add(spellDir);
        location.setY(location.getY() - gravity);
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
        spellDir.multiply(speed);
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
