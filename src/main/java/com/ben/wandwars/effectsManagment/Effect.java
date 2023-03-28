package com.ben.wandwars.effectsManagment;

import org.bukkit.potion.PotionEffect;

import java.util.List;
import java.util.UUID;

public abstract class Effect {
    private long timeTicks;
    private UUID playerID;

    public Effect(UUID playerID) {
        this.timeTicks = getMaxTime();
        this.playerID = playerID;
    }

    protected void suicide() {
        EffectsManager.getInstance().removeEffect(playerID, getID());
    }

    public long getTimeSeconds(){
        return timeTicks /60;
    }
    public long getTimeTicks() {
        return timeTicks;
    }
    public void offsetTimeTicks(int offset){
        timeTicks += offset;
    }
    public abstract List<PotionEffectNoDur> getPotionEffects();
    public abstract int getID();
    public abstract long getMaxTime();
    public abstract String getName();
}
