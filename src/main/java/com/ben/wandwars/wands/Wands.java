package com.ben.wandwars.wands;


import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

//this class stores an instance of all the wands.
// is used to get what wand is in a players hand and such.
// all methods are just about getting the wands or a spefific wnad
public class Wands {
    List<Wand> wands = new ArrayList<>();

    List<Wand> WIPWands = new ArrayList<>();
    private Wands() {
        //add the wands to the list
    }


    public static List<Wand> getWands() {
        Wands wands = new Wands();
        return wands.wands;
    }

    public static List<Wand> getWIPWands() {
        Wands wands = new Wands();
        return wands.getWIPWands();
    }

    //returns the wand gotten from the item
    public static Wand getWand(ItemStack wand) {

        Wands wands = new Wands();



        if(wand == null) {return null;}

        Wand returnValue = null;

        for(Wand loopingWand : wands.getWIPWands()) {
            if(loopingWand.getItem().isSimilar(wand)) {
                returnValue = loopingWand;
                break;
            }
        }

        return returnValue;
    }

}
