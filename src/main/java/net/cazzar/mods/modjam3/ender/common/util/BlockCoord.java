package net.cazzar.mods.modjam3.ender.common.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Author: Cayde
 */
public class BlockCoord {
    public double x, y, z;

    public BlockCoord(String str) {
        List<String> parts = Lists.newArrayList(Splitter.on(',').limit(3).trimResults().split(str));
        for (String s : parts) {
            List<String> parts = Lists.newArrayList(Splitter.on('=').limit(2).omitEmptyStrings().trimResults().split(s));

            if (parts.get(0).equals("x")) this.x = Double.parseDouble(parts.get(1));
            if (parts.get(0).equals("y")) this.y = Double.parseDouble(parts.get(1));
            if (parts.get(0).equals("z")) this.z = Double.parseDouble(parts.get(1));
        }
    }

    @Override
    public String toString() {
        return  "x:" + x +
                ",y:" + y +
                ",z:" + z;
    }

    public BlockCoord(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
