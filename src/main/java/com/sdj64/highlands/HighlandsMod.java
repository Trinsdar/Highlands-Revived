package com.sdj64.highlands;

import java.io.File;

import com.sdj64.highlands.init.HighlandsBiomes;
import com.sdj64.highlands.init.HighlandsBlocks;
import com.sdj64.highlands.generator.GeneratePlants;
import com.sdj64.highlands.generator.GenerateRiverRapids;
import com.sdj64.highlands.generator.GenerateTrees;

import com.sdj64.highlands.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.MC_VERSION +"-" + References.MOD_VERSION)
public class HighlandsMod {

	@SidedProxy(clientSide = "com.sdj64.highlands.proxy.ClientProxy", serverSide = "com.sdj64.highlands.proxy.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance
	public static HighlandsMod instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(proxy);
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
	
	
	
	
	
	
	
	
}
