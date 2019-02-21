package com.sdj64.highlands.generator;

import com.sdj64.highlands.HighlandsSettings;
import com.sdj64.highlands.init.HighlandsBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class GenerateTrees implements IWorldGenerator
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
			
			
			if(biome.equals(HighlandsBiomes.meadow) && random.nextInt(32) == 1){
				HighlandsGenerators.poplarGen.generate(world, random, pos2);
				HighlandsGenerators.poplarGen.generate(world, random, pos2.east(random.nextInt(16)-7).north(random.nextInt(16)-7));
				HighlandsGenerators.poplarGen.generate(world, random, pos2.east(random.nextInt(16)-7).north(random.nextInt(16)-7));
				HighlandsGenerators.poplarGen.generate(world, random, pos2.east(random.nextInt(16)-7).north(random.nextInt(16)-7));
				HighlandsGenerators.poplarGen.generate(world, random, pos2.east(random.nextInt(16)-7).north(random.nextInt(16)-7));
			}
			
			if (HighlandsSettings.vanillaBiomeChanges){

				if (Biome.getIdForBiome(biome) == Biome.getIdForBiome(Biomes.DESERT) + 128 && random.nextInt(4) == 1) {
					HighlandsGenerators.palmGen.generate(world, random, pos2);
				}
				if (biome.equals(Biomes.SAVANNA) && random.nextInt(45) == 1) {
					HighlandsGenerators.aspenGen.generate(world, random, pos2);
					HighlandsGenerators.aspenGen.generate(world, random, pos2.east(random.nextInt(16) - 7).north(random.nextInt(16) - 7));
					HighlandsGenerators.aspenGen.generate(world, random, pos2.east(random.nextInt(16) - 7).north(random.nextInt(16) - 7));
					HighlandsGenerators.aspenGen.generate(world, random, pos2.east(random.nextInt(16) - 7).north(random.nextInt(16) - 7));
					HighlandsGenerators.aspenGen.generate(world, random, pos2.east(random.nextInt(16) - 7).north(random.nextInt(16) - 7));
				}
				if (biome.equals(Biomes.JUNGLE_EDGE) || biome.equals(Biomes.JUNGLE)) {
					HighlandsGenerators.eucalyptusGen.generate(world, random, pos2);
					HighlandsGenerators.eucalyptusGen.generate(world, random, pos2.east(random.nextInt(16) - 7).north(random.nextInt(16) - 7));
					HighlandsGenerators.eucalyptusGen.generate(world, random, pos2.east(random.nextInt(16) - 7).north(random.nextInt(16) - 7));
				}

				if (biome.equals(Biomes.FOREST) && random.nextInt(12) == 1) {
					HighlandsGenerators.greatOakGen.generate(world, random, pos2);
				}
				else if (biome.equals(Biomes.FOREST) && random.nextInt(4) == 1) {
					new WorldGenBigTree(false).generate(world, random, pos2);
				}


				if ((biome.equals(Biomes.TAIGA) || biome.equals(Biomes.COLD_TAIGA)) && random.nextInt(25) == 1) {
					new WorldGenMegaPineTree(false, true).generate(world, random, pos2);
				}
				if (biome.equals(Biomes.TAIGA) || biome.equals(Biomes.COLD_TAIGA)) {//&& random.nextInt(2) == 1){
					HighlandsGenerators.firGen.generate(world, random, pos2);
				}

				if (biome.equals(Biomes.SWAMPLAND) && random.nextInt(3) == 1) {
					HighlandsGenerators.deadTreeGen.generate(world, random, pos2);
				}

				if (biome.equals(Biomes.ICE_PLAINS) && random.nextInt(6) == 1) {
					HighlandsGenerators.shrubGen.generate(world, random, pos2);
				}
			}
			
			//System.out.println("Generating Trees!");
			
			//MoreTreesMod.ashTreeGen.generate(world, random, pos2);
		}
	}

}
