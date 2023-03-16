package com.ben.wandwars.wands.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//if a player attacks while in shift they can't dash anymore. This manager keeps track of that.
public class ShiftManager implements Listener {
    private List<UUID> shiftedPlayers = new ArrayList<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerShift(PlayerToggleSneakEvent event) {
        if(event.isSneaking()) {
            shiftedPlayers.add(event.getPlayer().getUniqueId());
        } else {
            shiftedPlayers.remove(event.getPlayer().getUniqueId());
        }
    }

    public void removeShift(UUID uuid) {
        shiftedPlayers.remove(uuid);
    }

    public boolean canDash(UUID uuid) {
        return shiftedPlayers.contains(uuid);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        shiftedPlayers.remove(event.getPlayer().getUniqueId());
    }

    private static ShiftManager instance;

    public static ShiftManager getInstance() {
        if(instance == null) {
            instance = new ShiftManager();
        }

        return instance;
    }

    private ShiftManager() {}
}
