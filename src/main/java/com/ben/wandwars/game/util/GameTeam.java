package com.ben.wandwars.game.util;

import com.ben.wandwars.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameTeam {
    List<UUID> alivePlayers;
    List<UUID> deadPlayers;

    int score;
    String name;

    public GameTeam(List<UUID> players, String name) {
        this.alivePlayers = players;
        this.deadPlayers = new ArrayList<>();
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }
    public void addWin() {
        score++;
    }

    public boolean isWin(int neededScore) {
        return score >= neededScore;
    }

    //sets the chosen player as dead
    public boolean killPlayer(Player player) {
        return killPlayer(player.getUniqueId());
    }

    //sets the chosen player as dead
    public boolean killPlayer(UUID player) {
        if(!alivePlayers.contains(player)) {
            return false;
        }

        alivePlayers.remove(player);
        deadPlayers.add(player);

        return true;
    }

    //sets all the players to respawn
    public void respawnPlayers() {
        for(UUID deadPlayer : deadPlayers) {
            alivePlayers.add(deadPlayer);
            deadPlayers.remove(deadPlayer);
        }
    }

    //returns whether the team is fully dead or not
    public boolean isDead() {
        return alivePlayers.isEmpty();
    }

    public boolean hasPlayer(UUID player) {
        if(alivePlayers.contains(player)) {
            return true;
        }
        return deadPlayers.contains(player);
    }

    public List<Player> getPlayers() {
        List<Player> returnValue = new ArrayList<>();

        for(UUID uuid : alivePlayers) {
            returnValue.add(Bukkit.getPlayer(uuid));
        }

        for(UUID uuid : deadPlayers) {
            returnValue.add(Bukkit.getPlayer(uuid));
        }

        return returnValue;
    }

    //returns the winner of the list of teams, or null if none
    public static GameTeam getWinner(List<GameTeam> teams) {
        GameTeam wonTeam = null;

        for(GameTeam team : teams) {
            if(team.isDead()) continue;

            if(wonTeam != null) {
                return null;
            }

            wonTeam = team;
        }

        return wonTeam;
    }

    public static boolean hasWinner(List<GameTeam> teams) {
        return getWinner(teams) != null;
    }

    //gets all the players from the list of teams
    public static List<Player> getPlayers(List<GameTeam> gameTeams) {
        List<Player> returnValue = new ArrayList<>();

        for(GameTeam gameTeam : gameTeams) {
            returnValue.addAll(gameTeam.getPlayers());
        }

        return returnValue;
    }

    public static boolean killPlayer(Player player, List<GameTeam> teams) {
        for(GameTeam gameTeam : teams) {
            if(gameTeam.killPlayer(player)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasPlayer(Player player, List<GameTeam> teams) {

        UUID uuid = player.getUniqueId();

        for(GameTeam gameTeam : teams) {
            if(gameTeam.hasPlayer(uuid)) {
                return true;
            }
        }

        return false;
    }
}
