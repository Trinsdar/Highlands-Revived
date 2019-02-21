package com.sdj64.highlands.init;

import com.sdj64.highlands.Config;
import com.sdj64.highlands.HighlandsSettings;
import com.sdj64.highlands.References;
import com.sdj64.highlands.biome.BiomeGenAdirondacks;
import com.sdj64.highlands.biome.BiomeGenAlps;
import com.sdj64.highlands.biome.BiomeGenBadlands;
import com.sdj64.highlands.biome.BiomeGenBaldHill;
import com.sdj64.highlands.biome.BiomeGenBambooForest;
import com.sdj64.highlands.biome.BiomeGenBaseHighlands;
import com.sdj64.highlands.biome.BiomeGenDryForest;
import com.sdj64.highlands.biome.BiomeGenDunes;
import com.sdj64.highlands.biome.BiomeGenGreyMountains;
import com.sdj64.highlands.biome.BiomeGenHighlands;
import com.sdj64.highlands.biome.BiomeGenLake;
import com.sdj64.highlands.biome.BiomeGenLowlands;
import com.sdj64.highlands.biome.BiomeGenMeadow;
import com.sdj64.highlands.biome.BiomeGenMojave;
import com.sdj64.highlands.biome.BiomeGenPinelands;
import com.sdj64.highlands.biome.BiomeGenPoplarHills;
import com.sdj64.highlands.biome.BiomeGenRedwoodForest;
import com.sdj64.highlands.biome.BiomeGenTropHills;
import com.sdj64.highlands.biome.BiomeGenTropicalIslands;
import com.sdj64.highlands.biome.HighlandsBiomeProperties;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/*
 * Highlands biomes - Highlands API
 * 
 * This class contains all of the biomes for Highlands.
 * Only access this class in Post Initialization!
 * The values are populated during Highlands initialization.
 */
public class HighlandsBiomes {

	//main biomes
	public static Biome adirondack;
	public static Biome alps;
	public static Biome badlands;
	public static Biome bambooForest;
	public static Biome dryForest;
	public static Biome dunes;
	public static Biome greyMtns;
	public static Biome highlandsBiome;
	public static Biome lowlands;
	public static Biome meadow;
	public static Biome mojave;
	public static Biome pinelands;
	public static Biome poplarHills;
	public static Biome redwoodForest;
	public static Biome tropHills;

	//Sub Biomes
	public static Biome adirondackFoothills;
	public static Biome alpsFoothills;
	public static Biome badlandsFoothills;
	public static Biome baldHill;
	public static Biome greyMtnsFoothills;
	public static Biome lake;
	public static Biome tropicalIslands;

	//unused variables, not sure if sdj64 intended to implement them.
	public static Biome autumnForest;
    public static Biome tropicDryForest;

    //ArrayList of biomes for the Highlands worldtype
    public static ArrayList<Biome> biomesForHighlands = new ArrayList<Biome>();
    
    //ArrayList of Highlands biomes not including default ones, these will be added to the default world
    //Currently not used since BiomeManager doesn't really do different biomes for different world types
    //public static ArrayList<Biome> biomesForDefault = new ArrayList<Biome>();
    
    //ArrayList of sub-biomes, controls which Highlands biomes generate as sub-biomes (currently used for Lake and Bald Hill)
    public static ArrayList<Biome> subBiomes = new ArrayList<Biome>();
    
