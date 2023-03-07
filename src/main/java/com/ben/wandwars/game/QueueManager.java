package com.ben.wandwars.game;

import com.ben.wandwars.game.maps.MapManager;
import com.ben.wandwars.game.util.GameTeam;
import com.ben.wandwars.helpers.PlayerHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class QueueManager {
    //stores a list of players to gametype.
    //has to be ordered from least to greatest, so multiple arraylists.
    //

    private GameManager gameManager = GameManager.getInstance();
    private HashMap<GameType, ArrayList<UUID>> queueList;

    private QueueManager() {
        this.queueList = initQueueList();
    }

    public void addPlayer(Player player, GameType gameType) {
        queueList.get(gameType).add(player.getUniqueId());
        checkQueue(gameType);
    }

    //checks if there are enough players in a queue, if so it clears them and starts the game.
    public void checkQueue(GameType gameType) {
        int playersNum = queueList.get(gameType).size();

        Bukkit.broadcastMessage("players num " + playersNum);

        if(playersNum < gameType.getPlayers()) {
            Bukkit.broadcastMessage("players num wasn't enough");
            return;
        }

        List<Player> players = PlayerHelper.convertListToPlayer(queueList.get(gameType));


        //this is a possible source of error.
        queueList.get(gameType).clear();

        gameManager.createGame(players, gameType);
    }

    //initializes the queue list for the first time.
    private HashMap<GameType, ArrayList<UUID>> initQueueList() {

        HashMap<GameType, ArrayList<UUID>> returnValue = new HashMap<>();

        for(GameType gameType : GameType.values()) {
            returnValue.put(gameType, new ArrayList<>());
        }

        return returnValue;
    }

    //singleton stuff
    private static QueueManager instance = new QueueManager();
    public static QueueManager getInstance() {
        return instance;
    }
}
