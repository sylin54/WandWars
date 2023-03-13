package com.ben.wandwars.wands;

import com.ben.wandwars.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


//the main logic of a wand, this is an interface shared by all wands.


public abstract class Wand {
    //gets the item
    public abstract ItemStack getItem();


    //triggers on wand cast
    public abstract void leftClickCast(Player caster);

    //triggers on left click wand cast
    // void rightClickCast(Player caster, Main main);
    public abstract void rightClickCast(Player caster);
    public abstract void offHandCast(Player caster);
    public abstract void shiftLeftClickCast(Player caster);
    public abstract void shiftRightClickCast(Player caster);
    public abstract void dropKeyCast(Player caster);


    public abstract AbilityInf getLeftClickCastInf(Player caster);
    public abstract AbilityInf getRightClickCastInf(Player caster);
    public abstract AbilityInf getOffHandCastInf(Player caster);
    public abstract AbilityInf getShiftLeftClickCastInf(Player caster);
    public abstract AbilityInf getShiftRightClickCastInf(Player caster);
    public abstract AbilityInf getDropKeyCast(Player caster);
}
