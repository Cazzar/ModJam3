package net.cazzar.mods.modjam3.ender.items;

import net.cazzar.mods.modjam3.ender.Ender;
import net.cazzar.mods.modjam3.ender.blocks.tile.TileEntityTeleporter;
import net.cazzar.mods.modjam3.ender.common.util.BlockCoord;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @Author: Cayde
 */
public class ItemSheenPearl extends Item {
    public ItemSheenPearl(int id) {
        super(id);
        setCreativeTab(Ender.proxy.tab);
        setMaxStackSize(32);
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int worldX, int worldY, int worldZ, int side, float playerX, float playerY, float playerZ) {
        if (world.isRemote) return false;
        if (player.isSneaking()) return false;

        BlockCoord coord = new BlockCoord(worldX, worldY, worldZ);
        TileEntity tileEntity = coord.getTile(world);
        if (tileEntity instanceof TileEntityTeleporter) {
            TileEntityTeleporter teleporter = (TileEntityTeleporter) tileEntity;
            if (!teleporter.canTeleport()) {
                player.addChatMessage("Teleporter unlinked!");
                return true;
            }

            itemStack.stackSize--;
            teleporter.teleport(player);
        }

        return true;
    }
}
