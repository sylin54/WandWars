package com.ben.wandwars.wands.listeners;

import com.ben.wandwars.Main;
import com.ben.wandwars.displaying.TitleWarnings;
import com.ben.wandwars.helpers.scheduling.CoolDownManager;
import com.ben.wandwars.spells.components.blockHit.managers.ManaManager;
import com.ben.wandwars.wands.Wands;
import com.ben.wandwars.wands.wandInterfaces.Wand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class WandListener implements Listener {
    Main main;

    //the cooldown of the wand ability
    CoolDownManager wandAbilityCooldown = new CoolDownManager(500);

    //the small cooldown between the wand right clikcs and the wands mana manager
    CoolDownManager rightClickWandCooldown = new CoolDownManager(500);
    CoolDownManager leftClickWandCooldown = new CoolDownManager(250);
    ManaManager manaManager;
    public WandListener(Main main) {
        this.main = main;
        this.manaManager = ManaManager.getInstance();
    }

    @EventHandler
    private void onPlayerOffHand(PlayerSwapHandItemsEvent event) {
        Wand wand = Wands.getWand(event.getOffHandItem());

        if(wand == null) return;

        //from here I have the wand and know it is legit
        event.setCancelled(true);

        Player player = event.getPlayer();

        //if the cooldown is free cast
        if(manaManager.offsetMana(player, -wand.getAbilityMana())) {
            if(wandAbilityCooldown.isFree(player)){
                wand.abilityCast(player, main);
                wandAbilityCooldown.resetCoolDown(player);
            }
        }else {
            TitleWarnings.sendManaWarning(player, manaManager.getMana(player), wand.getAbilityMana());
        }
    }


    //handles the logic for detecting wands
    @EventHandler
    private void OnPlayerClick(PlayerInteractEvent event) {

        //making sure the interacted item is a wand
        if(event.getAction() == Action.PHYSICAL) {return;}

        Wand wand = Wands.getWand(event.getItem());
        if(wand == null) return;

        event.setCancelled(true);

        //variable initialization
        Player player = event.getPlayer();

        //this is the code block with the false positive
        if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            //make sure the player doesn't have a cooldown
            if(!leftClickWandCooldown.isFree(player)) return;
            leftClickWandCooldown.resetCoolDown(player);
            //make sure there is sufficent mana
            if(manaManager.offsetMana(player , -wand.getLeftManaUsage())) {
                wand.leftClickCast(player, main);
            } else{
                TitleWarnings.sendManaWarning(player, manaManager.getMana(player), wand.getLeftManaUsage());
            }

        }

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            //make sure the player doesn't have a cooldown
            if(!rightClickWandCooldown.isFree(player)) return;
            //make sure the player has sufficient mana
            if(manaManager.offsetMana(player, -wand.getRightClickManaUsage())) {
                wand.rightClickCast(player, main);
            } else {
                TitleWarnings.sendManaWarning(player, manaManager.getMana(player), wand.getRightClickManaUsage());
            }
        }
    }


}
