package com.ben.wandwars.wands.listeners;

import com.ben.wandwars.displaying.Displayer;
import com.ben.wandwars.displaying.TitleWarnings;
import com.ben.wandwars.helpers.scheduling.CoolDownManager;
import com.ben.wandwars.helpers.scheduling.CoolDownManagerCallback;
import com.ben.wandwars.stateManagers.DashStateManager;
import com.ben.wandwars.wands.Wands;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import java.util.Random;

public class DashListener implements Listener {
    CoolDownManager dashCoolDown;

    DashStateManager dashStateManager;


    public DashListener( ) {
        this.dashStateManager = DashStateManager.getInstance();

        dashCoolDown = new CoolDownManager(2250, new CoolDownManagerCallback() {
            @Override
            public void run(Player target) {
                dashStateManager.setDash(target, true);
                Displayer.update(target);
            }
        });
    }

    @EventHandler
    private void onShift(PlayerToggleSneakEvent event) {
        if(!event.isSneaking()) return;

        Player player = event.getPlayer();

        //if the player doesn't have a in their hand return wand return
        if(Wands.getWand(player.getInventory().getItemInMainHand()) == null) {
            player.sendMessage(ChatColor.RED + "You must have a wand equip to dash!");
            return;
        }


        //if the cooldown is free cast the dash
        if(dashCoolDown.isFree(player)) {
            dashCoolDown.resetCoolDown(player);
            World world = player.getWorld();


            spawnParticles(15, player.getLocation());
            dash(player);

            dashStateManager.setDash(player, false);

        } else {
            TitleWarnings.sendDashWarning(player, dashCoolDown.getCoolDownDuration(player));
        }
    }

    private void dash(Player player) {
        player.setVelocity(player.getLocation().getDirection().normalize().multiply(1.5));
        player.setVelocity(player.getVelocity().add(new Vector(0, 0.1, 0)));
        player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 3, 1);
    }

    private void spawnParticles(int particles, Location location ) {
        Random random = new Random();
        World world = location.getWorld();

        for(int i = 0; i < particles; i++) {
            int x = random.nextInt(1);
            x += (location.getX() - 0.5);

            int y = random.nextInt(2);
            y += (location.getY() - 1);

            int z = random.nextInt(1);
            z += (location.getZ() - 0.5);

            world.spawnParticle(Particle.REDSTONE, new Location(world, x, y, z), 1, new Particle.DustOptions(Color.WHITE, 3));
        }
    }
}
