package com.ben.wandwars.commands;

import com.ben.wandwars.wands.Wands;
import com.ben.wandwars.wands.Wand;
import com.ben.wandwars.wands.items.ExplosionWand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            Wand wand = Wands.getWand(player.getInventory().getItemInMainHand());

            if(wand == null) return false;

            if(wand instanceof ExplosionWand) {
                player.sendMessage("explosion wand stats");
            }
        }

        return false;
    }
}
