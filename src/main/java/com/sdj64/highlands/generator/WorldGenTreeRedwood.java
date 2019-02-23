package com.sdj64.highlands.generator;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenTreeRedwood extends WorldGenMTreeBase
{


    public WorldGenTreeRedwood(Block leafBlock, Block woodBlock,
			int leafBlockMeta, int woodBlockMeta, int minH, int maxH,
			boolean notify) {
		super(leafBlock, woodBlock, leafBlockMeta, woodBlockMeta, minH, maxH, notify);
	}

	@Override
	public boolean generate(World wor, Random rand, BlockPos pos)
    {
    	world = wor;
    	random = rand;
    	
        if(!isLegalTreePosition(pos, false, false))return false;
        if(!isCubeClear(pos.down(-3), 4, 20))return false;
        
    	int locX = pos.getX();
        int locY = pos.getY();
        int locZ = pos.getZ();
        
        int treeHeight = minHeight + random.nextInt(maxHeight-minHeight);

		if(pos.getY() + treeHeight + 1 > world.getHeight() || pos.getY() < 1) {
			return false;
		}

		//generates the trunk - different than other tree types, uses layer circles.
		double theta = Math.atan(2.2/treeHeight);
		
		for(int i = -3; i <= treeHeight; i++){
			double r = (treeHeight - i) * Math.tan(theta);
			generateWoodLayerCircle(r, locX, locZ, locY+i);
		}
		
		//generate the branches. They start halfway up the tree and are generated every 3 blocks up.
		int h = locY + treeHeight - 5;
		double r = 2;
		for(int i = 0; i < 6; i++){
			generateSequoiaBranch(r, locX, locZ, h);
			h-=3;
			if(r < 6)r++;
		}
		
		h = locY + treeHeight - 2;
		generateLeafLayerCircle(3.5, locX, locZ, h);
		h++;
		generateLeafLayerCircle(2.5, locX, locZ, h);
		h++;
		generateLeafLayerCircle(2, locX, locZ, h);
		h++;
		generateLeafLayerCircle(1.5, locX, locZ, h);
		h++;
		generateLeafLayerCircle(1, locX, locZ, h);
		h++;
		generateLeafLayerCircle(0, locX, locZ, h);
		h++;
		generateLeafLayerCircle(0, locX, locZ, h);
		return true;
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
}













