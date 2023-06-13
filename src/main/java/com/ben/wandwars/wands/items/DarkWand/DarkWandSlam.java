package com.ben.wandwars.wands.items.DarkWand;

import com.ben.wandwars.Main;
import com.ben.wandwars.spell.templates.ShieldSpellInfo;
import com.ben.wandwars.spell.templates.Spell;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class DarkWandSlam extends Spell {

    private Player player;
    private boolean onBlock;

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
        if(onBlock && getTicks() <= 4) {
            System.out.println("test");
            return;
        }

        player.setVelocity(player.getVelocity().add(new Vector(0, -0.09, 0)));

        if(!Main.isTransparent(player.getLocation().add(0, -1, 0).getBlock().getType())) {
            //do stuff
            player.sendMessage(ChatColor.RED + "deleted spell");
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 12, 1);
            delete();
        }
    }

    @Override
    public void onCast() {
        player = getPlayerCaster();

        if(Main.isTransparent(player.getLocation().add(0, -0.3, 0).getBlock().getType())) {
            onBlock = false;

        } else {
            onBlock = true;
            player.setVelocity(new Vector(0, 1, 0));
        }


    }

    @Override
    public int tickInterval() {
        return 1;
    }

    @Override
    public int getRange() {
        return 10;
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
