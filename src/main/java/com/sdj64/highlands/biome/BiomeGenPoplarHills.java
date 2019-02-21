package com.sdj64.highlands.biome;

import com.sdj64.highlands.generator.HighlandsGenerators;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenPoplarHills extends BiomeGenBaseHighlands
{

	public BiomeGenPoplarHills()
    {
        super(HighlandsBiomeProperties.POPLAR_HILLS);
        
        decorator.treesPerChunk = 6;
        decorator.grassPerChunk = 10;
        decorator.flowersPerChunk = 4;
        
        plants.add(HighlandsGenerators.mcOrchid);
        plants.add(HighlandsGenerators.mcDaisy);
    }
	
    
    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random)
    {
        return HighlandsGenerators.poplarGen;
    }

    @Override
    public void decorate(World world, Random random, BlockPos pos)
    {
        super.decorate(world, random, pos);
        
        genStandardOre(10, HighlandsGenerators.hlwater, 10, 64, world, random, pos);
        genStandardOre(decorator.chunkProviderSettings.diamondCount/2, decorator.diamondGen, decorator.chunkProviderSettings.diamondMinHeight, decorator.chunkProviderSettings.diamondMaxHeight, world, random, pos);
    }
}
