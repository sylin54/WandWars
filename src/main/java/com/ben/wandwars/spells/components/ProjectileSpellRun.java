package com.ben.wandwars.spells.components;

public abstract class ProjectileSpellRun {


    protected ProjectileSpellComponent projectileSpellComponent;
    boolean isCancelled = false;

    public abstract void run();


    public void cancel() {
        isCancelled = true;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setProjectileSpellComponent(ProjectileSpellComponent projectileSpellComponent) {
        this.projectileSpellComponent = projectileSpellComponent;
    }
}
