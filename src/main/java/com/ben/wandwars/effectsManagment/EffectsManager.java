package com.ben.wandwars.effectsManagment;


import com.ben.wandwars.Main;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

//add displaying updating
public class EffectsManager {
    private HashMap<UUID, List<Effect>> effectMap = new HashMap<>();

    public void initialize() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(UUID uuid : effectMap.keySet()) {
                    List<Effect> effects = effectMap.get(uuid);

                    for(Effect effect : effects) {
                        effect.offsetTimeTicks(20/4);
                        if(effect.getTimeSeconds() <= 0) {
                            effects.remove(effect);
                        }
                    }

                    //update display here
                }
            }

        }.runTaskTimer(Main.getInstance(), 5, 5);
    }

    public void addEffect(Player player, Effect effect) {

        UUID uuid = player.getUniqueId();

        if(effectMap.containsKey(uuid)) {
            addFilteredEffect(effect, effectMap.get(uuid));
        } else {
            effectMap.put(uuid, new ArrayList<>());
            effectMap.get(uuid).add(effect);
        }
    }

    public void removeEffect(UUID uuid, int effectID) {
        if(effectMap.containsKey(uuid)) {
            for(Effect effect : effectMap.get(uuid)) {
                if(effect.getID() == effectID) {
                    effectMap.get(effect).remove(effect);
                }
            }

            if(effectMap.get(uuid).size() == 0) effectMap.remove(uuid);
        }
    }

    public void removeAllEffects(UUID uuid) {
        effectMap.remove(uuid);
    }

    public List<Effect> getEffects(UUID uuid) {
        if(effectMap.containsKey(uuid)) return effectMap.get(uuid);

        return new ArrayList<>();
    }

    //removes the effects that have a lower time left on them and have the same id; adds the effect if possible
    private void addFilteredEffect(Effect effect, List<Effect> effects) {
        for(Effect testEffect : effects) {
            if(effect.getID() == testEffect.getID() && effect.getTimeTicks() > testEffect.getTimeTicks()) {
                effects.add(effect);
                effects.remove(testEffect);
                return;
            }
        }

        effects.add(effect);
    }

    private void addPotionEffects(Player player) {
        UUID playerID = player.getUniqueId();

        if(!effectMap.containsKey(playerID)) {
            return;
        }

        List<Effect> effects = effectMap.get(playerID);

        for(Effect effect : effects){
            if(effect.getTimeTicks() == 0) {
                for(PotionEffectNoDur potionEffect : effect.getPotionEffects()) {
                    potionEffect.apply(player, Integer.MAX_VALUE);
                }

                return;
            }

            for(PotionEffectNoDur potionEffect : effect.getPotionEffects()) {
                potionEffect.apply(player, effect.getTimeTicks());
            }
        }
    }

    //singleton stuff:
    private static EffectsManager instance;

    public static EffectsManager getInstance() {
        if(instance == null) {
            instance = new EffectsManager();
        }

        return instance;
    }

    private EffectsManager(){}
}
