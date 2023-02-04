package com.ben.wandwars.wands.wandInterfaces;

import com.ben.wandwars.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


//the main logic of a wand, this is an interface shared by all wands.
public abstract class Wand {
    //gets the item
    public abstract ItemStack getItem();

    //triggers on wand cast
    public abstract boolean leftClickCast(Player caster, Main main);

    //triggers on left click wand cast
    // void rightClickCast(Player caster, Main main);
    public abstract boolean rightClickCast(Player caster, Main main);

    public abstract boolean abilityCast(Player caster, Main main);

    //returns the mana usage
    public abstract int getLeftManaUsage();

    //returns the lift click mana usage
    public abstract int getRightClickManaUsage();

    public abstract int getAbilityMana();
}
