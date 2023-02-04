package com.ben.wandwars.helpers.scheduling;

import com.ben.wandwars.Main;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CoolDownManager {
    int coolDownDurationMillis;
    private final Cache<UUID, Long> coolDown;
    private final HashMap<UUID, Integer> taskMap = new HashMap<>();

    private CoolDownManagerCallback callback;

    public CoolDownManager(int coolDownDurationMillis) {
        this.coolDownDurationMillis = coolDownDurationMillis;
        //buils the cache according to the cooldown duration
        coolDown = CacheBuilder.newBuilder().expireAfterWrite(coolDownDurationMillis, TimeUnit.MILLISECONDS).build();
    }

    public CoolDownManager(int coolDownDurationMillis, CoolDownManagerCallback callback) {
        this.coolDownDurationMillis = coolDownDurationMillis;
        this.coolDown = CacheBuilder.newBuilder().expireAfterWrite(coolDownDurationMillis, TimeUnit.MILLISECONDS).build();
        this.callback = callback;

    }


    public void resetCoolDown(UUID uuid) {
        coolDown.asMap().remove(uuid);
        coolDown.put(uuid, System.currentTimeMillis() + coolDownDurationMillis);
        resetCallback(uuid);
    }

    public void resetCoolDown(LivingEntity livingEntity) {
        resetCoolDown(livingEntity.getUniqueId());
    }

    //starts a new callback and cancels the old one(if any)
    private void resetCallback(UUID uuid) {
        if(callback == null) return;

        //cancels the old callback
        if(taskMap.containsKey(uuid)) {
            int taskID = taskMap.get(uuid);
            Bukkit.getScheduler().cancelTask(taskID);
            taskMap.remove(uuid);
        }

        //convert the cooldown duration to ticks
        long delay = coolDownDurationMillis;
        delay = TimeUnit.MILLISECONDS.toSeconds(delay);
        delay *= 20;

        //starts a new callback and puts it in the task id map
        BukkitTask bukkitTask = Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                callback.run(Bukkit.getPlayer(uuid));
                taskMap.remove(uuid);
            }
        }, delay);

        taskMap.put(uuid, bukkitTask.getTaskId());
    }

    public long getCoolDownDuration(UUID uuid) {
        long distance = coolDown.asMap().get(uuid) - System.currentTimeMillis();
        return distance;
    }

    public long getCoolDownDuration(LivingEntity livingEntity) {
        return getCoolDownDuration(livingEntity.getUniqueId());
    }


    public boolean isFree(UUID uuid) {
        return !coolDown.asMap().containsKey(uuid);
    }
    public boolean isFree(LivingEntity livingEntity) {return !coolDown.asMap().containsKey(livingEntity.getUniqueId());}
}
