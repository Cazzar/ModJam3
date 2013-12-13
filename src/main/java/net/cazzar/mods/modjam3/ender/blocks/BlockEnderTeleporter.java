package net.cazzar.mods.modjam3.ender.blocks;

import net.cazzar.mods.modjam3.ender.Ender;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @Author: Cayde
 */
public class BlockEnderTeleporter extends Block {
    public BlockEnderTeleporter(int id) {
        super(id, Material.rock);
        this.setHardness(2.0F);
        this.setCreativeTab(Ender.proxy.tab);
    }
}
