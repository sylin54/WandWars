package com.ben.wandwars.util;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class BlockUtil {
    public static double getMaxX(Block block) {
        return block.getX() + 1;
    }

    public static double getMinX(Block block) {
        return block.getX();
    }

    public static double getMaxY(Block block) {
        return block.getY() + 1;
    }

    public static double getMinY(Block block) {
        return block.getY();
    }

    public static double getMaxZ(Block block) {
        return block.getY() + 1;
    }

    public static double getMinZ(Block block) {
        return block.getY();
    }

}
