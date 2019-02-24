package com.sdj64.highlands.generator;

import com.sdj64.highlands.HighlandsSettings;
import com.sdj64.highlands.biome.BiomeHighlandsBase;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class GeneratePlants implements IWorldGenerator
{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
						 IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == 0){
			int locX = chunkX*16 + random.nextInt(16);
			int locZ = chunkZ*16 + random.nextInt(16);
			BlockPos pos = new BlockPos(locX, 1, locZ);
			BlockPos pos2 = world.getTopSolidOrLiquidBlock(pos);
			
			Biome biome = world.getBiome(pos);
			
			if(biome instanceof BiomeHighlandsBase){
				
				
				int l = ((BiomeHighlandsBase) biome).plants.size();
				if(l > 0){
					((BiomeHighlandsBase) biome).plants.get(random.nextInt(l)).generate(world, random, pos2);
				}
			}
			
			if (HighlandsSettings.vanillaBiomeChanges){
				if(biome.equals(Biomes.SWAMPLAND) && random.nextInt(32) == 1){
					HighlandsGenerators.cattail.generate(world, random, pos2);
				}
				if(biome.equals(Biomes.MESA_ROCK) && random.nextInt(45) == 1){
					HighlandsGenerators.mcOTulip.generate(world, random, pos2);
				}
			
			}
		}
	}

}
