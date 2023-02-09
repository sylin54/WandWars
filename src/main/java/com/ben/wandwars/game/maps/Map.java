package com.ben.wandwars.game.maps;


import com.ben.wandwars.game.util.LocationUtil;
import org.bukkit.Location;

import java.util.List;

public class Map {

    private int templateID;
    private Location cornerLocation;

    public Map(int templateID, Location cornerLocation) {
        this.templateID = templateID;
        this.cornerLocation = LocationUtil.convertToMapLoc(cornerLocation);
    }

    public int getTemplateID() {
        return templateID;
    }

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