    //ArrayList of biomes that have foothills, not that are foothills.
    public static ArrayList<Biome> foothillsBiomes = new ArrayList<Biome>();
    

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event)
	{
		int weight;
		//main biomes
		if(Config.adirondackGenerate.getBoolean(true))
		{
			adirondack = registerBiome(event, new BiomeGenAdirondacks(HighlandsBiomeProperties.ADIRONDACKS), "adirondack");
			weight = Config.adirondackWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(adirondack, weight));
				BiomeManager.addSpawnBiome(adirondack);
			}

		}
		if(Config.alpsGenerate.getBoolean(true))
		{
			alps = registerBiome(event, new BiomeGenAlps(HighlandsBiomeProperties.ALPS), "alps");
			weight = Config.alpsWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.ICY, new BiomeManager.BiomeEntry(alps, weight));
				BiomeManager.addSpawnBiome(alps);
			}
		}
		if(Config.badlandsGenerate.getBoolean(true))
		{
			badlands = registerBiome(event, new BiomeGenBadlands(HighlandsBiomeProperties.BADLANDS), "badlands");
			weight = Config.badlandsWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(badlands, weight));
				BiomeManager.addSpawnBiome(badlands);
			}
		}
		if(Config.bambooForestGenerate.getBoolean(true))
		{
			bambooForest = registerBiome(event, new BiomeGenBambooForest(), "bamboo_forest");
			weight = Config.bambooForestWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.DESERT, new BiomeManager.BiomeEntry(bambooForest, weight));
				BiomeManager.addSpawnBiome(bambooForest);
			}
		}
		if(Config.dryForestGenerate.getBoolean(true))
		{
			dryForest = registerBiome(event, new BiomeGenDryForest(), "dry_forest");
			weight = Config.dryForestWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.DESERT, new BiomeManager.BiomeEntry(dryForest, weight));
				BiomeManager.addSpawnBiome(dryForest);
			}
		}
		if(Config.dunesGenerate.getBoolean(true))
		{
			dunes = registerBiome(event, new BiomeGenDunes(), "dunes");
			weight = Config.dunesWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(dunes, weight));
				BiomeManager.addSpawnBiome(dunes);
			}
		}
		if(Config.greyMtnsGenerate.getBoolean(true))
		{
			greyMtns = registerBiome(event, new BiomeGenGreyMountains(HighlandsBiomeProperties.GREY_MOUNTAINS), "grey_mountains");
			weight = Config.greyMtnsWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(greyMtns, weight));
				BiomeManager.addSpawnBiome(greyMtns);
			}
		}
		if(Config.highlandsbGenerate.getBoolean(true))
		{
			highlandsBiome = registerBiome(event, new BiomeGenHighlands(), "highlands");
			weight = Config.highlandsbWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(highlandsBiome, weight));
				BiomeManager.addSpawnBiome(highlandsBiome);
			}
		}
		if(Config.lowlandsGenerate.getBoolean(true))
		{
			lowlands = registerBiome(event, new BiomeGenLowlands(), "lowlands");
			weight = Config.lowlandsWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(lowlands, weight));
				BiomeManager.addSpawnBiome(lowlands);
			}
		}
		if(Config.meadowGenerate.getBoolean(true))
		{
			meadow = registerBiome(event, new BiomeGenMeadow(), "meadow");
			weight = Config.meadowWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(meadow, weight));
				BiomeManager.addSpawnBiome(meadow);
			}
		}
		if(Config.mojaveGenerate.getBoolean(true))
		{
			mojave = registerBiome(event, new BiomeGenMojave(), "mojave");
			weight = Config.mojaveWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.DESERT, new BiomeManager.BiomeEntry(mojave, weight));
				BiomeManager.addSpawnBiome(mojave);
			}
		}
		if(Config.pinelandsGenerate.getBoolean(true))
		{
			pinelands = registerBiome(event, new BiomeGenPinelands(), "pinelands");
			weight = Config.pinelandsWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(pinelands, weight));
				BiomeManager.addSpawnBiome(pinelands);
			}
		}
		if(Config.poplarHillsGenerate.getBoolean(true))
		{
			poplarHills = registerBiome(event, new BiomeGenPoplarHills(), "poplar_hills");
			weight = Config.poplarHillsWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(poplarHills, weight));
				BiomeManager.addSpawnBiome(poplarHills);
			}
		}
		if(Config.redwoodForestGenerate.getBoolean(true))
		{
			redwoodForest = registerBiome(event, new BiomeGenRedwoodForest(), "redwood_forest");
			weight = Config.redwoodForestWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(redwoodForest, weight));
				BiomeManager.addSpawnBiome(redwoodForest);
			}
		}
		if(Config.tropHillsGenerate.getBoolean(true))
		{
			tropHills = registerBiome(event, new BiomeGenTropHills(), "tropical_hills");
			weight = Config.tropHillsWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(tropHills, weight));
				BiomeManager.addSpawnBiome(tropHills);
			}
		}

		//sub-biomes
		if (Config.adirondackFoothillsGenerate.getBoolean(true)){
			adirondackFoothills = registerBiome(event, new BiomeGenAdirondacks(HighlandsBiomeProperties.ADIRONDACKS_FOOTHILLS), "adirondack_foothills");
			weight = Config.adirondackFoothillsWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(adirondackFoothills, weight));
				BiomeManager.addSpawnBiome(adirondackFoothills);
			}
		}
		if (Config.alpsFoothillsGenerate.getBoolean(true)){
			alpsFoothills = registerBiome(event, new BiomeGenAlps(HighlandsBiomeProperties.ALPS_FOOTHILLS), "alps_foothills");
			weight = Config.alpsFoothillsWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.ICY, new BiomeManager.BiomeEntry(alpsFoothills, weight));
				BiomeManager.addSpawnBiome(alpsFoothills);
			}
		}
		if (Config.badlandsFoothillsGenerate.getBoolean(true)){
			badlandsFoothills = registerBiome(event, new BiomeGenBadlands(HighlandsBiomeProperties.BADLANDS_FOOTHILLS), "badlands_foothills");
			weight = Config.badlandsFoothillsWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(badlandsFoothills, weight));
				BiomeManager.addSpawnBiome(badlandsFoothills);
			}
		}
		if(Config.baldHillGenerate.getBoolean(true))
		{
			baldHill = registerBiome(event, new BiomeGenBaldHill(), "bald_hill");
			weight = Config.baldHillWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(baldHill, weight));
				BiomeManager.addSpawnBiome(baldHill);
			}
		}
		if (Config.greyMtnsFoothillsGenerate.getBoolean(true)){
			greyMtnsFoothills = registerBiome(event, new BiomeGenGreyMountains(HighlandsBiomeProperties.GREY_MOUNTAINS_FOOTHILLS), "grey_mountains_foothills");
			weight = Config.greyMtnsFoothillsWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(greyMtnsFoothills, weight));
				BiomeManager.addSpawnBiome(greyMtnsFoothills);
			}
		}
		if(Config.lakeGenerate.getBoolean(true))
		{
			lake = registerBiome(event, new BiomeGenLake(), "lake");
			weight = Config.lakeWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(lake, weight));
				BiomeManager.addSpawnBiome(lake);
			}
		}
		if(Config.tropicalIslandsGenerate.getBoolean(true))
		{
			tropicalIslands = registerBiome(event, new BiomeGenTropicalIslands(), "tropical_islands");
			weight = Config.tropicalIslandsWeight.getInt();
			if (weight > 0){
				BiomeManager.addBiome(BiomeType.WARM, new BiomeManager.BiomeEntry(tropicalIslands, weight));
				BiomeManager.addSpawnBiome(tropicalIslands);
			}
		}
	}

	private static Biome registerBiome(RegistryEvent.Register<Biome> event, Biome biome, String name) {
		biome.setRegistryName(References.MOD_ID, name);

		event.getRegistry().register(biome);

		return biome;
	}
	
	
	//sets up sub-biome lists after all biomes are initialized.
