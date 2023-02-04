package com.ben.wandwars.wands.wandInterfaces;

import org.bukkit.Material;

import java.util.HashMap;

//this is used when a wand is craftable
public interface WandCraftable {
    //gets the crafting id. do it in the format name_name_recipe
    String getCraftingID();

    //gets the crafting reference to convert chars to materials
    HashMap<Character, Material> getCraftingReference();

    //gets the recipe
    String[] getRecipe();
}
