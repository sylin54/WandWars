package com.ben.wandwars.game;

import com.ben.wandwars.Main;
import com.ben.wandwars.game.maps.Map;
import com.ben.wandwars.game.maps.MapManager;
import com.ben.wandwars.game.util.GameTeam;
import com.ben.wandwars.game.util.countDown.CountDownUtil;
import com.ben.wandwars.game.util.countDown.CountDownUtilCallback;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

//this is a bit of a bad class. The queueing of the map especially has to be reworked. WIll need ot look at that later.
//the start game function cannot be performed at any delay from creating hte class, otherwise the map will not be unqueued.
public class Game {
    //find map
    //choose wand
    //teleport to map
    //countdown
    //game
    //keep going until score is settled
    //end

    private MapManager mapManager = MapManager.getInstance();
    private GameManager gameManager = GameManager.getInstance();
    private GameType gameType;
    private Map map;
    private Phase phase;
    private List<GameTeam> teams;
    private CountDownUtil countDownUtil;

    public Game(List<GameTeam> teams, GameType gameType, Map map) {
        this.gameType = gameType;
        this.map = map;
        this.teams = teams;


        mapManager.queueMap(map);

        phase = Phase.COUNTDOWN;

        countDownUtil = new CountDownUtil(4, 3, 3, new CountDownUtilCallback() {
            @Override
            public void call(Player player) {

            }

            @Override
            public void callOnce() {
                nextPhase();
            }
        });
        map.setSpawnPoints(teams);

    }

    public void startGame() {
        countDownUtil.start(GameTeam.getPlayers(teams));
        map.teleportToSpawnPoints();
    }

    private void nextPhase() {
        switch(phase) {

            case GAME:
                endRound();
                break;

            default:
                startRound();
                break;
        }
    }

    private void endRound() {
        GameTeam winnerTeam = GameTeam.getWinner(teams);

        winnerTeam.addWin();
        if(winnerTeam.isWin(gameType.getNeededWins())) {

            phase = Phase.ENDING;

            for(Player player : GameTeam.getPlayers(teams)) {
                player.sendMessage(ChatColor.RED + "Game is over! " + winnerTeam.getName() + " is the winner!");
            }

            new BukkitRunnable() {

                @Override
                public void run() {
                    for(Player player : GameTeam.getPlayers(teams)) {
                        player.teleport(new Location(Bukkit.getWorld("world"), 0, 0, 0));
                    }
                }
            }.runTaskLater(Main.getInstance(), 5 * 20);
            gameManager.endGame(this);
        }
    }

    private void startRound() {
        phase = Phase.GAME;
    }

    public void handleDeath(Player player) {
        GameTeam.killPlayer(player, teams);

        if(GameTeam.hasWinner(teams)) {
            nextPhase();
        }
    }

    public boolean hasPlayer(Player player) {
        return GameTeam.hasPlayer(player, teams);
    }

    public enum Phase {
        WAND_CHOOSE,
        COUNTDOWN,
        GAME,
        ENDING;
    }
}
