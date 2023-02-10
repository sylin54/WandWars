package com.ben.wandwars.game;

public enum GameType {
    SOLOS(1, 1, 1),
    DOUBLES(2, 2, 2),
    TRIOS(3, 3, 3);

    private int players;
    private int[] teamLengths;
    private int neededWins;

    GameType(int neededWins, int... teamLengths) {
        this.teamLengths = teamLengths;
        this.neededWins = neededWins;

        players = 0;
        for(int team : teamLengths) {
            players += team;
        }
    }

    public int getPlayers() {
        return players;
    }
    public int[] getTeamLengths() {return teamLengths;}
    public int getNeededWins() {return neededWins;}
}
