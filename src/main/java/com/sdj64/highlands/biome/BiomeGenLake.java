package com.sdj64.highlands.biome;

import com.sdj64.highlands.generator.HighlandsGenerators;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenLake extends BiomeGenBaseHighlands
{

	public BiomeGenLake()
    {
		super(HighlandsBiomeProperties.LAKE);
		
        decorator.treesPerChunk = 3;
        decorator.grassPerChunk = 12;
        decorator.flowersPerChunk = 0;
	    
        this.spawnableCreatureList.clear();
    }
	
	public WorldGenAbstractTree genBigTreeChance(Random random)
    {
		return HighlandsGenerators.poplarGen;
    }
}
