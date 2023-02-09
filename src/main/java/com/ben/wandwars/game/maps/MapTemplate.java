package com.ben.wandwars.game.maps;

import com.ben.wandwars.game.util.LocationUtil;
import org.bukkit.Location;


import java.util.List;

public class MapTemplate {

    private List<Location> redSpawns;
    private List<Location> blueSpawns;
    private Location cornerLocation;

    public MapTemplate(List<Location> redSpawns, List<Location> blueSpawns, Location location) {
        this.redSpawns = redSpawns;
        this.blueSpawns = blueSpawns;

        cornerLocation = LocationUtil.convertToMapLoc(location);
    }

    public List<Location> getRedSpawns() {
        return redSpawns;
    }

    public List<Location> getBlueSpawns() {
        return blueSpawns;
    }

    public Location getCornerLocation() {
        return cornerLocation;
    }
}
