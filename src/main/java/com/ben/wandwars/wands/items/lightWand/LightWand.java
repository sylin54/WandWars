package com.ben.wandwars.wands.items.lightWand;

import com.ben.wandwars.Main;
import com.ben.wandwars.helpers.CompleteSound;
import com.ben.wandwars.helpers.itemStackHelping.ItemStackHelper;
import com.ben.wandwars.helpers.particles.MovingParticleGroup;
import com.ben.wandwars.spells.components.ProjectileInf;
import com.ben.wandwars.spells.components.blockHit.NoBlockHitEffect;
import com.ben.wandwars.spells.components.entityDamage.OnHitEffect;
import com.ben.wandwars.spells.wandEffects.ProjectileWandEffect;
import com.ben.wandwars.spells.wandEffects.WandEffect;
import com.ben.wandwars.wands.wandInterfaces.Wand;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.UUID;

public class LightWand extends Wand {
    @Override
    public ItemStack getItem() {
        return ItemStackHelper.createItemStack(ChatColor.GOLD + "Light Wand", Material.STICK, new String[]{
                "Left Click: Ray of Light",
                "Right Click: Smite",
                "OffHand: Nourishment"
        });
    }

    @Override
    public boolean leftClickCast(Player caster, Main main) {

        ProjectileInf projectileInf = new ProjectileInf(1, 30);

        OnHitEffect onHitEffect = new OnHitEffect(7, new CompleteSound(Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.1f, 2));

        ProjectileWandEffect projectileWandEffect = new ProjectileWandEffect(onHitEffect, new NoBlockHitEffect(), projectileInf, new MovingParticleGroup(Particle.WAX_OFF));
        projectileWandEffect.cast(caster);

        return true;
    }

    @Override
    public boolean rightClickCast(Player caster, Main main) {
        return false;
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
