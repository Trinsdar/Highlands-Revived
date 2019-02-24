package com.sdj64.highlands.biome;

import com.sdj64.highlands.generator.HighlandsGenerators;
import com.sdj64.highlands.init.HighlandsBiomeProperties;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeDunes extends BiomeHighlandsBase
{

	public BiomeDunes()
    {
		super(HighlandsBiomeProperties.DUNES);
		
        decorator.treesPerChunk = 0;
        decorator.grassPerChunk = 0;
        decorator.flowersPerChunk = 0;

        
        this.topBlock = Blocks.SAND.getDefaultState();
        this.fillerBlock = Blocks.SAND.getDefaultState();
        
        this.plants.add(HighlandsGenerators.duneGrass);
        this.plants.add(HighlandsGenerators.empty);
    }

    @Override
	public WorldGenAbstractTree getRandomTreeFeature(Random random)
    {
		return HighlandsGenerators.palmGen;
    }
}
