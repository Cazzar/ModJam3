package net.cazzar.mods.modjam3.ender.blocks;

import net.cazzar.mods.modjam3.ender.Ender;
import net.cazzar.mods.modjam3.ender.blocks.tile.TileEntityTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemEnderEye;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @Author: Cayde
 */
public class BlockEnderTeleporter extends Block {
    public BlockEnderTeleporter(int id) {
        super(id, Material.rock);
        this.setHardness(2.0F);
        this.setCreativeTab(Ender.proxy.tab);
        setUnlocalizedName("enderTeleporter");
//        setTickRandomly(true);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityTeleporter(world, metadata);
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        for (int l = 0; l < 8; l++) {
            world.spawnParticle("portal", x + random.nextFloat(), y + random.nextFloat(), z + random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat());
        }
    }

    @Override
    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
        super.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (player.isSneaking()) {
            ItemStack held = player.getHeldItem();

            if (held.getItem() instanceof ItemEnderEye) {
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                if (tileEntity instanceof TileEntityTeleporter) {
                    if (((TileEntityTeleporter) tileEntity).teleportTo == null) {
                        player.addChatMessage("Teleport Failed!");
                        return true;
                    }

                    held.stackSize--;
                    ((TileEntityTeleporter) tileEntity).teleport(player);
                    return true;
                }
            }
        }

        return false;
    }
}
