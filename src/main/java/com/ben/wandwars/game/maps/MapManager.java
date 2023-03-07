package com.ben.wandwars.game.maps;

import com.ben.wandwars.game.GameType;
import com.ben.wandwars.game.util.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapManager {

    //maps that aren't being queued.
    private List<Map> activeMaps = new ArrayList<>();
    //maps that are currently being used.
    private List<Map> queuedMaps = new ArrayList<>();

    Random random = new Random();

    //adds a map to the usable maps.
    public void addMap(MapTemplate mapTemplate, Location location) {

        System.out.println("adding map");
        location = LocationUtil.convertToMapLoc(location);

        Map map = new Map(mapTemplate.getId(), location);

        activeMaps.add(map);

        MapTemplateManager.getInstance().loadTemplate(0, location);

    }

    //gets an available map from the mapID
    public Map getMap(int mapID) {

        for(Map map : activeMaps) {
            if(mapID == map.getTemplateID()) {
                return map;
            }
        }

        return null;
    }

    public boolean hasAvailableMap(int mapID) {
        for(Map map : activeMaps) {
            if(map.getTemplateID() == mapID) return true;
        }

        return false;
    }

    //gets an available map
    public Map getRandomMap(GameType gameType) {
        for(int i = 0; i < 10000; i++) {


            int mapID = MapTemplateManager.getInstance().getRandomID(gameType);
            Bukkit.broadcastMessage("Map id: " + mapID);
            if(hasAvailableMap(mapID)) {
                return getMap(mapID);
            }
        }

        return null;
    }

    public boolean queueMap(Map map) {
        if(!activeMaps.remove(map)) {
            return false;
        }

        queuedMaps.add(map);

        return true;
    }

    public boolean unQueueMap(Map map) {
        if(!queuedMaps.remove(map)) {
            return false;
        }

        map.clearSpawnPoints();
        activeMaps.add(map);

        return true;
    }

    //singleton stuff:
    private static MapManager instance;
    public static MapManager getInstance() {

        if(instance == null) {
            instance = new MapManager();
        }

        return instance;
    }
    private MapManager() {
    }
}
