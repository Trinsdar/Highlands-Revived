package com.sdj64.highlands.generator;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class GenerateRiverRapids implements IWorldGenerator
{
	
	public static final int SEA_LEVEL = 64;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
						 IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		/*
		if(world.provider.getDimensionId() == 0){
			
			
			for(int i = 0; i < 16; i++){
				for(int k = 0; k < 16; k++){
					int locX = chunkX*16 + i;
					int locZ = chunkZ*16 + k;
					
					BlockPos pos = new BlockPos(locX, 1, locZ);
					Biome biome = world.getBiome(pos);
					
					if(biome.equals(BiomeGenBase.river)){
						BlockPos pos2 = world.getTopSolidOrLiquidBlock(pos);
						
						Biome biome2 = world.getBiome(pos2.north(8));
						if(biome2.equals(BiomeGenBase.river))  biome2 = world.getBiome(pos2.east(8));
						Biome biome3 = world.getBiome(pos2.south(8));
						if(biome3.equals(Biomes.RIVER))  biome3 = world.getBiome(pos2.west(8));
						
						int y = getNewSeaLevel(biome2, biome3);
						for(int j = 0; j < y-pos2.getY(); j++){
							world.setBlockState(pos2.up(j), Blocks.WATER.getDefaultState(), 2);
							Blocks.WATER.updateTick(world, pos2.up(j), Blocks.WATER.getDefaultState(), random);
						}
						
					}
				}
			}
		}
		*/
	}

	
	public int getNewSeaLevel(Biome b1, Biome b2){
		double b1average = b1.getBaseHeight() + b1.getHeightVariation()/2;
		double b2average = b2.getBaseHeight() + b2.getHeightVariation()/2;
		
		if(b2average + b1average > 2)	
			return (int)(SEA_LEVEL + b1average + b2average);
		else return SEA_LEVEL;
	}
}
