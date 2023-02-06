package com.ben.wandwars.stateManagers;

import com.ben.wandwars.displaying.Displayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class ManaManager implements Listener {

    private static ManaManager instance;

    private int manaCap = 100;

    private ManaManager( ) {
    }

    //mana map
    private HashMap<UUID, Integer> manaMap = new HashMap<>();

    //gets the players mana
    public int getMana(Player player) {
        UUID uuid = player.getUniqueId();

        if(manaMap.containsKey(uuid)) {
            return manaMap.get(uuid);
        }

        return 100;
    }

    //offsets the players mana to the input
    public boolean offsetMana(Player player, int offsetAmount) {
        UUID uuid = player.getUniqueId();

        if(manaMap.get(uuid) + offsetAmount > manaCap) {
            manaMap.put(uuid, manaCap);
            Displayer.update(player);
            return true;
        }

        if(manaMap.get(uuid) + offsetAmount < 0) {
            Displayer.update(player);
            return false;
        }

        manaMap.replace(uuid, manaMap.get(uuid) + offsetAmount);

        Displayer.update(player);
        return true;
    }

    //registers player on join
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        UUID joinedUUID = event.getPlayer().getUniqueId();

        if(!manaMap.containsKey(joinedUUID)) {
            manaMap.put(joinedUUID, 100);
        }

        Displayer.update(event.getPlayer());
    }

    //gets the mana cap
    public int getCap() {return manaCap;}

    public static ManaManager getInstance() {
        if(instance == null) {
            instance = new ManaManager();
        }

        return instance;
    }
}
