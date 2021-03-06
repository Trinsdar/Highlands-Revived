package com.sdj64.highlands.biome;

import com.sdj64.highlands.generator.HighlandsGenerators;
import com.sdj64.highlands.init.HighlandsBiomeProperties;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

import java.util.Random;

public class BiomePinelands extends BiomeHighlandsBase
{

	public BiomePinelands()
    {
        super(HighlandsBiomeProperties.PINELANDS);
        
        decorator.treesPerChunk = 3;
        decorator.grassPerChunk = 6;
        decorator.flowersPerChunk = 0;
        
        plants.add(HighlandsGenerators.thornbush);
        plants.add(HighlandsGenerators.blueberryBush);
        plants.add(HighlandsGenerators.raspberryBush);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random)
    {
        return (par1Random.nextInt(2) == 0 ? HighlandsGenerators.shrubGen : new WorldGenTaiga2(false));
    }

    @Override
    public void decorate(World world, Random random, BlockPos pos)
    {
        super.decorate(world, random, pos);
        
        genStandardOre(decorator.chunkProviderSettings.ironCount/2, decorator.ironGen, decorator.chunkProviderSettings.ironMinHeight, decorator.chunkProviderSettings.ironMaxHeight, world, random, pos);

    }
}
