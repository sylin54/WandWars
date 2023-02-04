package com.ben.wandwars.wands;

public enum WandCoolDownType {
    SHORT(1500),
    MEDIUM(2500),
    LONG(5000);

    long millis;

    WandCoolDownType(long millis) {
        this.millis = millis;
    }

    public long getMillis() {return millis;}
}
