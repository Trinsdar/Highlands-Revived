package com.sdj64.highlands.generator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenTreeAspen extends WorldGenMTreeBase
{

    public WorldGenTreeAspen(Block leafBlock, Block woodBlock, int leafBlockMeta,
			int woodBlockMeta, int minH, int maxH, boolean notify) {
		super(leafBlock, woodBlock, leafBlockMeta, woodBlockMeta, minH, maxH, notify);
	}


	@Override
    public boolean generate(World wor, Random rand, BlockPos pos)
    {
    	world = wor;
    	random = rand;
        
        if(!isLegalTreePosition(pos, false, false)){
        	return false;
        }
        if(!isCubeClear(pos.up(2), 2, 10)){
        	return false;
        }
        
        int locX = pos.getX();
        int locY = pos.getY();
        int locZ = pos.getZ();
        
        //generates trunk
    	int treeHeight = minHeight + random.nextInt(maxHeight-minHeight);
    	for(int i = 0; i < treeHeight; i++){
    		setBlockLog(pos.up(i), 0);
    	}
    	//generates leaves at top
    	int h;
    	for(h = locY+treeHeight - 3; h < locY + treeHeight; h++){
    		generateLeafLayerCircleNoise(3.5, locX, locZ, h);
    	}
    	generateLeafLayerCircleNoise(3.8, locX, locZ, h);
    	h++;
    	generateLeafLayerCircleNoise(2.5, locX, locZ, h);
    	h++;
    	generateLeafLayerCircleNoise(2, locX, locZ, h);
    	h++;
    	generateLeafLayerCircleNoise(1, locX, locZ, h);
    	h -= 12;
    	//generates branches / leaves
    	int firstDir = random.nextInt(4);
    	for(int i = 0; i < 6; i++){
    		BlockPos end = generateStraightBranch(4, locX, h+i, locZ, (firstDir + i)%4);
    		int[] xyz = {end.getX(), end.getY(), end.getZ()};
    		generateLeafLayerCircleNoise(1.8, xyz[0], xyz[2], xyz[1]-1);
    		generateLeafLayerCircleNoise(2.5, xyz[0], xyz[2], xyz[1]);
    		generateLeafLayerCircleNoise(1.8, xyz[0], xyz[2], xyz[1]+1);
    	}
    	return true;
    }

	private boolean checkForObstructions(World world, BlockPos origin, int height, int bareTrunkHeight, int radius) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(origin);

		for(int i = 0; i < bareTrunkHeight; i++) {
			boolean canReplaceAll =
					canReplaceBlock(world, world.getBlockState(pos.setPos(origin.getX(), origin.getY() + i, origin.getZ())), pos) &&
							canReplaceBlock(world, world.getBlockState(pos.setPos(origin.getX() + 1, origin.getY() + i, origin.getZ())), pos) &&
							canReplaceBlock(world, world.getBlockState(pos.setPos(origin.getX(), origin.getY() + i, origin.getZ() + 1)), pos) &&
							canReplaceBlock(world, world.getBlockState(pos.setPos(origin.getX() + 1, origin.getY() + i, origin.getZ() + 1)), pos);

			if(!canReplaceAll) {
				return false;
			}
		}

		for(int dY = bareTrunkHeight; dY < height + 4; dY++) {
			for(int dZ = -radius; dZ <= radius + 1; dZ++) {
				for(int dX = -radius; dX <= radius + 1; dX++) {
					pos.setPos(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					IBlockState state = world.getBlockState(pos);
					if(!canReplaceBlock(world, state, pos)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private static boolean canReplaceBlock(World world, IBlockState state, BlockPos pos) {
		Block block = state.getBlock();

		if(block.isAir(state, world, pos) || block.isLeaves(state, world, pos) || block.isWood(world, pos)) {
			return true;
		}

		Material material = state.getMaterial();
		if(material == Material.AIR || material == Material.LEAVES) {
			return true;
		}

		return block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.LOG || block == Blocks.LOG2 || block == Blocks.SAPLING || block == Blocks.VINE;
	}
}













