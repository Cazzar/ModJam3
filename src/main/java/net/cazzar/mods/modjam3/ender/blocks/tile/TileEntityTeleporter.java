package net.cazzar.mods.modjam3.ender.blocks.tile;

import net.cazzar.mods.modjam3.ender.common.util.BlockCoord;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @Author: Cayde
 */
public class TileEntityTeleporter extends TileEntity {
    public BlockCoord teleportTo;

    public TileEntityTeleporter(World world, int metadata) {
        this.worldObj = world;
        this.blockMetadata = metadata;

        System.out.println("TILE LOADED!");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        if (teleportTo != null)
            tag.setString("tele", teleportTo.toString());
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        if (tag.hasKey("tele"))
            teleportTo = new BlockCoord(tag.getString("tele"));
    }
}
