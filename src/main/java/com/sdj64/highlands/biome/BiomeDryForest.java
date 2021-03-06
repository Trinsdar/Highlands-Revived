package com.sdj64.highlands.biome;

import com.sdj64.highlands.generator.HighlandsGenerators;
import com.sdj64.highlands.init.HighlandsBiomeProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeDryForest extends BiomeHighlandsBase
{

	public BiomeDryForest()
    {
        super(HighlandsBiomeProperties.DRY_FOREST);
        
        decorator.treesPerChunk = 12;
        decorator.grassPerChunk = 10;
        decorator.flowersPerChunk = 1;

        plants.add(HighlandsGenerators.raspberryBush);
        plants.add(HighlandsGenerators.mcRTulip);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random)
    {
    	if(par1Random.nextInt(3) == 0){
            return HighlandsGenerators.aspenGen;
        } else if(par1Random.nextInt(2) == 0){
            return this.TREE_FEATURE;
        } else{
            return HighlandsGenerators.shrub2Gen;
        }
    }

    @Override
    public void decorate(World world, Random random, BlockPos pos)
    {
        super.decorate(world, random, pos);
        
        genStandardOre(2, HighlandsGenerators.hlsand, 0, 72, world, random, pos);
        genStandardOre(decorator.chunkProviderSettings.goldCount/2, decorator.goldGen, decorator.chunkProviderSettings.goldMinHeight, decorator.chunkProviderSettings.goldMaxHeight, world, random, pos);
    }
}
