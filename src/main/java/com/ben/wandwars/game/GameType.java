package com.ben.wandwars.game;

public enum GameType {
    SOLOS(1, 1),
    DOUBLES(2, 2),
    TRIOS(3, 3);

    int players;
    int[] teamLengths;

    GameType(int... teamLengths) {
        this.teamLengths = teamLengths;

        players = 0;
        for(int team : teamLengths) {
            players += team;
        }
    }

    public int getPlayers() {
        return players;
    }
    public int[] getTeamLengths() {return teamLengths;}
}
