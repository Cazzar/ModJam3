package net.cazzar.mods.modjam3.ender.common.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

/**
 * @Author: Cayde
 */
public class BlockCoord {
    public int x, y, z;

    public BlockCoord(String str) {
        List<String> parts = Lists.newArrayList(Splitter.on(',').limit(3).trimResults().split(str));
        for (String s : parts) {
            List<String> params = Lists.newArrayList(Splitter.on('=').limit(2).omitEmptyStrings().trimResults().split(s));

            if (params.get(0).equals("x")) this.x = Integer.parseInt(params.get(1));
            if (params.get(0).equals("y")) this.y = Integer.parseInt(params.get(1));
            if (params.get(0).equals("z")) this.z = Integer.parseInt(params.get(1));
        }
    }

    @Override
    public String toString() {
        return  "x:" + x +
                ",y:" + y +
                ",z:" + z;
    }

    public TileEntity getTile(World world) {
        return world.getBlockTileEntity(x, y, z);
    }

    public BlockCoord(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
