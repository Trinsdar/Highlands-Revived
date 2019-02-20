package com.sdj64.highlands.biome;

import com.sdj64.highlands.generator.HighlandsGenerators;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenDunes extends BiomeGenBaseHighlands
{

	public BiomeGenDunes()
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
	
	public WorldGenAbstractTree genBigTreeChance(Random random)
    {
		return HighlandsGenerators.palmGen;
    }
}
