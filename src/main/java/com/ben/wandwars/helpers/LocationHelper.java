package com.ben.wandwars.helpers;

import org.bukkit.Location;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocationHelper {

    protected LocationHelper() {}

    public static Location getOffsetLocation(double offset, Location location) {
        return getOffsetLocation(offset, offset, offset, location);
    }

    public static Location getOffsetLocation(double offsetX, double offsetY, double offsetZ, Location location) {
        Random random = new Random();

        double x = random.nextDouble(offsetX * 2);
        x -= offsetX;
        x += location.getX();

        double y = random.nextDouble(offsetY * 2);
        y -= offsetY;
        y += location.getY();

        double z = random.nextDouble(offsetZ * 2);
        z -= offsetZ;
        z += location.getZ();

        return new Location(location.getWorld(), x, y, z);
    }


    public List<Location> getOffsetLocations(double offset, Location location, int amount) {
        return getOffsetLocations(offset, offset, offset, location, amount);
    }

    public List<Location> getOffsetLocations(double offsetX, double offsetY, double offsetZ, Location location, int amount) {
        List<Location> returnValue = new ArrayList<>();

        for(int i = 0; i < amount; i++) {
            returnValue.add(getOffsetLocation(offsetX, offsetY, offsetZ, location));
        }

        return returnValue;
    }




}
