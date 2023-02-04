package com.ben.wandwars.spells.components;

import com.ben.wandwars.Main;
import com.ben.wandwars.helpers.particles.MovingParticleGroup;
import com.ben.wandwars.spells.components.blockHit.BlockHitEffect;
import com.ben.wandwars.spells.components.entityDamage.DamageEntityHelper;
import com.ben.wandwars.spells.components.entityDamage.OnHitEffect;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectileSpellComponent {
    OnHitEffect onHitEffect;
    MovingParticleGroup movingParticleGroup;

    Location location;
    Vector movement;

    List<UUID> hitUUIDS = new ArrayList<>();

    UUID caster;

    BlockHitEffect blockHitEffect;

    Main main;


    ProjectileInf projectileInf;

    public ProjectileSpellComponent(OnHitEffect onHitEffect, BlockHitEffect blockHitEffect, MovingParticleGroup movingParticleGroup, Location startingLocation, Vector direction, ProjectileInf projectileInf, UUID casterUUID) {
        this.onHitEffect = onHitEffect;

        this.movingParticleGroup = movingParticleGroup;

        this.location = startingLocation;

        this.movement = direction.multiply(projectileInf.getSpeed());

        this.caster = casterUUID;

        this.blockHitEffect = blockHitEffect;

        this.projectileInf = projectileInf;

        main = Main.getInstance();
    }

    public void move() {
        location.add(movement);

        movingParticleGroup.spawn(location);
    }


    public boolean detect() {
        DamageEntityHelper damageEntityHelper = new DamageEntityHelper(projectileInf.getOffset(), onHitEffect);

        damageEntityHelper.damageEntities(location, hitUUIDS, caster);

        if(damageEntityHelper.getHitUUIDs().size() > 0) {
            if(onHitEffect.dissipatesOnEntityHit()) return true;

            hitUUIDS.addAll(damageEntityHelper.getHitUUIDs());
        }

        Location testLoc = location.add(movement).clone();

        if(!Main.isTransparent(testLoc.getBlock().getType())) {
            blockHitEffect.hit(location, main, caster);
            if(blockHitEffect.dissipatesOnBlockHit()) return true;
        }

        return false;
    }

    public void setMovement(Vector movement) {
        this.movement = movement;
    }

    public Vector getMovement() {
        return movement;
    }

    public void offsetMovement(Vector movementOffset) {
        movement.subtract(movementOffset);
    }

    public MovingParticleGroup getParticles() {
        return movingParticleGroup;
    }

    public Location getLocation() {return location.clone();}

    public List<UUID> getHitUUIDS() {return hitUUIDS;}

    public void cast() {
        if(!onHitEffect.hitsCaster()) {
            hitUUIDS.add(caster);
        }

        //if the interval is less than 1 that means it was not changed
        if(projectileInf.getInterval() < 1) {
            castRay();
        } else {
            castRun();
        }
    }

    public void cast(ProjectileSpellRun projectileSpellRun) {
        if(!onHitEffect.hitsCaster()) {
            hitUUIDS.add(caster);
        }

        if(projectileInf.getInterval() < 1) {
            castRay(projectileSpellRun);
        } else {
            castRun(projectileSpellRun);
        }
    }

    private void castRun(ProjectileSpellRun projectileSpellRun) {

        projectileSpellRun.setProjectileSpellComponent(this);
        new BukkitRunnable() {

            int runs = 0;

            @Override
            public void run() {

                if(projectileSpellRun.isCancelled()) {
                    cancel();
                    return;
                }

                move();

                projectileSpellRun.run();

                if(detect()) {
                    cancel();
                    return;
                }

                runs++;
                if(runs > projectileInf.getRange()) {
                    cancel();
                }

            }
        }.runTaskTimer(main, 0, projectileInf.getInterval());
    }

    private void castRun() {
        new BukkitRunnable() {

            int runs = 0;

            @Override
            public void run() {

                move();

                if(detect()) {
                    cancel();
                    return;
                }

                runs++;
                if(runs > projectileInf.getRange()) {
                    cancel();
                }

            }
        }.runTaskTimer(main, 0, projectileInf.getInterval());
    }

    private void castRay(ProjectileSpellRun projectileSpellRun) {
        projectileSpellRun.setProjectileSpellComponent(this);

        for(int i = 0; i < projectileInf.getRange(); i++) {
            if(projectileSpellRun.isCancelled) {
                return;
            }

            move();

            projectileSpellRun.run();

            if(detect()) {
                return;
            }
        }
    }

    private void castRay() {

        for(int i = 0; i < projectileInf.getRange(); i++) {
            move();

            if(detect()) {
                return;
            }
        }
    }

}
