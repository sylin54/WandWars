package com.ben.wandwars.wands.items;

import com.ben.wandwars.helpers.itemStackHelping.ItemStackHelper;
import com.ben.wandwars.wands.AbilityInf;
import com.ben.wandwars.wands.Wand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/*
(Idea of this is to be a snipe build with some mobility and team play. Pistols are also a good poke/dmg option which should hopefully make the build more fast paced)
Explosion wand:
(poke based attack, good for more neutral moments)
left click:
pistol shot: slight homing, low cooldown, shoot twice in a shot, fast bullet speed, low mana

(pretty self explanotary move. can work well with team play. I'm gonna try to have a fun synergy with pistol, having a good balance of mid dmg/long poke and long/mid damage)
right click:
Snipe: medum cooldown, high damage, basically current right click but shorter delay and lower area, medium mana

(this is mainly meant for teamplay, but it can also play well in 1v1's. This might be a bit op, but I also think it'll make some interactions more fun. This shouldn't be to op for 1v1's since the wand is mainly gonna be focusing on targetting this player
shift left click:
Mark: shoots an invisible raycast, now damage, gives an effect that gives a damage multiple on the hit player, super high mana, super mana

(This is mainly meant for duels where the player can avoid a bit of damage with some skill, also needs the opponent to bait out the shield to win some interactions, which might make some matchups more fun)
shift right click:
parry: small shield, low projectile time, doesn't stop all projctiles stronger ones don't get stopped, medium delay, low mana

(just a tool to keep the opponents away, probably isn't gonna give to much blindness so probably gonna be an escape tool. Also the low proj speed makes it more easiyl escapable)
drop key:
smokebomb: high cooldown, very low range, high mana

(slightly good for mobility, mostly gonna be for getting to high places
offhand:
double jump: high jump, very high cooldown, can be stacked, medium mana

(Idea is a high damage, melee, disruptor build, can also pull enemies into worse positions)
Dark Wand:
left click:
slash: low mana, high damage, short range slash attack, low mana

right click:
bolt: short delay(doesn't stop aiming), high speed, does blind and slow on hit, homing, medium mana, medium cooldown

(is probably gonna need balacing for team play, idea is to seriously punish careless sniper builds. might be comboed with )
shift left click:
telegraphic pull: pulls enemies towards the caster. mid range.

(very similar to snipe wand, but can punish more. Which fits the punish-based wand)
shift right click:
deflects: small shield, low projectile time, doesn't stop all projctiles stronger ones don't get stopped but can stop stronger ones then snipe wand, medium delay, low mana, is a slash, deflects projectiles

drop key:
grand slam:
air:
(not a really good ambush option since the high kb, I imagine it mainly be used as a mixup. could also be good to kb them to a wall with a melee followup)
jumps down into the ground, does more damage and kb the higher jumped from. Also can afflict slowness and blindness depending on how high it starts from
(is a good escape, I imagine this move could be dodged by a skillfull player)
ground: jumps up then does a small slam, high kb

offhand:
Dash: just an extra dash, medium mana, medium cooldown

Moves:
mellee wand

parry shield
moving forward shield

mace swing



Light Wand
Left click:
Slash: low kb, low mana, medium damage, low cooldown

Right click:
heal ray: no cooldown, very low health heal, low mana(but takes a ton of mana to heal a bit), medium range

shift left click:
Large shield: Summons a large, moving forward shield, that blocks most attacks. high mana, high cooldown

shift right click:
parry: same as sniper wnad, low mana, medium cooldown

offhand key:
mace swing: same as current mace swing, high mana, high cooldown

drop key:
team buff: gives a buff to the surrounding team in a circular area


 */

public class ExplosionWand extends Wand {
    @Override
    public ItemStack getItem() {
        return ItemStackHelper.createItemStack("Test Explosion Wand", Material.STICK);
    }

    @Override
    public void leftClickCast(Player caster) {
        caster.sendMessage("left click cast");
    }

    @Override
    public void rightClickCast(Player caster) {
        caster.sendMessage("right click cast");
    }

    @Override
    public void offHandCast(Player caster) {
        caster.sendMessage("offhand cast");
    }

    @Override
    public void shiftLeftClickCast(Player caster) {
        caster.sendMessage("Shift left click cast");
    }

    @Override
    public void shiftRightClickCast(Player caster) {
        caster.sendMessage("Shift right click cast");
    }

    @Override
    public void dropKeyCast(Player caster) {
        caster.sendMessage("drop key cast");
    }

    @Override
    public AbilityInf getLeftClickCastInf(Player caster) {
        return new AbilityInf(10);
    }

    @Override
    public AbilityInf getRightClickCastInf(Player caster) {
        return new AbilityInf(10);
    }

    @Override
    public AbilityInf getOffHandCastInf(Player caster) {
        return new AbilityInf(10);
    }

    @Override
    public AbilityInf getShiftLeftClickCastInf(Player caster) {
        return new AbilityInf(10);
    }

    @Override
    public AbilityInf getShiftRightClickCastInf(Player caster) {
        return new AbilityInf(10);
    }

    @Override
    public AbilityInf getDropKeyCast(Player caster) {
        return new AbilityInf(10);
    }
}
