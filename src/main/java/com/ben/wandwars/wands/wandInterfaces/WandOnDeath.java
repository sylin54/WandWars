package com.ben.wandwars.wands.wandInterfaces;

import com.ben.wandwars.Main;
import org.bukkit.entity.Player;

//this is used when a wand has an on death effect
public interface WandOnDeath {
    void onDeath(Player player, Main main, Player killer);
}
