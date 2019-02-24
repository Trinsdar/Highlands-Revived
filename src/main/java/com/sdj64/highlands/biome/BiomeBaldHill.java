package com.sdj64.highlands.biome;

import com.sdj64.highlands.generator.HighlandsGenerators;
import com.sdj64.highlands.init.HighlandsBiomeProperties;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeBaldHill extends BiomeHighlandsBase
{

	public BiomeBaldHill(){
		super(HighlandsBiomeProperties.BALD_HILL);
		
        decorator.treesPerChunk = 0;
        decorator.grassPerChunk = 4;
        decorator.flowersPerChunk = 3;
        
        plants.add(HighlandsGenerators.mcBluet);
        plants.add(HighlandsGenerators.mcAllium);
        
    }

    @Override
	public WorldGenAbstractTree getRandomTreeFeature(Random random)
    {
		return (random.nextInt(10) == 0 ? this.BIG_TREE_FEATURE : this.TREE_FEATURE);
    }
	    
}
