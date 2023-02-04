package com.ben.wandwars.spells.components.entityDamage;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;

public class ExplosionOnHitEffect extends OnHitEffect {

    int power;

    public ExplosionOnHitEffect(int power) {
        this.power = power;
    }

    @Override
    public void hit(LivingEntity victim) {
        Location location = victim.getLocation();

        location.getWorld().createExplosion(location, power, true, false);

        OnHitEffect onHitEffect = new OnHitEffect();
        onHitEffect.setHitsCaster(true);
        onHitEffect.setOverrideDamage(true);
        DamageEntityHelper damageEntityHelper = new DamageEntityHelper(power, onHitEffect);
        damageEntityHelper.damageEntities(location, new ArrayList<>(), victim.getUniqueId());
    }
}
