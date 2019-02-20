package com.sdj64.highlands.biome;

import com.sdj64.highlands.generator.HighlandsGenerators;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenLowlands extends BiomeGenBaseHighlands
{

	private int terrainInt1;
    private int terrainInt2;
    private int terrainInt3;
    private int terrainInt4;
	
	public BiomeGenLowlands()
    {
        super(HighlandsBiomeProperties.LOWLANDS);
        
        decorator.treesPerChunk = 2;
        decorator.grassPerChunk = 4;
        decorator.flowersPerChunk = 0;

        decorator.generateFalls = false;
        
        this.terrainInt1 = 0;
        this.terrainInt2 = 1;
        this.terrainInt3 = 2;
        this.terrainInt4 = this.terrainInt1;
        
        plants.add(HighlandsGenerators.blueSwampFlower);
        plants.add(HighlandsGenerators.mcDaisy);
        plants.add(HighlandsGenerators.cattail);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenAbstractTree genBigTreeChance(Random par1Random)
    {
        return (par1Random.nextInt(8) == 0 ?
        		HighlandsGenerators.shrub2Gen : par1Random.nextInt(4) != 0 ?
        		this.TREE_FEATURE : HighlandsGenerators.firGen);
    }

    public void decorate(World world, Random random, BlockPos pos)
    {
        super.decorate(world, random, pos);
        
        genStandardOre(10, HighlandsGenerators.hlwater, 10, 64, world, random, pos);
        genStandardOre(decorator.chunkProviderSettings.diamondCount/2, decorator.diamondGen, decorator.chunkProviderSettings.diamondMinHeight, decorator.chunkProviderSettings.diamondMaxHeight, world, random, pos);
    }
    
    public void genTerrainBlocks(World worldIn, Random random, ChunkPrimer primer, int x, int z, double whatisthis)
    {
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();

        if (whatisthis > 1.0D && this.terrainInt4 != this.terrainInt2)
        {
            this.topBlock = Blocks.STONE.getDefaultState();
            this.fillerBlock = Blocks.STONE.getDefaultState();
        }

        this.generateBiomeTerrain(worldIn, random, primer, x, z, whatisthis);
    }
}
