package com.ben.wandwars.game;

import java.util.ArrayList;
import java.util.List;

public enum GameType {
    SOLOS("solos",1, 1, 1),
    DOUBLES("doubles" ,2, 2, 2),
    TRIOS("trios" ,3, 3, 3);

    private int players;
    private int[] teamLengths;
    private int neededWins;

    private String gameName;

    GameType(String gameName, int neededWins, int... teamLengths) {
        this.teamLengths = teamLengths;
        this.neededWins = neededWins;

        players = 0;
        for(int team : teamLengths) {
            players += team;
        }
        this.gameName = gameName;
    }
    public int getPlayers() {
        return players;
    }
    public int[] getTeamLengths() {return teamLengths;}
    public int getNeededWins() {return neededWins;}
    public String getName() {return gameName;}

    public static GameType getGameType(String name) {
        for(GameType gameType : GameType.values()) {
            if(gameType.getName().equalsIgnoreCase(name)) {
                return gameType;
            }
        }
        return null;
    }

    public static List<String> getGameNames() {
        List<String> names = new ArrayList<>();

        for(GameType gameType : values()) {
            names.add(gameType.getName());
        }

        return names;
    }
}
