package com.ben.wandwars.game;

import com.ben.wandwars.game.maps.Map;
import com.ben.wandwars.game.maps.MapManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private List<Game> games = new ArrayList<>();

    private MapManager mapManager = MapManager.getInstance();

    public boolean createGame(List<Player> players, GameType gameType) {
        Map map = mapManager.getRandomMap(gameType);

        if(map == null) {
            return false;
        }


    }

    public void endGame(Game game) {

    }

    public List<Game> getGames() {
        return games;
    }

    //singleton stuff:
    private static GameManager instance = new GameManager();
    public static GameManager getInstance() {
        return instance;
    }
    private GameManager() {

    }
}
