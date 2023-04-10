package com.ben.wandwars.commands.wandsCommand;

import com.ben.wandwars.wands.Wands;
import com.ben.wandwars.wands.Wand;
import com.ben.wandwars.wands.wandInterfaces.WandState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class WandsCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            Inventory inventory = Bukkit.createInventory(player, 54, getInventoryName());


            if(player.getGameMode() == GameMode.CREATIVE) {


                List<Wand> wands = Wands.getWIPWands();

                for (int i = 0; i < wands.size(); i++) {
                    Wand wand = wands.get(i);

                    if(wand == null) continue;

                    if(!(wand instanceof WandState)) {
                        inventory.setItem(i, wand.getItem());
                    } else {
                        i--;
                    }

                }

                player.openInventory(inventory);
            } else {
                List<Wand> wands = Wands.getWands();

                for (int i = 0; i < wands.size(); i++) {
                    Wand wand = wands.get(i);

                    if(wand == null) continue;

                    if(!(wand instanceof WandState)) {
                        inventory.setItem(i, wand.getItem());
                    } else {
                        i--;
                    }

                }

                player.openInventory(inventory);
            }

        }

        return false;
    }

    public static String getInventoryName() {return ChatColor.DARK_PURPLE + "Wands";}
}
