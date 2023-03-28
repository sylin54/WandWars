package com.ben.wandwars.effectsManagment;


import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionEffectNoDur {

    private boolean ambient;

    private boolean particles;
    private int amplifier;
    private PotionEffectType potionEffectType;

    public PotionEffectNoDur(int amplifier, PotionEffectType potionEffectType) {
        this.amplifier = amplifier;
        this.potionEffectType = potionEffectType;

        this.ambient = false;
        this.particles = true;
    }

    public PotionEffectNoDur(int amplifier, PotionEffectType potionEffectType, boolean ambient, boolean particles) {
        this.amplifier = amplifier;
        this.potionEffectType = potionEffectType;
        this.ambient = ambient;
        this.particles = particles;
    }

    public void apply(Player player, int duration) {
        player.addPotionEffect(new PotionEffect(potionEffectType, duration, amplifier, ambient, particles));
    }

}