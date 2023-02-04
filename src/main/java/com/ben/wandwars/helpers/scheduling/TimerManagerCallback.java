package com.ben.wandwars.helpers.scheduling;

import org.bukkit.entity.Player;

public abstract class TimerManagerCallback {

    boolean cancelled = false;

    public abstract void tick(long time, Player player);
    public abstract void end(Player player);


    protected void cancel() {
        cancelled = true;
    }

    public boolean isCancelled() {return cancelled;}
}
