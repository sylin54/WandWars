package com.ben.wandwars.spell.managers;

import com.ben.wandwars.Main;
import com.ben.wandwars.spell.templates.ShieldSpellInfo;
import com.ben.wandwars.spell.templates.Spell;
import com.ben.wandwars.util.HitUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SpellManager {
    //handles the sotring and ticks of all the spells

    private List<Spell> spells = Collections.synchronizedList(new ArrayList<>());
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
                synchronized (spells) {
                    for (int i = 0; i < spells.size(); i++) {

                        Spell spell = spells.get(i);

                        //stores the last loction to incase spells use explosions in side their effets, this will prevent the explosions from being suffocated
                        Location lastLocation = spell.getLocation();

                        spell.tick();

                        //collisions only uses the first collided for now, can change later if enough people think it is jank
                        if (HitUtil.intersectsWithBlock(spell)) {
                            spell.onBlockHit(HitUtil.getIntersectingBlock(spell), lastLocation);
                        }

//                    if(HitUtil.intersectsEntity(spell)) {
//                        spell.onEntityHit(HitUtil.getEntities(spell).get(0));
//                    }

                        if (intersectsWithSpell(spell)) {

                            Spell hit = getIntersectingSpells(spell).get(0);

                            if (hit.isCharging()) {
                                spell.onInterruptionHit(hit);
                                hit.onInterruption(spell);
                                break;
                            }

                            if (hit instanceof ShieldSpellInfo) {
                                spell.onShieldHit((ShieldSpellInfo) hit);
                            } else {
                                spell.onSpellHit(getIntersectingSpells(spell).get(0));
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 1, 1);
    }

    public Spell getSpell(UUID spellID) {
        synchronized (spells) {
            for (Spell spell : spells) {
                if (spell.getSpellID().equals(spellID)) {
                    return spell;
                }
            }
            return null;
        }
    }

    public  boolean intersectsWithSpell(Spell spell) {
//        double radius = spell.getRadius();
//
//        for(Spell testSpell : spells) {
//
//            if(testSpell == spell) continue;
//
//            double testRadius = testSpell.getLocation().distance(spell.getLocation());
//
//            if(testRadius <= radius) {
//                return true;
//            }
//        }
        return false;
    }

    public List<Spell> getIntersectingSpells(Spell spell) {
//        List<Spell> returnValue = new ArrayList<>();
//
//        double radius = spell.getRadius();
//
//        for(Spell testSpell : spells) {
//
//            if(testSpell == spell) continue;
//
//            double testRadius = testSpell.getLocation().distance(spell.getLocation());
//
//            if(testRadius <= radius) {
//                returnValue.add(testSpell);
//            }
//        }
        return new ArrayList<>();
    }

    private static SpellManager instance = new SpellManager();
    public static SpellManager getInstance() {
        return instance;
    }
    private SpellManager() {}

}
