package com.ben.wandwars.util.spell;

import com.ben.wandwars.spell.managers.SpellManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SpellCaster {
    private UUID casterID;

    CasterType casterType;

    //only use htis constructor if you do not know the caster type when you instantiate the class.
    public SpellCaster(UUID casterID) {
        this.casterID = casterID;

    }


    public SpellCaster(UUID casterID, CasterType casterType) {
        this.casterID = casterID;
        this.casterType = casterType;
    }

    public CasterType getCasterType() {
        return casterType;
    }

    //gets the caster for the first time, is stored into a variable afterwards since this is a  slightly memory intensive proccess.
    private CasterType initCasterType(UUID casterID) {
        if(Bukkit.getPlayer(casterID) != null) {
            return CasterType.PLAYER;
        }

        if(SpellManager.getInstance().getSpell(casterID) != null) {
            return CasterType.SPELL;
        }

        return CasterType.UNKNOWN;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(casterID);
    }
}
