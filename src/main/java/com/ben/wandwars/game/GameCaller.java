package com.ben.wandwars.game;

import com.ben.wandwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.List;


public class GameCaller implements Listener {

    GameManager gameManager = GameManager.getInstance();

    //delegates the player death event to running games. The getting of the list from game manager shouldn't be to mhuch of a performance loss, but might need lookign at if the performance is low.
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        List<Game> games = gameManager.getGames();
        for(Game game : games) {
            if(game.hasPlayer(player)) {
                game.handleDeath(player);
                break;
            }

        }
    }

    //singleton stuff
    private static GameCaller instance = new GameCaller();
    public static GameCaller getInstance() {return instance;}
    private GameCaller() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

}
