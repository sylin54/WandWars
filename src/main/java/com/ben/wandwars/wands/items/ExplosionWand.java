package com.ben.wandwars.wands.items;

import com.ben.wandwars.Main;
import com.ben.wandwars.helpers.itemStackHelping.ItemStackHelper;
import com.ben.wandwars.helpers.particles.MovingParticle;
import com.ben.wandwars.helpers.particles.MovingParticleGroup;
import com.ben.wandwars.spells.components.ProjectileInf;
import com.ben.wandwars.spells.components.blockHit.ExplosionBlockHitEffect;
import com.ben.wandwars.spells.components.blockHit.NoBlockHitEffect;
import com.ben.wandwars.spells.components.entityDamage.ExplosionOnHitEffect;
import com.ben.wandwars.spells.components.entityDamage.OnHitEffect;
import com.ben.wandwars.spells.wandEffects.BlockHitEffectEveryTickProjectile;
import com.ben.wandwars.spells.wandEffects.GravityProjectileWandEffect;
import com.ben.wandwars.spells.wandEffects.ProjectileWandEffect;
import com.ben.wandwars.wands.wandInterfaces.Wand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ExplosionWand extends Wand {

    @Override
    public ItemStack getItem() {
        return ItemStackHelper.createItemStack(ChatColor.GOLD + "Explosion Wand", Material.STICK, new String[]{
                "Left Click: Impact Grenade",
                "Right Click: Rail Gun",
                "OffHand: Fiery Jump"
        });
    }

    @Override
    public boolean leftClickCast(Player caster, Main main) {

        ProjectileInf projectileInf = new ProjectileInf(1, 30, 1);

        MovingParticleGroup particles = new MovingParticleGroup();

        particles.addParticle(new MovingParticle(Particle.FLAME));
        particles.addParticle(new MovingParticle(Particle.SMOKE_NORMAL));
        particles.addParticle(new MovingParticle(Particle.SMOKE_LARGE));
        particles.addParticle(new MovingParticle(Particle.SMALL_FLAME));
        particles.setOffset(0.2);

        GravityProjectileWandEffect gravityProjectileWandEffect = new GravityProjectileWandEffect(
                new ExplosionOnHitEffect(3), new ExplosionBlockHitEffect(3), projectileInf, particles, 0.03f);

        gravityProjectileWandEffect.setOffset(0.2f);

        gravityProjectileWandEffect.cast(caster);

        return true;
    }

    @Override
    public boolean rightClickCast(Player caster, Main main) {

        Vector direction = caster.getLocation().getDirection();
        Location location = caster.getLocation();

        ProjectileInf projectileInf = new ProjectileInf(1, 30);

        OnHitEffect onHitEffect = new OnHitEffect();
        onHitEffect.setHitsCaster(false);

        ProjectileWandEffect projectileWandEffect = new ProjectileWandEffect(onHitEffect , new NoBlockHitEffect(), projectileInf, new MovingParticleGroup(Particle.END_ROD));
        projectileWandEffect.cast(caster);

        direction.multiply(2);

        location.add(direction);

        direction.normalize();

        new BukkitRunnable(){
            ProjectileInf projectileInf = new ProjectileInf(3, 10, 1);
            BlockHitEffectEveryTickProjectile blockHitEffectEveryTickProjectile = new BlockHitEffectEveryTickProjectile(onHitEffect, new NoBlockHitEffect(), projectileInf, new MovingParticleGroup());

            @Override
            public void run() {
                blockHitEffectEveryTickProjectile.cast(direction, location, caster);
            }
        }.runTaskLater(main, 15);

        return true;
    }

    @Override
    public boolean abilityCast(Player caster, Main main) {
        return false;
    }

    @Override
    public int getLeftManaUsage() {
        return 0;
    }

    @Override
    public int getRightClickManaUsage() {
        return 0;
    }

    @Override
    public int getAbilityMana() {
        return 0;
    }
}
