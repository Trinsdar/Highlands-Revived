package com.sdj64.highlands.biome;

import com.sdj64.highlands.generator.HighlandsGenerators;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenBaldHill extends BiomeGenBaseHighlands
{

	public BiomeGenBaldHill(){
		super(HighlandsBiomeProperties.BALD_HILL);
		
        decorator.treesPerChunk = 0;
        decorator.grassPerChunk = 4;
        decorator.flowersPerChunk = 3;
        
        plants.add(HighlandsGenerators.mcBluet);
        plants.add(HighlandsGenerators.mcAllium);
        
    }

	public WorldGenAbstractTree genBigTreeChance(Random random)
    {
		return (random.nextInt(10) == 0 ? this.BIG_TREE_FEATURE : this.TREE_FEATURE);
    }
	    
}

