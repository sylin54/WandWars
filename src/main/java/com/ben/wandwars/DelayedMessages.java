package com.ben.wandwars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DelayedMessages{
    long period;

    Random random = new Random();
    List<String> messages = new ArrayList<>();


    public DelayedMessages(long periodTick) {
        Main main = Main.getInstance();
        this.period = periodTick;

        messages.add(ChatColor.BLUE + "Be sure to join our discord with /discord!");
        messages.add(ChatColor.RED + "If you find a bug be sure to report in the discord server!");
        messages.add(ChatColor.GREEN + "Did you know: Pressing the offhand key gives a whole new ability!");
        messages.add(ChatColor.GREEN+ "Be sure to post suggestions on the discord server!");
        messages.add(ChatColor.BLUE + "It is my unbiased opinion that this server is really cool!");
    }

    public void start() {
        new BukkitRunnable(){


            @Override
            public void run() {
                int rand = random.nextInt(messages.size());

                for(Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(messages.get(rand));
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, period);

    }
}
