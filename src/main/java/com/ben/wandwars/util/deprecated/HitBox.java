package com.ben.wandwars.util.deprecated;

import org.bukkit.Location;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class HitBox {

    private List<OffsetBoundingBox> hitBoxes = new ArrayList<>();
    private Location location;

    int highestDistance = 0;

    //adds a hit box to the hit map
    public void addBox(Vector offset, BoundingBox box) {
        OffsetBoundingBox hitBox = new OffsetBoundingBox(offset, box);
        if(highestDistance < hitBox.getLongestWidth(location)) {
            highestDistance = (int) hitBox.getLongestWidth(location) + 1;
        }

        hitBoxes.add(hitBox);
    }

    //clears the hit boxes
    public void clearBoxes() {
        highestDistance = 0;
        hitBoxes.clear();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    //methods to check if the box intercects with stuff and getting what it intersects with
//    public boolean intersectsWithBlock() {
//    }
//
//    public List<Block> getIntersectingBlocks() {
//
//    }
//
//    public boolean intersectsWithSpell() {
//
//    }
//
//    public List<Spell> getIntersectingSpells() {
//
//    }
//
//    public boolean intersectsWithEntity() {
//
//    }
//
//    public List<LivingEntity> getIntersectingEntities() {
//
//    }

    public boolean containsLoc(Location location) {
        for(OffsetBoundingBox boundingBox : hitBoxes) {
            if(location.getX() > boundingBox.getMaxX(location) || location.getX() < boundingBox.getMinX(location)) {
                continue;
            }

            if(location.getY() > boundingBox.getMaxY(location) || location.getY() < boundingBox.getMinY(location)) {
                continue;
            }

            if(location.getY() > boundingBox.getMaxZ(location) || location.getZ() < boundingBox.getMinZ(location)) {
                continue;
            }

            return true;
        }

        return false;
    }
}
