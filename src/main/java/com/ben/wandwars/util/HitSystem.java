package com.sylin.spellsystem.util;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class HitSystem {

    private List<OffsetPoint> points = new ArrayList<>();

    private Location location;

    public HitSystem(Location location) {
        this.location = location;
    }

    public void addPoint(Vector offset, double radius) {
        points.add(new OffsetPoint(offset, radius));
    }

    public void clearPoints() {
        points.clear();
    }

    public List<Block> getIntersectingBlocks() {

        List<Block> blocks = new ArrayList<>();

        for(OffsetPoint offsetPoint : points) {

            Block block = offsetPoint.getBlock(location);

            if(!block.getType().isAir() && !blocks.contains(block)) {
                blocks.add(block);
            }
        }

        return blocks;
    }

//    public List<LivingEntity> getIntersectingEntities() {
//
//    }
//
//    public List<Spell> getIntersectingSpells() {
//
//    }
//
//    public boolean intersectsWithBlock() {
//
//    }
//
//    public boolean intersectsWithEntity() {
//
//    }
//
//    public boolean intersectsWithSpell() {
//
//    }
}
