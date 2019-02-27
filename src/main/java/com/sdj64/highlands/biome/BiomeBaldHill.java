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
        
    }

	    
}

