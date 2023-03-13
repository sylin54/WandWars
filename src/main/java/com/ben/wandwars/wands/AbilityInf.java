package com.ben.wandwars.wands;

public class AbilityInf {
    private int manaUsage;
    private long cooldown;
    private boolean castable;

    public AbilityInf(int manaUsage, long cooldown, boolean isSuccessful) {
        this.manaUsage = manaUsage;
        this.cooldown = cooldown;
        this.castable = isSuccessful;
    }

    public AbilityInf(int manaUsage, long cooldown) {
        this.manaUsage = manaUsage;
        this.cooldown = cooldown;
        castable = true;
    }

    public AbilityInf(int manaUsage) {
        this.manaUsage = manaUsage;
        cooldown = 0;
        castable = true;
    }

    public int getManaUsage() {
        return manaUsage;
    }

    public long getCooldown() {
        return cooldown;
    }

    public boolean isCastable() {
        return castable;
    }
}
