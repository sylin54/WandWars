package com.ben.wandwars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class MiscListener implements Listener {
    //EffectsManager effectsManager;

    @EventHandler
    private void onBlockBreak(BlockBreakEvent event) {
        if(event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!player.isOp()) {
            player.getInventory().clear();
        }

        if(!Bukkit.getOfflinePlayer(player.getUniqueId()).hasPlayedBefore()) {
            Bukkit.broadcastMessage(ChatColor.YELLOW + player.getDisplayName() + " Has joined the server for the first time! Do /wands to get started!");
        }

        //effectsManager.display(player);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDamage(EntityDamageEvent event) {

        if(event.getEntity() == null) {
            return;
        }

        if(event.getEntity() instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) event.getEntity();

            //effectsManager.damage(livingEntity);
        }
    }
}
