package com.ben.wandwars.displaying;

import com.ben.wandwars.effectsManagment.Effect;
import com.ben.wandwars.effectsManagment.EffectsManager;
import com.ben.wandwars.stateManagers.ManaManager;
import com.ben.wandwars.wands.listeners.DashStateManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class Displayer {
    public static void update(Player player) {



        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("scoreboard", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "WAND WARS");


        int scorePlace = 15;

        //whitespace
        Score whitespace = obj.getScore(" ");
        whitespace.setScore(scorePlace);
        scorePlace--;

        //mana amount
        Score manaAmount = obj.getScore(ChatColor.BLUE + "Mana: " + 3);
        manaAmount.setScore(scorePlace);
        scorePlace--;

        //dash info
        Score dashScore;

        if(DashStateManager.getInstance().hasDash(player)) {
            dashScore = obj.getScore(ChatColor.YELLOW + "Dash: Ready");
        } else {
            dashScore = obj.getScore(ChatColor.YELLOW + "Dash: Recharging");
        }

        dashScore.setScore(scorePlace);
        scorePlace--;

        //whitespace
        Score whitespace1 = obj.getScore("  ");
        whitespace1.setScore(scorePlace);
        scorePlace--;

        //effect headline
        Score effectHeadline = obj.getScore(ChatColor.LIGHT_PURPLE +  "Effects:");
        effectHeadline.setScore(scorePlace);
        scorePlace--;

        //effects (may be none)
        for(Effect effect : EffectsManager.getInstance().getEffects(player)) {
            Score effectDisplay = obj.getScore(effect.getType().getName());
            effectDisplay.setScore(scorePlace);
            scorePlace--;
        }

        //whitespace
        Score whitespace2 = obj.getScore("   ");
        whitespace2.setScore(scorePlace);
        scorePlace--;

        //ip
        Score ip = obj.getScore("helloper.minehut.gg");
        ip.setScore(scorePlace);


        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§BMana: " + ManaManager.getInstance().getMana(player) + " §YDash: " + DashStateManager.getInstance().hasDash(player)));
    }
}
