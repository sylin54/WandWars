package com.ben.wandwars.commands;

import com.ben.wandwars.game.GameType;
import com.ben.wandwars.game.QueueManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QueueCommand implements CommandExecutor {

    QueueManager queueManager = QueueManager.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return false;
        }

        if (args.length < 1) {
            return false;
        }

        Player player = (Player) sender;
        GameType gameType = GameType.getGameType(args[0]);

        if (gameType == null) {
            return false;
        }

        queueManager.addPlayer(player, gameType);
        player.sendMessage(ChatColor.GREEN + "You have been queued into a " + gameType.getName() + " game.");

        return true;
    }
}
