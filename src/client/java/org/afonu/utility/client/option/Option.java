package org.afonu.utility.client.option;

import net.minecraft.item.Item;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Option {
    //Properties by default

    //Other
    public boolean pauseGameOnMenu = true;

    //Attack
    public boolean allowAttackBlock = false;
    public boolean allowAttackEntity = false;
    public boolean enableFilter = false;
    public Map<Item, Integer> ItemMinDurability = new HashMap<Item, Integer>();

    //ArmorHUD
    public boolean armorHUD = false;
    public int armorHUD_positionX = 0;
    public int armorHUD_positionY = 0;

    public boolean itemHUD = false;
    public int itemHUD_positionX = 0;
    public int itemHUD_positionY = 0;

}
