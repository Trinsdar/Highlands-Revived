package com.sdj64.highlands.generator;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenPlants extends WorldGenerator{

	private IBlockState plant;
	private int num;
	
	public WorldGenPlants(IBlockState plantState, int quantity){
		plant = plantState;
		num = quantity;
	}
	
	@Override
	public boolean generate(World world, Random random, BlockPos pos) {
		BlockPos pos2 = pos;
		
		for(int i = 0; i < num; i++){
			pos2 = world.getTopSolidOrLiquidBlock(pos2).down();
			Block soil = world.getBlockState(pos2).getBlock();
			
			if(soil.equals(Blocks.DIRT) || soil.equals(Blocks.SAND) || soil.equals(Blocks.GRASS)){
				if(world.isAirBlock(pos2.up()))world.setBlockState(pos2.up(), plant, 2);
			}
			pos2 = pos.east(random.nextInt(16)-7).north(random.nextInt(16)-7);
		}
		return true;
	}

}
