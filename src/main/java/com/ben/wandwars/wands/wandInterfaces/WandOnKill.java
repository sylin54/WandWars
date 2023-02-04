package com.ben.wandwars.wands.wandInterfaces;

import com.ben.wandwars.Main;
import org.bukkit.entity.Player;

//this is used when a wand has an on kill effect
public interface WandOnKill {
    void onKill(Player killer, Player slain, Main main);
}
