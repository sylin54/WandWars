package com.ben.wandwars.effectsManagment;

import com.ben.wandwars.Main;
import com.ben.wandwars.displaying.Displayer;
import com.ben.wandwars.helpers.scheduling.CoolDownManagerCallback;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class OldEffectsManager {
    private HashMap<UUID, List<OldEffectBundler>> effectMap = new HashMap<>();

    static OldEffectsManager instance;

    Main main;
    protected OldEffectsManager( ) {
        main = Main.getInstance();
    }

    public static OldEffectsManager getInstance() {
        if(instance == null) {
            instance = new OldEffectsManager();
        }

        return instance;
    }

    public boolean hasEffect(LivingEntity livingEntity, OldEffectType effectType) {
        if(!effectMap.containsKey(livingEntity.getUniqueId())) return false;

        for(OldEffectBundler effect : effectMap.get(livingEntity.getUniqueId())) {
            if(effectType == effect.getEffect().getType()) {
                return true;
            }
        }

        return false;
    }

    public List<OldEffect> getEffects(LivingEntity livingEntity) {

        List<OldEffect> returnValue = new ArrayList<>();

        if(!effectMap.containsKey(livingEntity.getUniqueId())) {
            return returnValue;
        }

        for(OldEffectBundler effectBundler : effectMap.get(livingEntity.getUniqueId())) {
            returnValue.add(effectBundler.getEffect());
        }
        return returnValue;
    }


    public OldEffect getEffect(LivingEntity livingEntity, OldEffectType effectType) {
        for(OldEffectBundler effectBundler : effectMap.get(livingEntity.getUniqueId())) {
            if(effectBundler.getEffect().getType() == effectType) {
                return effectBundler.getEffect();
            }
        }
        return null;
    }


    public boolean removeEffect(LivingEntity livingEntity, OldEffectType effectType) {
        UUID playerUUID = livingEntity.getUniqueId();

        if(!effectMap.containsKey(playerUUID)) {
            return false;
        }

        List<OldEffectBundler> modifiedEffects = effectMap.get(playerUUID);

        boolean modified = false;
        int effects = 0;

        //loops through the effects and remove the one with the same effect type
        for(int i = 0; i < modifiedEffects.size(); i++) {
            OldEffectBundler effectBundler = modifiedEffects.get(i);

            if(effectBundler.getEffect().getType() == effectType) {
                modifiedEffects.remove(effectBundler);
                effectBundler.remove();

                modified = true;

                //this is just to debug
                effects++;

            }
        }


        if(effects > 1) {
            livingEntity.sendMessage(ChatColor.RED + "You have more than one of the same effect! If you see this please contact an admin");
        }

        if(modifiedEffects.size() == 0) {
            effectMap.remove(playerUUID);

        } else {
            effectMap.replace(playerUUID, modifiedEffects);
        }

        //resets the living entities potion effects
        for(PotionEffect potionEffect : livingEntity.getActivePotionEffects()) {
            livingEntity.removePotionEffect(potionEffect.getType());
        }

        addPotionEffects(livingEntity);

        display(livingEntity);

        return modified;
    }


    public void addEffect(LivingEntity livingEntity, OldEffect effect) {
        //creating the effect bundler
        OldEffectBundler effectBundler = new OldEffectBundler(effect.getType(), effect.getDuration(), livingEntity, new CoolDownManagerCallback() {
            @Override
            public void run(Player target) {
                display(livingEntity);
            }
        }, new CoolDownManagerCallback() {
            @Override
            public void run(Player target) {
                display(livingEntity);
                removeEffect(livingEntity, effect.getType());
            }
        });

        UUID playerUUID = livingEntity.getUniqueId();

        if(!effectMap.containsKey(playerUUID)) {
            List<OldEffectBundler> effects = new ArrayList<>();
            effects.add(effectBundler);

            effectMap.put(playerUUID ,effects);

            applyEffectSpecificPotionEffects(effect, livingEntity);

            display(livingEntity);
            return;
        }

        //put the remove duplicate effects in a function
        //all of this is to make sure duplicates don't happen
        long highestDuration = 0;
        for(OldEffectBundler testEffectBundler : effectMap.get(playerUUID)) {
            OldEffect testEffect = testEffectBundler.getEffect();

            //gets the highest duration of the effect
            if(testEffect.getType() == effect.getType()) {
                if(testEffect.getDuration() > highestDuration) {
                    highestDuration = testEffect.getDuration();
                }
            }
        }

        if(highestDuration > effect.getDuration()) {
            return;
        } else {
            removeEffect(livingEntity, effect.getType());
        }

        //adds the effect to the list of effects
        if(effectMap.containsKey(playerUUID)) {
            List<OldEffectBundler> modifiedEffects = effectMap.get(playerUUID);
            modifiedEffects.add(effectBundler);
            effectMap.replace(playerUUID, modifiedEffects);
        } else {
            List<OldEffectBundler> modifiedEffects = new ArrayList<>();
            modifiedEffects.add(effectBundler);
            effectMap.put(playerUUID, modifiedEffects);
        }

        //adds the potion effects
        applyEffectSpecificPotionEffects(effect, livingEntity);

        display(livingEntity);
    }

    public void applyEffectSpecificPotionEffects(OldEffect effect, LivingEntity livingEntity) {
        for(PotionEffect potionEffect : effect.getType().getEffects()) {
            int duration;
            if(effect.getDuration() == 0) {
                duration = Integer.MAX_VALUE;
            } else {
                duration = effect.getDuration() * 20;
            }
            livingEntity.addPotionEffect(new PotionEffect(potionEffect.getType(), duration, potionEffect.getAmplifier(), true, false));
        }
    }

    public void addPotionEffects(LivingEntity livingEntity) {
        UUID entityUUID = livingEntity.getUniqueId();

        if(!effectMap.containsKey(entityUUID)) return;

        for(OldEffectBundler effectBundler : effectMap.get(entityUUID)) {
            OldEffect effect = effectBundler.getEffect();

            int duration;
            if(effect.getDuration() == 0) {
                duration = Integer.MAX_VALUE;
            } else {
                duration = effect.getDuration() * 20;
            }
            System.out.println(duration);


            for(PotionEffect potionEffect : effect.getType().getEffects()) {
                livingEntity.addPotionEffect(new PotionEffect(potionEffect.getType(), duration, potionEffect.getAmplifier(), true, false));
            }
        }
    }

    public void removeEffects(LivingEntity livingEntity) {
        effectMap.remove(livingEntity.getUniqueId());

        for(PotionEffect potionEffect : livingEntity.getActivePotionEffects()) {
            livingEntity.removePotionEffect(potionEffect.getType());
        }

        display(livingEntity);
    }

    public void display(LivingEntity livingEntity) {
        if(livingEntity instanceof Player) {
            Displayer.update((Player) livingEntity);
        }
    }

    //these next methods are just for checking effect specific things (like whether the player can dash)
    public boolean canDash(Player player) {
        UUID uuid = player.getUniqueId();

        if(!effectMap.containsKey(uuid)) {
            return true;
        }

        for(OldEffectBundler effectBundler : effectMap.get(uuid)) {
            if(effectBundler.getEffect().getType().stopsDash()) {
                return false;
            }
        }
        return true;
    }

    public boolean canWandsCommand(Player player) {
        UUID uuid = player.getUniqueId();

        if(!effectMap.containsKey(uuid)) {
            return true;
        }

        for(OldEffectBundler effectBundler : effectMap.get(uuid)) {
            if(effectBundler.getEffect().getType().stopsWandsCommand()) {
                return false;
            }
        }
        return true;
    }

    public void damage(LivingEntity livingEntity) {
        UUID uuid = livingEntity.getUniqueId();

        if(!effectMap.containsKey(uuid)) {
            return;
        }


        int size = effectMap.get(uuid).size();
        for(int i = 0; i < size; i++) {
            if(!effectMap.containsKey(uuid)) return;

            OldEffectType effectType = effectMap.get(uuid).get(i).getEffect().getType();

            if(effectType.removesOnDamage()) {
                removeEffect(livingEntity, effectType);
            }
        }
    }
}
