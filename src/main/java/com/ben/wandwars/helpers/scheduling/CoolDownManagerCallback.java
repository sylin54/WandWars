package com.ben.wandwars.helpers.scheduling;

import org.bukkit.entity.Player;

public interface CoolDownManagerCallback {
    void run(Player target);
}
