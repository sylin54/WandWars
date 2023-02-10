package com.ben.wandwars.game.maps;


import com.ben.wandwars.game.util.GameTeam;
import com.ben.wandwars.game.util.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

//stores spawn points to player, its map template for firther info, and the corner location of the map. needs to be cleared of data when it is done being used. this is handled by the map manager class.
public class Map {


    private HashMap<UUID, Location> spawnPoints = new HashMap<>();
    private int templateID;
    private Location cornerLocation;

    public Map(int templateID, Location cornerLocation) {
        this.templateID = templateID;
        this.cornerLocation = LocationUtil.convertToMapLoc(cornerLocation);
    }

    //this function would be outdated if mismatches were added; or if the matches were different
    //sets the players spawn point based on the teams given.
    public void setSpawnPoints(List<GameTeam> teams) {
        MapTemplate template = getMapTemplate();

        int[] teamLengths = template.getGameType().getTeamLengths();

        for(int i = 0; i < teamLengths.length; i++) {
            for(int x = 0; x < teamLengths[i]; x++) {
                spawnPoints.put(teams.get(i).getPlayers().get(x).getUniqueId(), template.getSpawns().get(i).get(x));
            }
        }
    }

    public void teleportToSpawnPoints() {
        for(UUID uuid : spawnPoints.keySet()) {
            Player player = Bukkit.getPlayer(uuid);

            player.teleport(spawnPoints.get(uuid));
        }
    }

    public void clearSpawnPoints() {
        spawnPoints.clear();
    }

    public int getTemplateID() {
        return templateID;
    }

    public MapTemplate getMapTemplate() {return MapTemplateManager.getInstance().getTemplate(templateID);}

    public Location getCornerLoc() {
        return cornerLocation;
    }

    public String toString() {
        return templateID + ":" + LocationUtil.locToString(cornerLocation);
    }

    public static Map fromString(String map) {
        String[] splitString = map.split(":");

        int templateID = Integer.parseInt(splitString[0]);
        Location cornerLocation = LocationUtil.locFromString(splitString[1]);

        return  new Map(templateID, cornerLocation);
    }
}