//	public static void setUpAllSubBiomes()
//	{
//		ArrayList<Biome> enabledBiomes = new ArrayList<Biome>();
//		for(Biome b : biomesForHighlands)enabledBiomes.add(b);
//		for(Biome b : subBiomes)enabledBiomes.add(b);
//
//		addSubBiome(alps, Biome.frozenRiver, enabledBiomes);
//		addSubBiome(autumnForest, baldHill, enabledBiomes);
//		addSubBiome(autumnForest, lake, enabledBiomes);
//		addSubBiome(poplarHills, meadow, enabledBiomes);
//		addSubBiome(poplarHills, lake, enabledBiomes);
//		addSubBiome(meadow, lake, enabledBiomes);
//		addSubBiome(highlandsBiome, Biome.forest, enabledBiomes);
//		addSubBiome(pinelands, autumnForest, enabledBiomes);
//		addSubBiome(redwoodForest, highlandsBiome, enabledBiomes);
//		addSubBiome(redwoodForest, lake, enabledBiomes);
//		addSubBiome(mojave, Biome.mesa, enabledBiomes);
//		addSubBiome(mojave, Biome.savanna, enabledBiomes);
//		addSubBiome(tropHills, lake, enabledBiomes);
//		addSubBiome(dryForest, Biome.savanna, enabledBiomes);
//	}

	public static void addSubBiome(Biome parent, Biome sub, ArrayList<Biome> list)
	{
		if(parent != null && sub != null && list.contains(parent) && list.contains(sub) && parent instanceof BiomeGenBaseHighlands){
			((BiomeGenBaseHighlands)parent).subBiomes.add(sub);
		}
	}
	
	/*public static void setUpBiomeManager()
	{
		for(int i = 0; i < biomesForHighlands.size(); i++)
		{
			Biome hlb = biomesForHighlands.get(i);
			if(!(hlb == null))
			{
				//System.out.println(hlb.biomeName + " has been added to the biome list.");

				BiomeEntry entry = new BiomeEntry(hlb, 10);
				BiomeType type = (hlb.temperature < 0.3 ? BiomeType.ICY : hlb.temperature < 0.5 ? BiomeType.COOL
						: hlb.temperature < 1.0 ? BiomeType.WARM : BiomeType.DESERT);
				BiomeManager.addBiome(type, entry);
				if(hlb.temperature >= 0.5 && hlb.temperature <= 0.7)
					BiomeManager.addBiome(BiomeType.COOL, entry);
				if(hlb.temperature >= 0.9 && hlb.temperature <= 1.0)
					BiomeManager.addBiome(BiomeType.DESERT, entry);
				BiomeManager.addSpawnBiome(hlb);
				BiomeManager.addStrongholdBiome(hlb);
				if(hlb.equals(meadow) || hlb.equals(highlandsBiome)
						|| hlb.equals(lowlands) || hlb.equals(mojave))
					BiomeManager.addVillageBiome(hlb, true);
			}

		}
		if(HighlandsSettings.vanillaBiomeChanges)
			BiomeManager.addVillageBiome(Biome.icePlains, true);

		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(Biome.desert, 10));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(Biome.savanna, 10));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(Biome.mesaPlateau, 5));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(Biome.mesaPlateau_F, 5));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(Biome.mesa, 5));

		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(Biome.jungle, 10));

		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(Biome.megaTaiga, 10));
	}
	
	
	public static void modifyVanillaBiomes(){

		if(HighlandsSettings.vanillaBiomeChanges){

			Biome.extremeHills.minHeight = 1.0F;
			Biome.swampland.minHeight = -0.1F;
			Biome.savannaPlateau.minHeight = 1.0F;
			Biome.STONEBeach.maxHeight = 0.5F;
			Biome.river.minHeight = -0.8F;
			Biome.river.maxHeight = 0.0F;
		}
	}*/
}







