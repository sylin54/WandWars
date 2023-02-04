package com.ben.wandwars.commands.wandsCommand;

import com.ben.wandwars.wands.Wands;
import com.ben.wandwars.wands.wandInterfaces.Wand;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class WandsInventoryListener implements Listener {
    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {




        Player player;

        if (event.getWhoClicked() instanceof Player) {
            player = (Player) event.getWhoClicked();
        } else {
            System.out.println("someone in who clicked on this inventory was not a player");
            return;
        }

        if(player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }

        ItemStack item = event.getCurrentItem();
        if (item == null) return;

        Wand wand = Wands.getWand(item);
        if (wand == null) {
            return;
        }


        if(event.getView().getTitle().equals(WandsCommand.getInventoryName()) && event.getClickedInventory().equals(event.getView().getTopInventory())) {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);
            player.getInventory().setItem(0, wand.getItem());
        }

    }

    @EventHandler
    private void OnPlayerDrop(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        if(item == null) return;

        Wand wand = Wands.getWand(item);
        if(wand == null) return;

        event.setCancelled(true);
    }

}
