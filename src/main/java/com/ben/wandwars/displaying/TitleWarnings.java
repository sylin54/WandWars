package com.ben.wandwars.displaying;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TitleWarnings {
    //sends the player a warning of low mana.
    public static void sendManaWarning(Player player, Integer mana, Integer neededMana) {
        player.sendTitle(ChatColor.BLUE + mana.toString() +  " / " + neededMana.toString(), "", 3, 4 ,3);
    }

    //sends a player a warning of not having enough dash
    public static void sendDashWarning(Player player, long dashDurationMillis) {

        long longDuration = dashDurationMillis;
        longDuration = longDuration/100;

        float duration = longDuration;
        duration /= 10;

        player.sendTitle(ChatColor.YELLOW + Float.toString(duration), "", 3, 4 ,3);
    }

    //sends a warning to a player telling them they died
    public static void sendDeathWarning(Player player) {
        player.sendTitle(ChatColor.RED + "You died!", ChatColor.RED + "Better luck next time!", 6, 8, 6);
    }
}
