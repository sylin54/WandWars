package com.ben.wandwars.stateManagers;

import com.ben.wandwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerBarsManager {

    //handles the regen of bars. call this on sever start
    public static void initialize() {

        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
            for(Player player : Bukkit.getOnlinePlayers()) {
                ManaManager.getInstance().offsetMana(player, 3);
            }
        },0, 6);
    }
}
