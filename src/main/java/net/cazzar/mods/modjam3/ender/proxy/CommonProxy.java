package net.cazzar.mods.modjam3.ender.proxy;

import cpw.mods.fml.common.registry.GameRegistry;
import net.cazzar.mods.modjam3.ender.blocks.BlockEnderTeleporter;
import net.cazzar.mods.modjam3.ender.common.Config;
import net.cazzar.mods.modjam3.ender.common.EnderCreativeTab;
import net.minecraftforge.common.Configuration;

import java.io.File;

/**
 * @Author: Cayde
 */
public class CommonProxy {
    public EnderCreativeTab tab = new EnderCreativeTab();
    public Config config;

    public static class Blocks {
        public static BlockEnderTeleporter enderTeleporter;
    }

    public void initConfig(File configFile) {
        Configuration configuration = new Configuration(configFile);
        config = new Config();

        config.teleporterID = configuration.get("block", "teleporterID", config.teleporterID).getInt();
    }

    public void initBlocks() {
        if (config == null) throw new RuntimeException("initConfig() has not been called yet.");

        GameRegistry.registerBlock(Blocks.enderTeleporter = new BlockEnderTeleporter(config.teleporterID), "enderTeleporter");
    }
}
