package com.ben.wandwars.helpers;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class CompleteSound {
    private Sound sound;
    private float volume;
    private float pitch;

    public CompleteSound(Sound sound, float volume, float pitch){
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    //functions for playing the sound. this is for ease
    public void playSound(Player player) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

    public void playSound(Player player, Location location) {
        player.playSound(location, sound, volume, pitch);
    }

    public void playSound(Location location) {
        location.getWorld().playSound(location, sound, volume, pitch);
    }

    //getters
    public Sound getSound() {
        return sound;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }

    //setter

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setSound(CompleteSound completeSound) {
        this.sound = completeSound.getSound();
        this.volume = completeSound.getVolume();
        this.pitch = completeSound.getPitch();
    }
}
