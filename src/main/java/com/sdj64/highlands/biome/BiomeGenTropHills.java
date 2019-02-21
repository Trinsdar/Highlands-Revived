package com.sdj64.highlands.biome;

import com.sdj64.highlands.generator.HighlandsGenerators;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenTropHills extends BiomeGenBaseHighlands
{

	public BiomeGenTropHills()
    {
        super(HighlandsBiomeProperties.TROPICAL_HILLS);
        
        decorator.treesPerChunk = 12;
        decorator.grassPerChunk = 10;
        decorator.flowersPerChunk = 1;
        
        plants.add(HighlandsGenerators.mcOrchid);
        plants.add(HighlandsGenerators.greenLeaf);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random)
    {
        return HighlandsGenerators.eucalyptusGen;
    }

    @Override
    public void decorate(World world, Random random, BlockPos pos)
    {
        super.decorate(world, random, pos);
        
        genStandardOre(decorator.chunkProviderSettings.coalCount/2, decorator.coalGen, decorator.chunkProviderSettings.coalMinHeight, decorator.chunkProviderSettings.coalMaxHeight, world, random, pos);
    }
}
