package com.ben.wandwars.game.util.countDown;

import org.bukkit.entity.Player;

public interface CountDownUtilCallback {
    void call(Player player);

    void callOnce();
}
