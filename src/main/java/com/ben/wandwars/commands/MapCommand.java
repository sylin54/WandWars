package com.ben.wandwars.commands;

import com.ben.wandwars.game.GameType;
import com.ben.wandwars.game.maps.MapManager;
import com.ben.wandwars.game.maps.MapTemplateManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MapCommand implements CommandExecutor {

    MapTemplateManager mapTemplateManager = MapTemplateManager.getInstance();
    MapManager mapManager = MapManager.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        if(args[0].equals("create")) {

            List<Location> locations = new ArrayList<>();
            locations.add(new Location(Bukkit.getWorld("world"), 1, 100, 1));
            locations.add(new Location(Bukkit.getWorld("world"), 1, 100, 1));

            mapTemplateManager.addTemplate(player.getLocation(), GameType.getGameType(args[1]), locations);
            System.out.println("created map");
        } else {

            mapManager.addMap(MapTemplateManager.getInstance().getTemplate(0), new Location(Bukkit.getWorld("world"), 1000, 100, 100));
        }

        return true;
    }
}
