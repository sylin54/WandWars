package com.ben.wandwars.wands.listeners;

import com.ben.wandwars.wands.Wands;
import com.ben.wandwars.displaying.Displayer;
import com.ben.wandwars.displaying.TitleWarnings;
import com.ben.wandwars.helpers.scheduling.CoolDownManager;
import com.ben.wandwars.helpers.scheduling.CoolDownManagerCallback;
import com.ben.wandwars.stateManagers.ManaManager;
import com.ben.wandwars.wands.Wand;
import com.ben.wandwars.wands.listeners.dash.DashStateManager;
import com.ben.wandwars.wands.listeners.dash.DashUtil;
import com.ben.wandwars.wands.listeners.dash.PlayerDashEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class WandListener implements Listener {

    //the cooldown of the wand ability
    CoolDownManager wandAbilityCooldown = new CoolDownManager(500);

    //the small cooldown between the wand right clikcs and the wands mana manager
    CoolDownManager rightClickWandCooldown = new CoolDownManager(500);
    CoolDownManager leftClickWandCooldown = new CoolDownManager(250);

    CoolDownManager dashCoolDown;

    public WandListener() {

        dashCoolDown = new CoolDownManager(2250, new CoolDownManagerCallback() {
            @Override
            public void run(Player target) {
                DashStateManager.getInstance().setDash(target, true);
                Displayer.update(target);
            }
        });
    }

    @EventHandler
    private void onPlayerOffHand(PlayerSwapHandItemsEvent event) {
        Wand wand = Wands.getWand(event.getOffHandItem());
        ManaManager manaManager = ManaManager.getInstance();

        if(wand == null) return;

        //from here I have the wand and know it is legit
        event.setCancelled(true);

        Player player = event.getPlayer();

        //if the cooldown is free cast
        if(!wand.getOffHandCastInf(player).isCastable()) return;
        if(manaManager.offsetMana(player, -wand.getOffHandCastInf(player).getManaUsage())) {
            if(wandAbilityCooldown.isFree(player)){
                wand.offHandCast(player);
                wandAbilityCooldown.resetCoolDown(player);
            }
        }else {
            TitleWarnings.sendManaWarning(player, manaManager.getMana(player), wand.getOffHandCastInf(player).getManaUsage());
        }
    }

    @EventHandler
    private void onPlayerDamage(EntityDamageByEntityEvent event) {
        if(!(event.getDamager() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getDamager();

        Wand wand = Wands.getWand(player.getInventory().getItemInMainHand());
        if(wand == null) return;

        event.setCancelled(true);

        ManaManager manaManager = ManaManager.getInstance();

        if(!player.isSneaking()) {
            if(!wand.getLeftClickCastInf(player).isCastable()) return;

            //make sure the player doesn't have a cooldown
            if(!leftClickWandCooldown.isFree(player)) return;
            leftClickWandCooldown.resetCoolDown(player);
            //make sure there is sufficent mana
            if(manaManager.offsetMana(player , -wand.getLeftClickCastInf(player).getManaUsage())) {
                wand.leftClickCast(player);
            } else{
                TitleWarnings.sendManaWarning(player, manaManager.getMana(player), wand.getLeftClickCastInf(player).getManaUsage());
            }
        } else {
            ShiftManager shiftManager = ShiftManager.getInstance();

            if(!wand.getShiftLeftClickCastInf(player).isCastable()) return;
            //make sure there is sufficent mana
            if(manaManager.offsetMana(player , -wand.getShiftLeftClickCastInf(player).getManaUsage())) {
                shiftManager.removeShift(player.getUniqueId());
                wand.shiftLeftClickCast(player);
            } else{
                TitleWarnings.sendManaWarning(player, manaManager.getMana(player), wand.getShiftLeftClickCastInf(player).getManaUsage());
            }
        }
    }



    //handles the logic for detecting wands
    @EventHandler
    private void OnPlayerClick(PlayerInteractEvent event) {

        ManaManager manaManager = ManaManager.getInstance();

        //making sure the interacted item is a wand
        if(event.getAction() == Action.PHYSICAL) {return;}

        Wand wand = Wands.getWand(event.getItem());
        if(wand == null) return;

        event.setCancelled(true);

        //variable initialization
        Player player = event.getPlayer();

        if(!player.isSneaking()) {
            if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {

                if(!wand.getLeftClickCastInf(player).isCastable()) return;

                //make sure the player doesn't have a cooldown
                if(!leftClickWandCooldown.isFree(player)) return;
                leftClickWandCooldown.resetCoolDown(player);
                //make sure there is sufficent mana
                if(manaManager.offsetMana(player , -wand.getLeftClickCastInf(player).getManaUsage())) {
                    wand.leftClickCast(player);
                } else{
                    TitleWarnings.sendManaWarning(player, manaManager.getMana(player), wand.getLeftClickCastInf(player).getManaUsage());
                }
            }

            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                //make sure the player doesn't have a cooldown

                if(!wand.getRightClickCastInf(player).isCastable()) return;

                if(!rightClickWandCooldown.isFree(player)) return;
                rightClickWandCooldown.resetCoolDown(player);
                //make sure the player has sufficient mana
                if(manaManager.offsetMana(player, -wand.getRightClickCastInf(player).getManaUsage())) {
                    wand.rightClickCast(player);
                } else {
                    TitleWarnings.sendManaWarning(player, manaManager.getMana(player), wand.getRightClickCastInf(player).getManaUsage());
                }
            }
        } else {

            ShiftManager shiftManager = ShiftManager.getInstance();

            if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {

                if(!wand.getShiftLeftClickCastInf(player).isCastable()) return;
                //make sure there is sufficent mana
                if(manaManager.offsetMana(player , -wand.getShiftLeftClickCastInf(player).getManaUsage())) {
                    shiftManager.removeShift(player.getUniqueId());
                    wand.shiftLeftClickCast(player);
                } else{
                    TitleWarnings.sendManaWarning(player, manaManager.getMana(player), wand.getShiftLeftClickCastInf(player).getManaUsage());
                }
            }

            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                //make sure the player doesn't have a cooldown

                if(!wand.getShiftRightClickCastInf(player).isCastable()) return;

                //make sure the player has sufficient mana
                if(manaManager.offsetMana(player, -wand.getShiftRightClickCastInf(player).getManaUsage())) {
                    shiftManager.removeShift(player.getUniqueId());
                    wand.shiftRightClickCast(player);
                } else {
                    TitleWarnings.sendManaWarning(player, manaManager.getMana(player), wand.getShiftRightClickCastInf(player).getManaUsage());
                }
            }
        }


        //this is the code block with the false positive
    }

    @EventHandler
    private void onShift(PlayerToggleSneakEvent event) {
        if(event.isSneaking()) return;

        Player player = event.getPlayer();

        //if the player doesn't have a in their hand return wand return
        if(Wands.getWand(player.getInventory().getItemInMainHand()) == null) {
            player.sendMessage(ChatColor.RED + "You must have a wand equip to dash!");
            return;
        }

        if(!ShiftManager.getInstance().canDash(event.getPlayer().getUniqueId())) {
            return;
        }

        //if the cooldown is free cast the dash
        if(dashCoolDown.isFree(player)) {

            //stuff to work with custom event
            PlayerDashEvent playerDashEvent =  new PlayerDashEvent(player);
            Bukkit.getPluginManager().callEvent(playerDashEvent);

            if(playerDashEvent.isCancelled()) {
                return;
            }

            dashCoolDown.resetCoolDown(player);

            DashUtil.spawnParticles(15, player.getLocation());
            DashUtil.dash(player);

            DashStateManager.getInstance().setDash(player, false);

        } else {
            TitleWarnings.sendDashWarning(player, dashCoolDown.getCoolDownDuration(player));
        }
    }


}
