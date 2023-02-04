package com.ben.wandwars.spells.components.blockHit;

import com.ben.wandwars.Main;
import com.ben.wandwars.spells.components.blockHit.BlockHitEffect;
import com.ben.wandwars.spells.components.entityDamage.DamageEntityHelper;
import com.ben.wandwars.spells.components.entityDamage.OnHitEffect;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.UUID;

public class ExplosionBlockHitEffect extends BlockHitEffect {

    int power;

    public ExplosionBlockHitEffect(int power) {
        this.power = power;
    }
    @Override
    public void hit(Location location, Main main, UUID caster) {
        location.getWorld().createExplosion(location, power, true, false);

        OnHitEffect onHitEffect = new OnHitEffect();
        onHitEffect.setHitsCaster(true);
        onHitEffect.setOverrideDamage(true);
        DamageEntityHelper damageEntityHelper = new DamageEntityHelper(power, onHitEffect);
        damageEntityHelper.damageEntities(location, new ArrayList<>(), caster);
    }
}
