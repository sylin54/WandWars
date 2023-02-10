package com.ben.wandwars.helpers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerHelper {
    public static List<Player> convertListToPlayer(List<UUID> players) {
        List<Player> returnValue = new ArrayList<>();

        for(UUID uuid : players) {
            returnValue.add(Bukkit.getPlayer(uuid));
        }

        return returnValue;
    }
}
