package com.sylin.spellsystem.util.time;


public class TimerManager {

    int interval;
    TimerCallBack callBack;

    public TimerManager(int interval, TimerCallBack callBack) {
        this.interval = interval;
        this.callBack = callBack;
    }

}
