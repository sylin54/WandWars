package com.ben.wandwars.wands.wandInterfaces;

import com.ben.wandwars.wands.WandCoolDownType;

public interface WandCoolDown {
    WandCoolDownType getRightClickCoolDown();
    WandCoolDownType getLeftClickCoolDown();
    WandCoolDownType getAbilityCoolDown();
}
