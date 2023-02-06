package com.ben.wandwars.stateManagers;

import com.ben.wandwars.displaying.Displayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class DashStateManager {

    private HashMap<UUID, Boolean> dashStateMap = new HashMap<>();

    protected static DashStateManager instance;

    public boolean hasDash(Player player) {
        if(dashStateMap.containsKey(player.getUniqueId())) {
            return dashStateMap.get(player.getUniqueId());
        }
        return true;
    }

    public void setDash(Player player, boolean dash) {
        if(dashStateMap.containsKey(player.getUniqueId())) {
            dashStateMap.replace(player.getUniqueId(), dash);
        } else {
            dashStateMap.put(player.getUniqueId(), dash);
        }

        Displayer.update(player);
    }

    public static DashStateManager getInstance() {
        if(instance == null) {
            instance = new DashStateManager();
        }

        return instance;
    }
}
