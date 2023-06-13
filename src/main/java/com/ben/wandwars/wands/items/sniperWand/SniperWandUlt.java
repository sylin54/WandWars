package com.ben.wandwars.wands.items.sniperWand;

import com.ben.wandwars.spell.templates.ShieldSpellInfo;
import com.ben.wandwars.spell.templates.Spell;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.nio.file.Path;

public class SniperWandUlt extends Spell {

    private int tickPeriod;
    private SniperWandSniperShot sniperWandSniperShot;

    private int attacks;

    public SniperWandUlt(double speed, int range, int chargeTime, int damage, int tickPeriod, int attacks) {
        this.tickPeriod = tickPeriod;
        this.attacks = attacks;


        sniperWandSniperShot = new SniperWandSniperShot(speed, range, chargeTime, damage);
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


        if(getTicks() == 0) {
            Player player = Bukkit.getPlayer(getCaster());

            sniperWandSniperShot.cast(player.getLocation(), player.getLocation().getDirection(), player.getUniqueId());

            attacks--;
            return;
        }

        if(getTicks() % tickPeriod == 1) {

            if(attacks < 0) {
                delete();
                return;
            }

            Player player = Bukkit.getPlayer(getCaster());

            sniperWandSniperShot.cast(player.getLocation(), player.getLocation().getDirection(), player.getUniqueId());

            attacks--;
            return;
        }

    }

    @Override
    public void onCast() {

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
