package com.ben.wandwars;

import com.ben.wandwars.wands.Wand;
import com.ben.wandwars.wands.Wands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.UUID;

public class DeathMessageHandling implements Listener {
    HashMap<UUID, String> lastWandMap = new HashMap<>();
    HashMap<UUID, UUID> lastPlayerMap = new HashMap<>();

    private static DeathMessageHandling instance;

    protected DeathMessageHandling() {}

    public void setLastKiller(UUID victimUUID, UUID killerUUID) {
        Wand wand = Wands.getWand(Bukkit.getPlayer(killerUUID).getInventory().getItemInMainHand());

        if(wand == null) return;


        if(lastPlayerMap.containsKey(victimUUID)) {
            lastPlayerMap.replace(victimUUID, killerUUID);
        } else {
            lastPlayerMap.put(victimUUID, killerUUID);
        }

        if(lastWandMap.containsKey(killerUUID)) {
            lastWandMap.replace(killerUUID, wand.getItem().getItemMeta().getDisplayName());
        } else {
            lastWandMap.put(killerUUID, wand.getItem().getItemMeta().getDisplayName());
        }
    }

    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if(player == null) {
            System.out.println("error in the deathmessagehandling>onPlayerDeath");
            return;
        }
        UUID uuid = player.getUniqueId();

        event.setKeepInventory(false);
        event.getDrops().clear();
        event.setDroppedExp(0);

        if(lastWandMap.containsKey(uuid) || lastPlayerMap.containsKey(uuid)) {
            event.setDeathMessage(player.getDisplayName() + " died to " + Bukkit.getPlayer(lastPlayerMap.get(uuid)).getDisplayName() + " using the [" + lastWandMap.get(uuid) + "]");
        }

        lastPlayerMap.remove(player.getUniqueId());
        lastWandMap.remove(player.getUniqueId());
    }

    public static DeathMessageHandling getInstance() {
        if(instance == null) {
            instance = new DeathMessageHandling();
        }

        return instance;
    }
}
