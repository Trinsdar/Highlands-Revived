package com.sdj64.highlands.generator;

import com.sdj64.highlands.block.BlockHighlandsLeaves;
import com.sdj64.highlands.block.BlockHighlandsLog;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class WorldGenTreeFir extends WorldGenMTreeBase
{
	private IBlockState woodState;
	private IBlockState leafState;

    public WorldGenTreeFir(Block leafBlock, Block woodBlock, int leafBlockMeta,
			int woodBlockMeta, int minH, int maxH, boolean notify) {
		super(leafBlock, woodBlock, leafBlockMeta, woodBlockMeta, minH, maxH, notify);
		woodState = woodBlock.getDefaultState().withProperty(BlockHighlandsLog.LOG_AXIS, BlockLog.EnumAxis.Y);
		leafState = leafBlock.getDefaultState().withProperty(BlockHighlandsLeaves.CHECK_DECAY, false);
	}



	@Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
		// Total trunk height
		int height = rand.nextInt(maxHeight - minHeight) + minHeight;

		// How much "bare trunk" there will be.
		int bareTrunkHeight = 1 + rand.nextInt(9);

		// Maximum leaf radius.
		int maxRadius = 2 + rand.nextInt(2);

		if(pos.getY() + height + 1 > world.getHeight() || pos.getY() < 1) {
			return false;
		}

		BlockPos below = pos.down();
		IBlockState soil = world.getBlockState(below);

		if(notifyFlag && !soil.getBlock().canSustainPlant(soil, world, below, EnumFacing.UP, (IPlantable) Blocks.SAPLING)) {
			return false;
		}
		if (!soil.getBlock().canSustainPlant(soil, world, below, EnumFacing.UP, (IPlantable)Blocks.SAPLING) && soil.getBlock() != Blocks.SNOW){
			return false;
		}

		if(!checkForObstructions(world, pos, height, bareTrunkHeight, maxRadius)) {
			return false;
		}

		boolean oldAllowLeavesDecay = BlockHighlandsLeaves.allowLeavesDecay;
		if(!notifyFlag) {
			BlockHighlandsLeaves.allowLeavesDecay = false;
		}

		setBlockAndNotifyAdequately(world, pos.down(), Blocks.DIRT.getDefaultState());
		growLeaves(world, pos, height, bareTrunkHeight, maxRadius);
		growTrunk(world, new BlockPos.MutableBlockPos(pos), height);

		if(!notifyFlag) {
			BlockHighlandsLeaves.allowLeavesDecay = oldAllowLeavesDecay;
		}

		return true;
    }

	private boolean checkForObstructions(World world, BlockPos origin, int height, int bareTrunkHeight, int radius) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(origin);

		for(int i = 0; i < bareTrunkHeight; i++) {
			IBlockState state = world.getBlockState(pos.move(EnumFacing.UP));

			if(!canReplaceBlock(world, state, pos)) {
				return false;
			}
		}

		for(int dY = bareTrunkHeight; dY < height; dY++) {
			for(int dZ = -radius; dZ <= radius; dZ++) {
				for(int dX = -radius; dX <= radius; dX++) {
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

	private void growLeaves(World world, BlockPos origin, int height, int bareTrunkHeight, int maxRadius) {
		int radius = 0;
		int radiusTarget = 1;
		boolean topCone = true;

		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(origin);

		for(int dY = height; dY >= bareTrunkHeight; dY--) {
			for(int dZ = -radius; dZ <= radius; dZ++) {
				for(int dX = -radius; dX <= radius; dX++) {
					if(radius > 0 && Math.abs(dZ) == radius && Math.abs(dX) == radius) {
						// Cull corners
						continue;
					}
					if(radius > 0 && radius == 3 && Math.abs(dZ) == radius && Math.abs(dX) == radius - 1) {
						// Cull additional x corners
						continue;
					}
					if(radius > 0 && radius == 3 && Math.abs(dZ) == radius - 1 && Math.abs(dX) == radius) {
						// Cull additional z corners
						continue;
					}

					pos.setPos(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					IBlockState existing = world.getBlockState(pos);
					if(existing.getBlock().canBeReplacedByLeaves(existing, world, pos)) {
						setBlockAndNotifyAdequately(world, pos, leafState);
					}
				}
			}

			radius += 1;

			if(radius > radiusTarget) {
				if(topCone) {
					radius = 0;
					radiusTarget = Math.min(2, maxRadius);
					topCone = false;
				} else {
					radius = 1;
					radiusTarget = Math.min(radiusTarget + 1, maxRadius);
				}
			}
		}
	}

	private void growTrunk(World world, BlockPos.MutableBlockPos pos, int height) {
		for(int i = 0; i < height; i++) {
			setBlockAndNotifyAdequately(world, pos, woodState);

			pos.move(EnumFacing.UP);
		}
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

	@Override
	protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
	{
		if (this.notifyFlag)
		{
			worldIn.setBlockState(pos, state, 3);
		}
		else
		{
			// Don't notify neighbors, don't load adjacent chunks.
			// The leaves will be just fine.

			worldIn.setBlockState(pos, state, 2 | 16);
		}
	}
    
    //TREE GENERATORS
    
	private boolean genTree(BlockPos pos, int treeHeight, boolean isWide){
    	//generates the trunk
    	for(int i = 0; i < treeHeight; i++){
    		setBlockLog(pos.up(i), 0);
    	}
    	
        int locX = pos.getX();
        int locY = pos.getY();
        int locZ = pos.getZ();
    	
    	//generates the leaves
    	double leafRadius = 3.5;
    	double finalRadius = 1.5;
    	
    	int h0 = 6;
    	if(treeHeight < 16) h0 = 3;
    	int h;
    	for(h = locY + h0; h < treeHeight + locY; h++){
    		//level 1
    		if(isWide){
    			generateLeafLayerCircleNoise(leafRadius + 1, locX, locZ, h);
    			h++;
    		}
    		//level 2
    		generateLeafLayerCircleNoise(leafRadius, locX, locZ, h);
    		h++;
    		//level 3
    		generateLeafLayerCircleNoise(leafRadius - 1, locX, locZ, h);
    		//if not wide, gen a smaller layer
    		if(!isWide){
    			h++;
    			generateLeafLayerCircleNoise(leafRadius - 1.5, locX, locZ, h);
    		}
    		
    		leafRadius -= (leafRadius - finalRadius)/3.0;
    		
    	}
    	//generate top of tree
    	if(isWide){
    		generateLeafLayerCircleNoise(2, locX, locZ, h);
    		h++;
    	}
    	generateLeafLayerCircleNoise(1.5, locX, locZ, h);
    	h++;
    	generateLeafLayerCircleNoise(1, locX, locZ, h);
    	h++;
    	setBlockLeaf(new BlockPos(locX, h, locZ));
    	h++;
    	setBlockLeaf(new BlockPos(locX, h, locZ));
    	return true;
    }
    
}













