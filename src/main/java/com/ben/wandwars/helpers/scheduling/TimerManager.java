package com.ben.wandwars.helpers.scheduling;

import com.ben.wandwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.UUID;

public class TimerManager {

    private final TimerManagerCallback timerManagerCallback;

    private final HashMap<UUID, Long> timeMap = new HashMap<>();
    private final HashMap<UUID, Integer> taskMap = new HashMap<>();

    public TimerManager(TimerManagerCallback timerManagerCallback) {
        this.timerManagerCallback = timerManagerCallback;
    }


    /*
    needs:
    must be able to accept a time in seconds and go that long
    must be able to do a callback every second and an ending callback
    must be able to
     */
    public void resetTimer(LivingEntity livingEntity, long timeSec) {

        final UUID uuid = livingEntity.getUniqueId();
        cancelTask(uuid);

        int taskID = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
            IncrementMapTime(uuid);
            if(timerManagerCallback.isCancelled()) {
                cancelTask(uuid);
            }
         }, 20, 20).getTaskId();


        timeMap.put(uuid, timeSec);
        taskMap.put(uuid, taskID);

        IncrementMapTime(livingEntity.getUniqueId());
    }

    public long getTime(LivingEntity livingEntity) {
        return timeMap.get(livingEntity.getUniqueId());
    }

    public long getTime(UUID uuid) {
        return timeMap.get(uuid);
    }


    private void IncrementMapTime(UUID uuid) {

        if(timeMap.get(uuid) == 1) {
            cancelTask(uuid);
        } else {

            if(timerManagerCallback.isCancelled()) {
                cancelTask(uuid);
                return;
            }
            timeMap.replace(uuid, timeMap.get(uuid) - 1);
            timerManagerCallback.tick(timeMap.get(uuid), Bukkit.getPlayer(uuid));
        }
    }

    private void cancelTask(UUID uuid) {

        timerManagerCallback.end(Bukkit.getPlayer(uuid));
        Bukkit.getScheduler().cancelTask(taskMap.get(uuid));

        taskMap.remove(uuid);
        timeMap.remove(uuid);
    }
}
