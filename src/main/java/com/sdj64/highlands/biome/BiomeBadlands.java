package com.sdj64.highlands.biome;

import com.sdj64.highlands.generator.HighlandsGenerators;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeBadlands extends BiomeHighlandsBase
{

    private int terrainInt1;
    private int terrainInt2;
    private int terrainInt3;
    private int terrainInt4;
	
	public BiomeBadlands(Properties properties)
    {
        super(properties);
        
        decorator.treesPerChunk = 1;
        decorator.grassPerChunk = 6;
        decorator.flowersPerChunk = 1;

        
        this.terrainInt1 = 0;
        this.terrainInt2 = 1;
        this.terrainInt3 = 2;
        this.terrainInt4 = this.terrainInt1;
        
        plants.add(HighlandsGenerators.thornbush);
        plants.add(HighlandsGenerators.mcOTulip);
        plants.add(HighlandsGenerators.mcBluet);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random random)
    {
        if (random.nextInt(3) != 0){
            return HighlandsGenerators.shrub2Gen;
        }else{
            return this.TREE_FEATURE;
        }

    }
    //(random.nextInt(3) != 0 ? HighlandsGenerators.shrub2Gen :

    @Override
    public void decorate(World world, Random random, BlockPos pos)
    {
        //genStandardOre(6, HighlandsGenerators.STONEInDIRT, 64, 128, world, random, pos);
    	
        super.decorate(world, random, pos);
        
        int i = 3 + random.nextInt(6);
        int j;
        int k;
        int l;

        for (j = 0; j < i; ++j)
        {
            k = random.nextInt(16);
            l = random.nextInt(28) + 4;
            int i1 = random.nextInt(16);
            BlockPos blockpos1 = pos.add(k, l, i1);

            if (world.getBlockState(blockpos1).getBlock().isReplaceableOreGen(world.getBlockState(blockpos1), world, blockpos1, net.minecraft.block.state.pattern.BlockMatcher.forBlock(Blocks.STONE)))
            {
                world.setBlockState(blockpos1, Blocks.EMERALD_ORE.getDefaultState(), 2);
            }
        }
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random random, ChunkPrimer primer, int x, int z, double whatisthis)
    {
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();

        if ((whatisthis < -1.0D || whatisthis > 2.0D) && this.terrainInt4 == this.terrainInt3)
        {
            this.topBlock = Blocks.DIRT.getStateFromMeta(1);
            this.fillerBlock = Blocks.DIRT.getStateFromMeta(1);
        }
        else if (whatisthis > 1.0D && this.terrainInt4 != this.terrainInt2)
        {
            this.topBlock = Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(8);
            this.fillerBlock = Blocks.HARDENED_CLAY.getDefaultState();
        }

        this.generateBiomeTerrain(worldIn, random, primer, x, z, whatisthis);
    }
    

    public int getModdedBiomeGRASSColor(int original)
    {
        return 0xCCB978;
    }
    
    /*
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1)
    {
    	if(HighlandsMain.skyColorFlag)return 0xFFCC8E;
    	else return super.getSkyColorByTemp(par1);
    }
    */
}



