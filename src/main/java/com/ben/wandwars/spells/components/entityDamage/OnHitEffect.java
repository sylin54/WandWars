package com.ben.wandwars.spells.components.entityDamage;

import com.ben.wandwars.helpers.CompleteSound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class OnHitEffect {
    double damage = 0;
    int fireTick = 0;
    List<CompleteSound> sounds;

    float knockBackAmount = 0;
    float knockBackZ = 0;
    Vector castDirection;

    List<PotionEffect> potionEffects;

    boolean overrideDamage = false;

    boolean dissipateOnEntityHit = true;

    boolean hitsCaster = false;

    public OnHitEffect() {
    }

    public OnHitEffect(double damage) {
        this.damage = damage;
    }

    public OnHitEffect( List<PotionEffect> potionEffect) {
        this.potionEffects = potionEffect;
    }

    public OnHitEffect(double damage, CompleteSound sound) {
        this.damage = damage;
        sounds = new ArrayList<>();
        sounds.add(sound);
    }


    public OnHitEffect(int damage, CompleteSound sound, List<PotionEffect> potionEffects, boolean dissipateOnEntityHit) {
        this.damage = damage;
        sounds = new ArrayList<>();
        sounds.add(sound);
        this.potionEffects = potionEffects;
        this.dissipateOnEntityHit = dissipateOnEntityHit;
    }

    public OnHitEffect(int damage, List<PotionEffect> potionEffect) {
        this.damage =damage;
        this.potionEffects = potionEffect;
    }

    public OnHitEffect(int damage, CompleteSound sound, int fireTick) {
        this.damage = damage;

        sounds = new ArrayList<>();
        sounds.add(sound);

        this.fireTick = fireTick;
    }

    public OnHitEffect(int damage, CompleteSound sound, float knockBackAmount, float knockBackZ, Vector castDirection) {
        this.damage = damage;
        sounds = new ArrayList<>();
        sounds.add(sound);

        this.knockBackAmount = knockBackAmount;
        this.knockBackZ = knockBackZ;
        this.castDirection = castDirection;

    }


    //damages the victim relative the to on hit effects
    public void hit(LivingEntity victim) {
        hitHelper(victim);
    }


    protected void hitHelper(LivingEntity victim) {


        //if this is true that means the value is positive which means it can just be a damage function, if it is false than  it has to a heal. if the damage is 0 nothing will happen

        if(damage != 0) {
            if (damage / Math.abs(damage) == 1) {
                victim.damage(damage);
            } else {
                damage = Math.abs(damage);

                double maxHealth = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

                if (maxHealth != 20) {
                    victim.sendMessage("potential error in on hit effect class");
                }

                if (damage + victim.getHealth() >= maxHealth) {
                    victim.setHealth(maxHealth);
                } else {
                    victim.setHealth(victim.getHealth() + damage);
                }
            }
        }

        if(fireTick > victim.getFireTicks()) {
            victim.setFireTicks(fireTick);
        }


        if(sounds != null) {
            for (CompleteSound completeSound : sounds) {
                completeSound.playSound(victim.getLocation());
            }
        }

        if(potionEffects != null) {
            for(PotionEffect potionEffect : potionEffects) {
                victim.addPotionEffect(potionEffect);
            }
        }

        if(castDirection != null && victim.getNoDamageTicks() > 0) {
            Vector knockBack = castDirection;
            knockBack.normalize();

            knockBack.multiply(knockBackAmount);
            knockBack.setY(knockBackZ);

            victim.setVelocity(victim.getVelocity().add(knockBack));
        }
    }

    public boolean hasDamage() {

        if(overrideDamage) return true;

        if(damage == 0) {
            return false;
        }

        if(damage / Math.abs(damage) == -1) {
            return false;
        } else {
            return true;
        }
    }

    //this is to make the thing do damage even if it technically doesn't. this is for the hit message system
    public void setOverrideDamage(boolean overrideDamage) {
        this.overrideDamage = overrideDamage;
    }

    public boolean overridesDamage() {
        return overrideDamage;
    }

    public boolean dissipatesOnEntityHit() {return dissipateOnEntityHit;}

    public void setHitsCaster(boolean hitsCaster) {this.hitsCaster = hitsCaster;}

    public boolean hitsCaster() {return hitsCaster;}
}
