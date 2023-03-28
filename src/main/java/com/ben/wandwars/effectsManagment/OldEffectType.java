package com.ben.wandwars.effectsManagment;

import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum OldEffectType {
    SMOKED(ChatColor.DARK_GRAY + "Smoked", true, false, false, new PotionEffect[]{new PotionEffect(PotionEffectType.SLOW, 0, 2, true, false), new PotionEffect(PotionEffectType.BLINDNESS, 0, 2, true, false)}),
    FORTIFIED(ChatColor.GREEN + "Fortified", true, true, false, new PotionEffect[]{new PotionEffect(PotionEffectType.SLOW, 0, 2, true, false)}),

    GHOSTLY(ChatColor.DARK_PURPLE + "Ghostly", false, false, true,
            new PotionEffect[]{new PotionEffect(PotionEffectType.SPEED, 0, 2, true, false),
                    new PotionEffect(PotionEffectType.JUMP, 0, 2, true, false),
                    new PotionEffect(PotionEffectType.INVISIBILITY, 0, 1, true, false)}),
    NOURISHED(ChatColor.GOLD + "Nourished", false, false, false, new PotionEffect[]{
            new PotionEffect(PotionEffectType.SPEED, 0, 1)
    });


    private boolean stopsDash;
    private boolean stopsWandsCommand;
    private String name;

    private boolean removesOnDamage;
    private PotionEffect[] effects;

    OldEffectType(String name, boolean stopsDash, boolean stopsWandsCommand, boolean removesOnDamage, PotionEffect[] effects) {
        this.name = name;
        this.stopsDash = stopsDash;
        this.stopsWandsCommand = stopsWandsCommand;
        this.effects = effects;
        this.removesOnDamage = removesOnDamage;
    }

    public boolean stopsDash() {
        return stopsDash;
    }

    public boolean stopsWandsCommand() {
        return stopsWandsCommand;
    }

    public String getName() {
        return name;
    }

    public PotionEffect[] getEffects() {
        return effects;
    }

    public boolean removesOnDamage() {
        return removesOnDamage;
    }

}
