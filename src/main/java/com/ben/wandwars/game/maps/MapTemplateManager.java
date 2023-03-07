package com.ben.wandwars.game.maps;

import com.ben.wandwars.game.GameType;
import com.ben.wandwars.game.util.LocationUtil;
import org.bukkit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//handles the creation of new maps from templates
public class MapTemplateManager {
    public static final int mapHeight = 100;

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

////        //does a runnable to avoid crashes.
//        new BukkitRunnable() {
//            int y = 0;
//            int z = 0;
//            int x = 0;
//
//            int blocksPlaced = 1;
//            @Override
//            public void run() {
//
//
//                for(int p = 0; p < blocksPlaced; p++) {
//                    for(int i = 0; i < blocksPlaced; i++) {
//
//                        x++;
//                        z++;
//
////                        System.out.println("x: " + x + " y: " + y + " z: " );
//                        Material blockType = mapTemplate.getBlock(x, y, z).getType();
//
//                        Location newBlockLoc = finalBoxLoc.add(x, y, z);
//
//                        newBlockLoc.getBlock().setType(blockType);
//                    }
//                }
//
//                if(z == 100) {
//                    z = 0;
//                }
//
//                if(x == 100) {
//                    x = 0;
//                }
//
//                y++;
//
//                if(!(y < mapHeight)) {
//                    cancel();
//                }
//            }
//        }.runTaskTimer(Main.getInstance(), 20, 20);
//

//        code without crash prevention.


        for(int y = 0; y <= mapHeight; y++) {
            for(int z = 0; z < 100; z++) {
                for(int x = 0; x < 100; x++) {

                }
            }
        }



        copyChunk(mapTemplate.getCornerLocation().getChunk(), boxLoc.getChunk());

    }

    public static void copyChunk(Chunk chunk1, Chunk chunk2) {

        for(int y = 0; y < mapHeight; y++) {
            for (int z = 0; z < 16; z++) {
                for (int x = 0; x < 16; x++) {
                    chunk2.getBlock(x, y, z).setType(chunk1.getBlock(x, y, z).getType());


                    Bukkit.broadcastMessage("B1: " + chunk1.getBlock(x, y, z).getLocation());
                    Bukkit.broadcastMessage("B2 " + chunk2.getBlock(x, y, z).getLocation());
                    Bukkit.broadcastMessage("x: " + x + " y: " + y + " z: " + z );
                }
            }
        }
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
    public void addTemplate(Location location, GameType gameType, List<Location> spawns) {

        location = LocationUtil.convertToMapLoc(location);

        for(MapTemplate testMapTemplate : mapTemplates) {
            if(testMapTemplate.getCornerLocation().equals(location)) {
                mapTemplates.remove(testMapTemplate);
            }
        }

        List<List<Location>> locations = new ArrayList<>();
        int spawnIndex = 0;
        for(int i = 0; i < gameType.getTeamLengths().length; i++) {

            locations.add(new ArrayList<>());

            for(int x = 0; x < gameType.getTeamLengths()[i]; x++) {
                locations.get(x).add(spawns.get(spawnIndex));

                spawnIndex++;
            }
        }

        mapTemplates.add(new MapTemplate(location, gameType, mapTemplates.size(), locations));
    }

    public MapTemplate getTemplate(int id) {
        return mapTemplates.get(id);
    }

    //singleton stuff
    private static MapTemplateManager instance;
    public static MapTemplateManager getInstance() {

        if(instance == null) {
            instance = new MapTemplateManager();
        }

        return instance;
    }
    private MapTemplateManager() {

        List<Location> locations = new ArrayList<>();
        locations.add(new Location(Bukkit.getWorld("world"), 2, 2,2));
        locations.add(new Location(Bukkit.getWorld("world"), 20, 2,2));

        addTemplate(new Location(Bukkit.getWorld("world"), 1, 1, 1), GameType.SOLOS, locations);
    }

}
