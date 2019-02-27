package com.sdj64.highlands.biome;

import com.sdj64.highlands.generator.HighlandsGenerators;
import com.sdj64.highlands.init.HighlandsBiomeProperties;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeLake extends BiomeHighlandsBase
{

	public BiomeLake(Properties properties)
    {
		super(properties);
		
        decorator.treesPerChunk = 3;
        decorator.grassPerChunk = 12;
        decorator.flowersPerChunk = 0;
	    
        this.spawnableCreatureList.clear();
    }

    @Override
	public WorldGenAbstractTree getRandomTreeFeature(Random random)
    {
		return HighlandsGenerators.poplarGen;
    }
}
