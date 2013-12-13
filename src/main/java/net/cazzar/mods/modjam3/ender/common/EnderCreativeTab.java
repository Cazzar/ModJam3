package net.cazzar.mods.modjam3.ender.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @Author: Cayde
 */
public class EnderCreativeTab extends CreativeTabs {
    public EnderCreativeTab() {
        super("cazzar.ender");
    }

    @Override
    public int getTabIconItemIndex() {
        return Item.enderPearl.itemID;
    }
}
