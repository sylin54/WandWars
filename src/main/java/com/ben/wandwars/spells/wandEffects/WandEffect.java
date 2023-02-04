package com.ben.wandwars.spells.wandEffects;

import com.ben.wandwars.helpers.particles.MovingParticleGroup;
import com.ben.wandwars.spells.components.ProjectileInf;
import com.ben.wandwars.spells.components.ProjectileSpellComponent;
import com.ben.wandwars.spells.components.blockHit.BlockHitEffect;
import com.ben.wandwars.spells.components.entityDamage.OnHitEffect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.UUID;

public abstract class WandEffect {

    protected OnHitEffect onHitEffect;
    protected BlockHitEffect blockHitEffect;
    protected ProjectileInf projectileInf;

    protected MovingParticleGroup movingParticleGroup;

    protected float offset = 0.1f;
    public WandEffect(OnHitEffect onHitEffect, BlockHitEffect blockHitEffect, ProjectileInf projectileInf, MovingParticleGroup movingParticleGroup) {
        this.onHitEffect = onHitEffect;
        this.blockHitEffect = blockHitEffect;
        this.projectileInf = projectileInf;
        this.movingParticleGroup = movingParticleGroup;
    }

    public abstract void cast(Vector direction, Location location, UUID casterUUID);

    public void cast(Player player) {
        cast(player.getLocation().getDirection(), player.getLocation(), player.getUniqueId());
    }

    public void cast(Vector direction, Location location, Player player) {
        cast(direction, location, player);
    }

    protected ProjectileSpellComponent createProjectileComponent(Location location, Vector direction, UUID casterUUID) {
        return new ProjectileSpellComponent(onHitEffect, blockHitEffect, movingParticleGroup, location, direction, projectileInf, casterUUID);
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public float getOffset() {
        return offset;
    }
}
