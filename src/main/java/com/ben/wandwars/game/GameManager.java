package com.ben.wandwars.game;

import com.ben.wandwars.game.maps.Map;
import com.ben.wandwars.game.maps.MapManager;
import com.ben.wandwars.game.util.GameTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameManager {

    private List<Game> games = new ArrayList<>();

    private MapManager mapManager = MapManager.getInstance();

    public boolean createGame(List<Player> players, GameType gameType) {

        Bukkit.broadcastMessage("created game");

        Map map = mapManager.getRandomMap(gameType);

        if(map == null) {
            Bukkit.broadcastMessage("no maps");
            return false;
        }

        int index = 0;

        List<GameTeam> teams = new ArrayList<>();

        for(int i = 0; i < gameType.getTeamLengths().length; i++) {
            List<UUID> teamPlayers = new ArrayList<>();
            Bukkit.broadcastMessage("next team");
            for(int x = 0; x < i; x++) {
                teamPlayers.add(players.get(0).getUniqueId());
                Bukkit.broadcastMessage(players.get(0).getDisplayName() + " joining team " + i);
                players.remove(0);
            }

            teams.add(new GameTeam(teamPlayers, getTeamName(0)));

        }

        Bukkit.broadcastMessage("created game");
        createGame(new Game(teams, gameType, mapManager.getRandomMap(gameType)));
        return true;
    }

    public void createGame(Game game) {
        games.add(game);
        game.startGame();
    }

    public boolean endGame(Game game) {
        return games.remove(game);
    }

    private String getTeamName(int teamNum) {
        switch(teamNum) {
            case 0:
                return ChatColor.RED + "red team";

            case 1:
                return ChatColor.BLUE + "blue team";

            case 2:
                return ChatColor.GREEN + "green team";

            case 3:
                return ChatColor.YELLOW + "yellow team";

            case 4:
                return ChatColor.GOLD + "gold team";

            case 5:
                return ChatColor.LIGHT_PURPLE + "purple team";
        }

        return null;
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
