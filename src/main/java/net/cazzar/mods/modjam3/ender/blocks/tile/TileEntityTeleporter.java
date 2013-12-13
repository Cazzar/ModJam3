package net.cazzar.mods.modjam3.ender.blocks.tile;

import net.cazzar.mods.modjam3.ender.common.util.BlockCoord;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @Author: Cayde
 */
public class TileEntityTeleporter extends TileEntity {
    public BlockCoord teleportTo;

    public TileEntityTeleporter() {
        teleportTo = null;
    }

    public TileEntityTeleporter(World world, int metadata) {
        this.worldObj = world;
        this.blockMetadata = metadata;
        teleportTo = null;
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

    public void teleport(EntityPlayer player) {
        player.setPositionAndUpdate(teleportTo.x + 0.5D, teleportTo.y + 1, teleportTo.z + 0.5D);
        player.worldObj.spawnParticle("explode", player.posX, player.posY, player.posZ, 1, 1, 1);
    }

    public boolean canTeleport() {
        return teleportTo != null;
    }
}
