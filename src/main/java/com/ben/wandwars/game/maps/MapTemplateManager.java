package com.ben.wandwars.game.maps;

import com.ben.wandwars.Main;
import com.ben.wandwars.game.GameType;
import com.ben.wandwars.game.util.LocationUtil;
import com.sun.org.apache.bcel.internal.generic.LOOKUPSWITCH;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//handles the creation of new maps from templates
public class MapTemplateManager {
    public static final int mapHeight = 100;

    private MapManager mapManager = MapManager.getInstance();
    private Random random = new Random();
    private List<MapTemplate> mapTemplates = new ArrayList<>();

    //spawns a template in the specified location.
    public void loadTemplate(int mapTemplateID, Location boxLoc) {
        Location finalBoxLoc = LocationUtil.convertToMapLoc(boxLoc);
        World world = boxLoc.getWorld();

        if(mapTemplates.size() < mapTemplateID) {
            throw new IndexOutOfBoundsException("a map template does not exist with that ID. ID: " + mapTemplateID);
        }

        MapTemplate mapTemplate = mapTemplates.get(mapTemplateID);

        //does a runnable to avoid crashes.
        new BukkitRunnable() {
            int y = 0;
            @Override
            public void run() {


                for(int z = 0; z < 100; z++) {
                    for(int x = 0; x < 100; x++) {
                        Material blockType = mapTemplate.getBlock(x, y, z).getType();

                        Location newBlockLoc = finalBoxLoc.add(x, y, z);

                        newBlockLoc.getBlock().setType(blockType);
                    }
                }

                y++;

                if(!(y < mapHeight)) {
                    cancel();
                }
            }
        }.runTaskTimer(Main.getInstance(), 1, 1);
        /*

        code without crash prevention.

        for(int y = 0; y <= mapHeight; y++) {
            for(int z = 0; z < 100; z++) {
                for(int x = 0; x < 100; x++) {
                    Material blockType = mapTemplate.getBlock(x, y, z).getType();

                    Location newBlockLoc = finalBoxLoc.add(x, y, z);

                    newBlockLoc.getBlock().setType(blockType);
                }
            }
        }
         */

        mapManager.addMap(mapTemplate, boxLoc);
    }

    //gets a random map ID from the pool of available map IDS
    public int getRandomID() {
        return random.nextInt(mapTemplates.size() + 1);
    }

    //prob need a better way to do this
    public int getRandomID(GameType gameType) {


        while (true) {
            MapTemplate mapTemplate = mapTemplates.get(random.nextInt(mapTemplates.size()));
            if(mapTemplate.getGameType().equals(gameType)) {
                return mapTemplate.getId();
            }
        }
    }

    //adds a templated to the map list. No duplicate locations.
    public void addTemplate(Location location, GameType gameType) {

        location = LocationUtil.convertToMapLoc(location);

        for(MapTemplate testMapTemplate : mapTemplates) {
            if(testMapTemplate.getCornerLocation().equals(location)) {
                mapTemplates.remove(testMapTemplate);
            }
        }

        mapTemplates.add(new MapTemplate(location, gameType, mapTemplates.size()));
    }

    public MapTemplate getTemplate(int id) {
        return mapTemplates.get(id);
    }

    //singleton stuff
    private static MapTemplateManager instance = new MapTemplateManager();
    public static MapTemplateManager getInstance() {
        return instance;
    }
    private MapTemplateManager() {
    }

}
