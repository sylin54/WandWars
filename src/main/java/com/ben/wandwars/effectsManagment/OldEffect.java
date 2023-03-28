package com.ben.wandwars.effectsManagment;


public class OldEffect {
    private int duration;
    private OldEffectType effectType;

    public OldEffect(int duration, OldEffectType effectType) {
        this.duration = duration;
        this.effectType = effectType;
    }


    public int getDuration() {
        return duration;
    }

    public OldEffectType getType() {
        return effectType;
    }
}
