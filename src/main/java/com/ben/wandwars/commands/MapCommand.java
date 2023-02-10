package com.ben.wandwars.commands;

import com.ben.wandwars.game.GameType;
import com.ben.wandwars.game.maps.MapManager;
import com.ben.wandwars.game.maps.MapTemplateManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MapCommand implements CommandExecutor {

    MapTemplateManager mapTemplateManager = MapTemplateManager.getInstance();
    MapManager mapManager = MapManager.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        if(args[0] == "create") {
            mapTemplateManager.addTemplate(player.getLocation(), GameType.getGameType(args[1]));
        } else {
            mapManager.addMap(mapTemplateManager.getTemplate(Integer.parseInt(args[1])), player.getLocation());
        }

        return true;
    }
}
