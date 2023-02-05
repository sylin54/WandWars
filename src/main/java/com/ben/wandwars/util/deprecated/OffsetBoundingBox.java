package com.sylin.spellsystem.util.deprecated;

import org.bukkit.Location;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

//a class mainly as util for the hitbox class. Doesn't store a location for performance.
// Is reccomended to use methods already inside for util.
public class OffsetBoundingBox {

    Vector offset;
    BoundingBox boundingBox;

    public OffsetBoundingBox(Vector offset, BoundingBox boundingBox) {
        this.offset = offset;
        this.boundingBox = boundingBox;
    }

    public Vector getOffset() {
        return offset;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    //various simple utils:

//    //point containment
//    public boolean containsPoint(Location location, Location point) {
//        if(location.getWorld.getName())
//    }
    
    public boolean containsX(Location location, double x) {
        return x < getMaxY(location) && x > getMinX(location);
    }
    
    public boolean containsY(Location location, double y) {
        return y < getMaxY(location) && y > getMinY(location);
    }
    
    public boolean containsZ(Location location, double z) {
        return z < getMaxZ(location) && z > getMinZ(location);
    }

    //min/max coords
    //gets the higher x corner. Takes into account location and offset.
    public double getMaxX(Location location) {
        return boundingBox.getMaxX() + offset.getX() + location.getX();
    }

    //gets the lower x corner. Takes into account location and offset.
    public double getMinX(Location location) {
        return boundingBox.getMinX() + offset.getX() + location.getX();
    }

    //gets the higher y corner. Takes into account location and offset.
    public double getMaxY(Location location) {
        return boundingBox.getMaxY() + offset.getY() + location.getY();
    }

    //gets the lower y corner. Takes into account location and offset.
    public double getMinY(Location location) {
        return boundingBox.getMinY() + offset.getY() + location.getY();
    }

    //gets the higher Z corner. Takes into account location and offset.
    public double getMaxZ(Location location) {
        return boundingBox.getMaxZ() + offset.getZ() + location.getZ();
    }

    //gets the lower Z corner. Takes into account location and offset.
    public double getMinZ(Location location) {
        return boundingBox.getMinZ() + offset.getZ() + location.getZ();
    }

    //width
    //gets the difference between maxX and minX, aka the width
    public double getWidthX(Location location) {
        return getMaxX(location) - getMinX(location);
    }

    //gets the difference between maxY and minY, aka the width
    public double getWidthY(Location location) {
        return getMaxY(location) -getMinY(location);
    }

    //gets the difference between maxZ and minZ, aka the width
    public double getWidthZ(Location location) {
        return getMaxZ(location) - getMinZ(location);
    }

    //gets the longest of the widths
    public double getLongestWidth(Location location) {
        if(getWidthX(location) > getWidthY(location) && getWidthX(location) > getWidthZ(location)) {
            return getWidthX(location);
        }

        if(getWidthZ(location) > getWidthY(location)) {
            return getWidthZ(location);
        }

        return getWidthY(location);
    }
}
