package com.sdj64.highlands.proxy;

import com.sdj64.highlands.Config;
import com.sdj64.highlands.FuelManager;
import com.sdj64.highlands.HLEventManager;
import com.sdj64.highlands.HighlandsRecipes;
import com.sdj64.highlands.HighlandsSettings;
import com.sdj64.highlands.init.HighlandsBiomeProperties;
import com.sdj64.highlands.generator.GeneratePlants;
import com.sdj64.highlands.generator.GenerateRiverRapids;
import com.sdj64.highlands.generator.GenerateTrees;
import com.sdj64.highlands.init.HighlandsBiomes;
import com.sdj64.highlands.init.HighlandsBlocks;
import com.sdj64.highlands.init.HighlandsItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;

public class CommonProxy {
    public static Configuration config;

    HLEventManager eventMgr = new HLEventManager();
    GenerateTrees genTrees = new GenerateTrees();
    GeneratePlants genPlants = new GeneratePlants();
    GenerateRiverRapids genRRapids = new GenerateRiverRapids();


    public void preInit(FMLPreInitializationEvent event) {

        MinecraftForge.TERRAIN_GEN_BUS.register(eventMgr);
        MinecraftForge.EVENT_BUS.register(eventMgr);

        MinecraftForge.EVENT_BUS.register(HighlandsBlocks.class);
        MinecraftForge.EVENT_BUS.register(HighlandsItems.class);
        HighlandsBiomeProperties.init();
        MinecraftForge.EVENT_BUS.register(HighlandsBiomes.class);

        config = new Configuration(new File(event.getModConfigurationDirectory() + File.separator + "highlands.cfg"));
        config.load();
        Config.setUpConfig(config);
        config.save();


    }

    public void init(FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator(genTrees, 10);
        GameRegistry.registerWorldGenerator(genPlants, 10);
        GameRegistry.registerWorldGenerator(genRRapids, 10);
        HighlandsBlocks.initOredict();
        HighlandsSettings.constructSettings();
        HighlandsRecipes.init();
        GameRegistry.registerFuelHandler(new FuelManager());
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
