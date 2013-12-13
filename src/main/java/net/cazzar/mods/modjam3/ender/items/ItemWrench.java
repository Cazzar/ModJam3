package net.cazzar.mods.modjam3.ender.items;

import net.cazzar.mods.modjam3.ender.Ender;
import net.cazzar.mods.modjam3.ender.blocks.tile.TileEntityTeleporter;
import net.cazzar.mods.modjam3.ender.common.util.BlockCoord;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @Author: Cayde
 */
public class ItemWrench extends Item {
    public ItemWrench(int id) {
        super(id);
        setCreativeTab(Ender.proxy.tab);

    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        itemIcon = par1IconRegister.registerIcon("ender:wrench");
    }

    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par1ItemStack.stackTagCompound == null)
            par1ItemStack.setTagCompound(new NBTTagCompound());
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int worldX, int worldY, int worldZ, int side, float playerX, float playerY, float playerZ) {
        if (world.isRemote) return false;
        if (world.getBlockTileEntity(worldX, worldY, worldZ) instanceof TileEntityTeleporter) {
            if (!itemStack.getTagCompound().hasKey("link")) {
                BlockCoord pos = new BlockCoord(worldX, worldY, worldZ);
                if (((TileEntityTeleporter) pos.getTile(world)).teleportTo != null) {
                    player.addChatMessage("Linking failed!");
                    return false;
                }
                player.addChatMessage("Started linking...");
                itemStack.getTagCompound().setString("link", pos.toString());
            } else {
                BlockCoord posOther = new BlockCoord(itemStack.getTagCompound().getString("link"));
                BlockCoord posThis = new BlockCoord(worldX, worldY, worldZ);
                TileEntityTeleporter tile = (TileEntityTeleporter) posThis.getTile(world);
                TileEntityTeleporter other = (TileEntityTeleporter) posOther.getTile(world);

                if (other.teleportTo != null) {
                    player.addChatMessage("Link failed!");
                    itemStack.getTagCompound().removeTag("link");
                    return false;
                }

                tile.teleportTo = posOther;
                other.teleportTo = posThis;


                itemStack.getTagCompound().removeTag("link");
                player.addChatMessage("Linked!");
            }
            return true;
        }

        return true;
    }
}
