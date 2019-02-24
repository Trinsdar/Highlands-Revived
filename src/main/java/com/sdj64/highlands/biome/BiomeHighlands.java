package com.sdj64.highlands.biome;

import com.sdj64.highlands.generator.HighlandsGenerators;
import com.sdj64.highlands.init.HighlandsBiomeProperties;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeHighlands extends BiomeHighlandsBase
{

	public BiomeHighlands()
    {
        super(HighlandsBiomeProperties.HIGHLANDS);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        
        decorator.treesPerChunk = 3;
        decorator.grassPerChunk = 12;
        decorator.flowersPerChunk = 6;
        
        plants.add(HighlandsGenerators.thornbush);
        plants.add(HighlandsGenerators.lavender);
        plants.add(HighlandsGenerators.cotton);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random random)
    {
        return (random.nextInt(3) != 0 ? HighlandsGenerators.shrub2Gen : this.TREE_FEATURE);
    }

    @Override
    public void decorate(World world, Random random, BlockPos pos)
    {
        super.decorate(world, random, pos);
        
        genStandardOre(decorator.chunkProviderSettings.coalCount/2, decorator.coalGen, decorator.chunkProviderSettings.coalMinHeight, decorator.chunkProviderSettings.coalMaxHeight, world, random, pos);
    }
}
