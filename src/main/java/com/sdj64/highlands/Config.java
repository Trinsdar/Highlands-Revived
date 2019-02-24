package com.sdj64.highlands;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config
{
	public static String CATEGORY_BIOME_GENERATE = "Generate Biomes true/false?";
	public static String CATEGORY_BIOME_WEIGHT = "Biome Weights";

	public static String CATEGORY_SUBBIOME_GENERATE = "Generate Sub Biomes true/false?";
	public static String CATEGORY_SUBBIOME_WEIGHT = "Sub Biome Weights";
	
	//public static String CATEGORY_VANILLABIOME_GENERATE = "Biomes.Generate Vanilla Biomes true/false?";
	
	
	//Biome Weight Properties
	public static Property adirondackWeight;
	public static Property alpsWeight;
	public static Property badlandsWeight;
	public static Property bambooForestWeight;
	public static Property dryForestWeight;
	public static Property dunesWeight;
	public static Property glacierWeight;
	public static Property greyMtnsWeight;
    public static Property highlandsbWeight;
	public static Property lowlandsWeight;
	public static Property meadowWeight;
	public static Property mojaveWeight;
    public static Property pinelandsWeight;
	public static Property poplarHillsWeight;
    public static Property redwoodForestWeight;
    public static Property tropicalIslandsWeight;

    // SubBiome Weight Properties
	public static Property adirondackFoothillsWeight;
	public static Property alpsFoothillsWeight;
	public static Property badlandsFoothillsWeight;
	public static Property baldHillWeight;
	public static Property greyMtnsFoothillsWeight;
	public static Property lakeWeight;
	public static Property tropHillsWeight;

	//Biome Generate Properties
	public static Property adirondackGenerate;
	public static Property alpsGenerate;
	public static Property badlandsGenerate;
	public static Property bambooForestGenerate;
	public static Property dryForestGenerate;
	public static Property dunesGenerate;
	public static Property glacierGenerate;
	public static Property greyMtnsGenerate;
	public static Property highlandsbGenerate;
	public static Property lowlandsGenerate;
	public static Property meadowGenerate;
	public static Property mojaveGenerate;
	public static Property pinelandsGenerate;
	public static Property poplarHillsGenerate;
	public static Property redwoodForestGenerate;

	//SubBiome Generate Properties
	public static Property tropicalIslandsGenerate;
	public static Property adirondackFoothillsGenerate;
	public static Property alpsFoothillsGenerate;
	public static Property badlandsFoothillsGenerate;
	public static Property baldHillGenerate;
	public static Property greyMtnsFoothillsGenerate;
	public static Property lakeGenerate;
	public static Property tropHillsGenerate;

	//Settings Properties
	//public static Property genDefault;
	public static Property genOre;
	public static Property vanillaBiomeChanges;
	
	
	
	
	
	public static void setUpConfig(Configuration config)
	{
		addBiomeEntries(config);
		addSettingsEntries(config);
	}
	
	private static void addBiomeEntries(Configuration config) 
	{
		//Biomes
		adirondackWeight = config.get(CATEGORY_BIOME_WEIGHT, "Adirondacks Weight", 10);
		adirondackGenerate = config.get(CATEGORY_BIOME_GENERATE, "Adirondacks Generate", true);
		alpsWeight = config.get(CATEGORY_BIOME_WEIGHT, "Alps Weight", 10);
		alpsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Alps Generate", true);
		badlandsWeight = config.get(CATEGORY_BIOME_WEIGHT, "Badlands Weight", 10);
		badlandsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Badlands Generate", true);
		bambooForestWeight = config.get(CATEGORY_BIOME_WEIGHT, "Bamboo Forest Weight", 10);
		bambooForestGenerate = config.get(CATEGORY_BIOME_GENERATE, "Bamboo Forest Generate", true);
		dryForestWeight = config.get(CATEGORY_BIOME_WEIGHT, "Dry Forest Weight", 10);
		dryForestGenerate = config.get(CATEGORY_BIOME_GENERATE, "Dry Forest Generate", true);
		dunesWeight = config.get(CATEGORY_BIOME_WEIGHT, "Dunes Weight", 10);
		dunesGenerate = config.get(CATEGORY_BIOME_GENERATE, "Dunes Generate", true);
		glacierWeight = config.get(CATEGORY_BIOME_WEIGHT, "Glacier Weight", 10);
		glacierGenerate = config.get(CATEGORY_BIOME_GENERATE, "Glacier Generate", true);
		greyMtnsWeight = config.get(CATEGORY_BIOME_WEIGHT, "Grey Mountains Weight", 10);
		greyMtnsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Grey Mountains Generate", true);
		highlandsbWeight = config.get(CATEGORY_BIOME_WEIGHT, "Highlands Weight", 10);
		highlandsbGenerate = config.get(CATEGORY_BIOME_GENERATE, "Highlands Generate", true);
		lowlandsWeight = config.get(CATEGORY_BIOME_WEIGHT, "Lowlands Weight", 10);
		lowlandsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Lowlands Generate", true);
		meadowWeight = config.get(CATEGORY_BIOME_WEIGHT, "Meadow Weight", 10);
		meadowGenerate = config.get(CATEGORY_BIOME_GENERATE, "Meadow Generate", true);
		mojaveWeight = config.get(CATEGORY_BIOME_WEIGHT, "Mojave Weight", 10);
		mojaveGenerate = config.get(CATEGORY_BIOME_GENERATE, "Mojave Generate", true);
		pinelandsWeight = config.get(CATEGORY_BIOME_WEIGHT, "Pinelands Weight", 10);
		pinelandsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Pinelands Generate", true);
		poplarHillsWeight = config.get(CATEGORY_BIOME_WEIGHT, "Poplar Hills Weight", 10);
		poplarHillsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Poplar Hills Generate", true);
		redwoodForestWeight = config.get(CATEGORY_BIOME_WEIGHT, "Redwood Forest Weight", 10);
		redwoodForestGenerate = config.get(CATEGORY_BIOME_GENERATE, "Redwood Forest Generate", true);
		tropicalIslandsWeight = config.get(CATEGORY_BIOME_WEIGHT, "Tropical Islands Weight", 10);
		tropicalIslandsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Tropical Islands Generate", true);

		//SubBiomes
		adirondackFoothillsWeight = config.get(CATEGORY_SUBBIOME_WEIGHT, "Adirondacks Foothills Weight", 10);
		adirondackFoothillsGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Adirondacks Foothills Generate", true);
		alpsFoothillsWeight = config.get(CATEGORY_SUBBIOME_WEIGHT, "Alps Foothills Weight", 10);
		alpsFoothillsGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Alps Foothills Generate", true);
		badlandsFoothillsWeight = config.get(CATEGORY_SUBBIOME_WEIGHT, "Badlands Foothills Weight", 10);
		badlandsFoothillsGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Badlands Foothills Generate", true);
		baldHillWeight = config.get(CATEGORY_SUBBIOME_WEIGHT, "Bald Hill Weight", 10);
		baldHillGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Bald Hill Generate", true);
		greyMtnsFoothillsWeight = config.get(CATEGORY_SUBBIOME_WEIGHT, "Grey Mountains Foothills Weight", 10);
		greyMtnsFoothillsGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Grey Mountains Foothills Generate", true);
		lakeWeight = config.get(CATEGORY_SUBBIOME_WEIGHT, "Lake Weight", 10);
		lakeGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Lake Generate", true);
		tropHillsWeight = config.get(CATEGORY_SUBBIOME_WEIGHT, "Tropical Hills Weight", 10);
		tropHillsGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Tropical Hills Generate", true);
	}

	
	private static void addSettingsEntries(Configuration config) 
	{
		genOre = config.get(config.CATEGORY_GENERAL, "Generate Biome-specific Ores", true);
		genOre.setComment("Set to false to disable extra ores of different types in different biomes.");
		vanillaBiomeChanges = config.get(config.CATEGORY_GENERAL, "Add modifications to vanilla biomes", true);
		vanillaBiomeChanges.setComment("Set to false to disable Highlands trees and small plants in vanilla biomes.");
	}
}
