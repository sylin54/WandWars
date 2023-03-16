package com.ben.wandwars.spell.templates;

import com.ben.wandwars.spell.managers.SpellManager;
import com.ben.wandwars.util.spell.SpellCaster;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

//the abstracvt class of a spell. All implementation is handled by the spell manager class
public abstract class Spell {

    SpellManager spellManager = SpellManager.getInstance();

    //the amounts of ticks the spell has gone through
    private int hits = 0;
    
    protected Location location;
    //the team the spell was shot from
    private Team team;

    //the uuid of the caster. This will also include spell uuids.
    private List<SpellCaster> casters;

    private UUID spellID;

    protected Vector spellDir;

    //wether frinedly fire is enabled. If this is turned to true the spell be able to hit players in the casting players team.
    protected boolean isFriendlyFire = false;
    //if this is turned on the spell will be deleted when the caster is hit
    protected boolean isCharging = false;

    public Spell() {
        spellID = UUID.randomUUID();
    }

    public void cast(Location location, Vector direction, SpellCaster... casters) {
        spellManager.addSpell(this);

        this.location = location;
        this.spellDir = direction;

        this.casters = Arrays.asList(casters);
    }

    //implementation ont the tick function. Is mainly for intervals
    public void tick() {
        if (hits > getRange()) {
            onOutOfRange();
            remove();
            return;
        }

        if(tickInterval() == 0) {
            onTick();
            return;
        }

        if(hits % tickInterval() == 0) {
            onTick();
        }

        hits++;
    }

    public List<Player> getPlayerCasters() {

        List<Player> returnValue = new ArrayList<>();

        for(SpellCaster spellCaster : casters) {
            if(spellCaster.getCasterType().isPlayer()) {
                returnValue.add(spellCaster.getPlayer());
            }
         }

        return returnValue;
    }

    public List<SpellCaster> getCastersID() {return casters;}

    protected Vector updateDirection(Location newLocation) {
        return location.toVector().subtract(newLocation.toVector());
    }

    public void remove() {
        spellManager.removeSpell(this);
    }

    public Location getLocation() {
        return location;
    }

    public UUID getSpellID() {return spellID;}

    protected void offsetLocation(Vector offset) {
        location.add(offset);
    }

    public int getTicks() {
        return hits;
    }

    public void delete(){
        spellManager.removeSpell(this);
    }

    public boolean isCharging() {
        return isCharging;
    }


    //wether it interupts the casting of other spells
    public abstract boolean interrupts();
    //wether it pierces a shield
    public abstract void onShieldHit(ShieldSpellInfo shieldSpell);
    //executes when the spell hits another spell.
    public abstract void onSpellHit(Spell spell);
    //executes when spell hits a block.uses the last location to prevent explosions form happening in blocks.
    public abstract void onBlockHit(Block block, Location lastLocation);
    //executes when a spell hits a entity.
    public abstract void onEntityHit(LivingEntity entities);
    //executes when the spells gets out of range. This will always delete the spell.
    public abstract void onOutOfRange();
    //executes every time the tick interval gets past by the ticks
    protected abstract void onTick();
    //the dealy  between the tick command in ticks. If this is 0 the tick command will activate untill the spell is terminated
    public abstract int tickInterval();
    //the range of the spell
    public abstract int getRange();
    //this is called if the spell ever gets interupted. Not used for all spells, but for some for some of them.
    public abstract void onInterruption(Spell interrupter);
    public abstract void onInterruptionHit(Spell interrupted);
    public abstract double getRadius();



}

