package com.ben.wandwars.effectsManagment;


public class Effect {
    private int duration;
    private EffectType effectType;

    public Effect(int duration, EffectType effectType) {
        this.duration = duration;
        this.effectType = effectType;
    }


    public int getDuration() {
        return duration;
    }

    public EffectType getType() {
        return effectType;
    }
}
