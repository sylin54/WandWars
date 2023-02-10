package com.ben.wandwars.game;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class QueueManager {
    //stores a list of players to gametype.
    //has to be ordered from least to greatest, so multiple arraylists.
    //

    HashMap<GameType, ArrayList<UUID>> queueList;

    public QueueManager() {
        this.queueList = initQueueList();
    }

    public void addPlayer(Player player, GameType gameType) {
        queueList.get(gameType).add(player.getUniqueId());
    }

    //checks if there are enough players in a queue, if so it clears them and starts the game.
    private void checkQueue(GameType gameType) {
        int players = queueList.get(gameType).size();

        if(players < gameType.getPlayers()) {
            return;
        }

        //this is a possible source of error.
        queueList.get(gameType).clear();
    }

    //initializes the queue list for the first time.
    private HashMap<GameType, ArrayList<UUID>> initQueueList() {

        HashMap<GameType, ArrayList<UUID>> returnValue = new HashMap<>();

        for(GameType gameType : GameType.values()) {
            returnValue.put(gameType, new ArrayList<>());
        }

        return returnValue;
    }
}
