package com.ben.wandwars.spells.components.blockHit;

import com.ben.wandwars.Main;
import org.bukkit.Location;

import java.util.UUID;

public abstract class BlockHitEffect {

    boolean dissipatesOnBlockHit;

    public abstract void hit(Location location, Main main, UUID caster);


    public void setDissipatesOnBlockHit(boolean dissipatesOnBlockHit) {this.dissipatesOnBlockHit = dissipatesOnBlockHit;}

    public boolean dissipatesOnBlockHit() {
        return dissipatesOnBlockHit;
    }
}
