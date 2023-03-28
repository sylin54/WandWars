package com.ben.wandwars.effectsManagment;

import com.ben.wandwars.helpers.scheduling.CoolDownManagerCallback;
import com.ben.wandwars.helpers.scheduling.TimerManager;
import com.ben.wandwars.helpers.scheduling.TimerManagerCallback;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class OldEffectBundler {
    private int duration;
    final private OldEffectType effectType;

    final private UUID uuid;

    final private CoolDownManagerCallback onSecondDecrease;
    final private CoolDownManagerCallback onExpire;

    private boolean isRemoved = false;

    public OldEffectBundler(OldEffectType effectType, int duration, LivingEntity livingEntity, CoolDownManagerCallback onTick, CoolDownManagerCallback onExpire) {
        this.duration = duration;

        this.effectType = effectType;
        this.uuid = livingEntity.getUniqueId();

        this.onSecondDecrease = onTick;
        this.onExpire = onExpire;

        if(duration != 0) {

            TimerManager timerManager = new TimerManager(new TimerManagerCallback() {
                @Override
                public void tick(long time, Player player) {
                    if (isRemoved) {
                        cancel();
                    }

                    tick(time, player);
                }

                @Override
                public void end(Player player) {
                    onExpire.run(player);
                }
            });

            timerManager.resetTimer(livingEntity, duration);
        }
    }

    public void remove() {
        isRemoved = true;
        onExpire.run(Bukkit.getPlayer(uuid));
    }

    private void tick(int time, Player player) {
        duration = time;

        onSecondDecrease.run(player);
    }

    public OldEffect getEffect() {
        return new OldEffect(duration, effectType);
    }
}
