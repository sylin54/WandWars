package com.sylin.spellsystem.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

public class LocationUtil {
    public static Location convertToMapLoc(Location location) {

        double x = location.getX();
        x = Math.round(Math.ceil(x/100)) * 100;

        double y = location.getY();
        y = Math.round(Math.ceil(y/100)) * 100;

        double z = location.getZ();
        z = Math.round(Math.ceil(z/100)) * 100;

        return new Location(location.getWorld(), x, y, z);
    }

    public static String locToString(Location location) {
        String returnValue = "";

        returnValue += location.getWorld().getUID() + " ";
        returnValue += location.getX() + " ";
        returnValue += location.getY() + " ";
        returnValue += location.getZ();

        return returnValue;
    }

    public static Location locFromString(String string) {

        String[] args = string.split(" ");

        World world = Bukkit.getWorld(UUID.fromString(args[0]));
        double x = Double.parseDouble(args[1]);
        double y = Double.parseDouble(args[2]);
        double z = Double.parseDouble(args[3]);

        return new Location(world, x, y, z);
    }
}
