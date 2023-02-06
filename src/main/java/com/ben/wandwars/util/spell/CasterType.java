package com.ben.wandwars.util.spell;

import com.ben.wandwars.spell.managers.SpellManager;
import org.bukkit.Bukkit;

import java.util.UUID;

public enum CasterType {
    PLAYER,
    SPELL,
    //if the castertype is unkown, then it probably used to a be spell that is now deceased, or a player that has logged of.
    UNKNOWN;

    public boolean isPlayer() {
        return this.equals(PLAYER);
    }

    public boolean isSpell() {
        return this.equals(SPELL);
    }

    public static CasterType getCasterType(UUID casterID) {
        if(Bukkit.getPlayer(casterID) != null) {
            return PLAYER;
        }

        if(SpellManager.getInstance().getSpell(casterID) != null) {
            return SPELL;
        }

        return UNKNOWN;
    }
}
