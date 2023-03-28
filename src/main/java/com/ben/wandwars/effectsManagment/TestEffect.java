package com.ben.wandwars.effectsManagment;

import org.bukkit.potion.PotionEffect;

import java.util.List;
import java.util.UUID;

public class TestEffect extends Effect{
    public TestEffect(UUID playerID) {
        super(playerID);
    }

    @Override
    public List<PotionEffectNoDur> getPotionEffects() {
        return null;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public long getMaxTime() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}
