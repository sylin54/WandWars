package com.ben.wandwars;

import com.ben.wandwars.commands.DiscordCommand;
import com.ben.wandwars.commands.MapCommand;
import com.ben.wandwars.commands.QueueCommand;
import com.ben.wandwars.commands.WandCommand;
import com.ben.wandwars.commands.wandsCommand.WandsCommand;
import com.ben.wandwars.commands.wandsCommand.WandsInventoryListener;
import com.ben.wandwars.game.GameCaller;
import com.ben.wandwars.stateManagers.ManaManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/*
todo:
    finish the cooldown manager callback system
    add the death message logic


needed classes:

    todo:
    wands
    finish the stats screen
    redo part of the moving particle list interface
    find a bettter way to do: int getRandomID(GameType gameType)/MapTemplateManager and the equilivant in the map manager class







 */
public final class Main extends JavaPlugin {

    private static Main instance;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        Bukkit.getPluginManager().registerEvents(ManaManager.getInstance(), this);
        Bukkit.getPluginManager().registerEvents(DeathMessageHandling.getInstance(), this);
        Bukkit.getPluginManager().registerEvents(new MiscListener(), this);
        Bukkit.getPluginManager().registerEvents(new WandsInventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(GameCaller.getInstance(), this);

        getCommand("wand").setExecutor(new WandCommand());
        getCommand("wands").setExecutor(new WandsCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("map").setExecutor(new MapCommand());
        getCommand("queue").setExecutor(new QueueCommand());

        DelayedMessages delayedMessages = new DelayedMessages(10 * 60 * 20);
        delayedMessages.start();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }

    public static boolean isTransparent(Material material) {
        List<Material> transparentBlocks = new ArrayList<>();
        transparentBlocks.add(Material.CAVE_AIR);
        transparentBlocks.add(Material.AIR);
        transparentBlocks.add(Material.WATER);
        transparentBlocks.add(Material.VINE);
        transparentBlocks.add(Material.CAVE_VINES);
        transparentBlocks.add(Material.CAVE_VINES_PLANT);
        transparentBlocks.add(Material.TALL_GRASS);
        transparentBlocks.add(Material.TALL_SEAGRASS);
        transparentBlocks.add(Material.DEAD_BUSH);
        transparentBlocks.add(Material.ROSE_BUSH);
        transparentBlocks.add(Material.SWEET_BERRY_BUSH);
        transparentBlocks.add(Material.GRASS);
        transparentBlocks.add(Material.LARGE_FERN);
        transparentBlocks.add(Material.DANDELION);
        transparentBlocks.add(Material.POPPY);
        transparentBlocks.add(Material.SEAGRASS);
        transparentBlocks.add(Material.LADDER);

        return transparentBlocks.contains(material);
    }

    public static Main getInstance() {return instance;}

}
