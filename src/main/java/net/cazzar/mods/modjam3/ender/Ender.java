package net.cazzar.mods.modjam3.ender;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.cazzar.mods.modjam3.ender.proxy.CommonProxy;

@Mod(modid = "cazzarender", name = "Ender")
public class Ender {
    @Mod.Instance("cazzarender")
    public static Ender instance;

    @SidedProxy(clientSide = "net.cazzar.mods.modjam3.ender.proxy.ClientProxy", serverSide = "net.cazzar.mods.modjam3.ender.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.initConfig(event.getSuggestedConfigurationFile());
        proxy.initBlocks();
        proxy.initItems();
        proxy.registerRecipes();
    }
}
