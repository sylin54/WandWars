package com.ben.wandwars.game.util.countDown;

import com.ben.wandwars.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;

//handles countdowns; Might need to be expanded.
public class CountDownUtil {

    int greenPeriod;
    int yellowPeriod;
    int redPeriod;

    CountDownUtilCallback onEndCallBack;

    public CountDownUtil(int greenPeriod, int yellowPeriod, int redPeriod, CountDownUtilCallback onEndCallBack) {
        this.greenPeriod = greenPeriod;
        this.yellowPeriod = yellowPeriod;
        this.redPeriod = redPeriod;
        this.onEndCallBack = onEndCallBack;
    }

    public void start(List<Player> players) {

        HashMap<Player, Location> playersMap = new HashMap<>();

        for(Player player : players) {
            playersMap.put(player, player.getLocation());
        }


        new BukkitRunnable() {

            int count = totalSecs();

            @Override
            public void run() {

                if(count < 0) {

                    onEndCallBack.callOnce();

                    for(Player player : playersMap.keySet()) {
                        onEndCallBack.call(player);
                    }
                    cancel();
                    return;
                }

                ChatColor color;

                if(isGreen(count)) {
                    color = ChatColor.GREEN;
                } else if(isYellow(count)) {
                    color = ChatColor.YELLOW;
                } else {
                    color = ChatColor.RED;
                }

                for(Player player : playersMap.keySet()) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 10, true, false, false));
                    player.sendTitle(color + Integer.toString(count), " ", 3, 14, 3);
                    player.teleport(playersMap.get(player));
                }

                count--;
            }
        }.runTaskTimer(Main.getInstance(), 0, 20);
    }

    private boolean isGreen(int second) {
        return second > yellowPeriod+redPeriod;
    }

    private boolean isYellow(int second) {
        return second > redPeriod && second < yellowPeriod + redPeriod;
    }

    private boolean isRed(int second) {
        return second < yellowPeriod;
    }

    private int totalSecs() {
        return greenPeriod + yellowPeriod + redPeriod;
    }
 }
