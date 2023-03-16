package com.ben.wandwars.spell.managers;

import com.ben.wandwars.Main;
import com.ben.wandwars.spell.templates.ShieldSpellInfo;
import com.ben.wandwars.spell.templates.Spell;
import com.ben.wandwars.util.HitUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpellManager {
    //handles the sotring and ticks of all the spells

    private List<Spell> spells = new ArrayList<>();
    public void addSpell(Spell spell) {
        spells.add(spell);
    }

    public void removeSpell(Spell spell) {
        spells.remove(spell);
    }

    //handles the movement of the spells.
    public void initialize() {
        new BukkitRunnable() {
            @Override
            public void run() {

                if(spells == null) Bukkit.broadcastMessage("NULL");

                for(Spell spell : spells) {

                    //stores the last loction to incase spells use explosions in side their effets, this will prevent the explosions from being suffocated
                    Location lastLocation = spell.getLocation();

                    spell.tick();

                    //collisions only uses the first collided for now, can change later if enough people think it is jank
                    if(HitUtil.intersectsWithBlock(spell)) {
                        spell.onBlockHit(HitUtil.getIntersectingBlock(spell), lastLocation);
                    }

//                    if(HitUtil.intersectsEntity(spell)) {
//                        spell.onEntityHit(HitUtil.getEntities(spell).get(0));
//                    }

                    if(HitUtil.intersectsWithSpell(spell)) {

                        Spell hit = HitUtil.getIntersectingSpells(spell).get(0);

                        if(hit.isCharging()) {
                            spell.onInterruptionHit(hit);
                            hit.onInterruption(spell);
                            break;
                        }

                        if(hit instanceof ShieldSpellInfo) {
                            spell.onShieldHit((ShieldSpellInfo) hit);
                        } else {
                            spell.onSpellHit(HitUtil.getIntersectingSpells(spell).get(0));
                        }
                    }

                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 1);
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public Spell getSpell(UUID spellID) {
        for(Spell spell : spells) {
            if(spell.getSpellID().equals(spellID)) {
                return spell;
            }
        }

        return null;
    }

    private static SpellManager instance = new SpellManager();
    public static SpellManager getInstance() {
        return instance;
    }
    private SpellManager() {}

}
