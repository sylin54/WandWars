package com.ben.wandwars.game;

public enum GameType {
    SOLOS(2),
    DOUBLES(4),
    TRIOS(6);

    int players;

    GameType(int players) {
        this.players = players;
    }

    public int getPlayers() {
        return players;
    }
}
