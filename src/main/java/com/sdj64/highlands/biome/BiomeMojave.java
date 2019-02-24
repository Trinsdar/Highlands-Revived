package com.sdj64.highlands.biome;

import com.sdj64.highlands.generator.HighlandsGenerators;
import com.sdj64.highlands.init.HighlandsBiomeProperties;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;

import java.util.Random;

public class BiomeMojave extends BiomeHighlandsBase
{

    private int terrainInt1;
    private int terrainInt2;
    private int terrainInt3;
    private int terrainInt4;
	
	public BiomeMojave(){
		super(HighlandsBiomeProperties.MOJAVE);
		
		decorator.treesPerChunk = 1;
        decorator.grassPerChunk = 5;
        decorator.flowersPerChunk = 0;
        
        this.spawnableCreatureList.clear();
        
        this.terrainInt1 = 0;
        this.terrainInt2 = 1;
        this.terrainInt3 = 2;
        this.terrainInt4 = this.terrainInt1;
        
        plants.add(HighlandsGenerators.thornbush);
        plants.add(HighlandsGenerators.mcBluet);
        plants.add(HighlandsGenerators.duneGrass);
    }


    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random)
    {
        return (par1Random.nextInt(3) == 0 ? new WorldGenSavannaTree(false) : HighlandsGenerators.shrub2Gen);
    }

    @Override
    public void decorate(World world, Random random, BlockPos pos)
    {
        //genStandardOre(6, HighlandsGenerators.sandInDIRT, 64, 120, world, random, pos);
    	
        super.decorate(world, random, pos);
        
        genStandardOre(5, HighlandsGenerators.hlsand, 0, 72, world, random, pos);
        genStandardOre(decorator.chunkProviderSettings.goldCount/2, decorator.goldGen, decorator.chunkProviderSettings.goldMinHeight, decorator.chunkProviderSettings.goldMaxHeight, world, random, pos);
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random random, ChunkPrimer primer, int x, int z, double whatisthis)
    {
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();

        if ((whatisthis < -1.0D || whatisthis > 2.0D) && this.terrainInt4 == this.terrainInt3)
        {
            this.topBlock = Blocks.SAND.getDefaultState();
            this.fillerBlock = Blocks.SAND.getDefaultState();
        }
        else if (whatisthis > 1.0D && this.terrainInt4 != this.terrainInt2)
        {
            this.topBlock = Blocks.SAND.getStateFromMeta(1);
            this.fillerBlock = Blocks.SAND.getStateFromMeta(1);
        }

        this.generateBiomeTerrain(worldIn, random, primer, x, z, whatisthis);
    }
	    
}