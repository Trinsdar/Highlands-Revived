package com.sdj64.highlands.generator;

import com.sdj64.highlands.block.BlockHighlandsLeaves;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenTreeBamboo extends WorldGenMTreeBase
{

    public WorldGenTreeBamboo(Block leafBlock, Block woodBlock, int leafBlockMeta,
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
        if(!isCubeClear(pos.up(1), 1, 7)){
        	return false;
        }
        
        int locX = pos.getX();
        int locY = pos.getY();
        int locZ = pos.getZ();
        
        double leafWidth = 0;
        
        //generates trunk and leaves
    	int treeHeight = minHeight + random.nextInt(maxHeight-minHeight);

    	if(pos.getY() + treeHeight + 1 > world.getHeight() || pos.getY() < 1) {
			return false;
		}

		boolean oldAllowLeavesDecay = BlockHighlandsLeaves.allowLeavesDecay;
		if(!notifyFlag) {
			BlockHighlandsLeaves.allowLeavesDecay = false;
		}


    	for(int i = 0; i < treeHeight; i++){
    		setBlockLog(pos.up(i), 0);
    		
    		leafWidth = (double)i/(double)treeHeight * 1.9;
    		generateLeafLayerCircleNoise(leafWidth, locX, locZ, locY+i);
    	}
    	generateLeafLayerCircleNoise(1.9, locX, locZ, locY+treeHeight);
    	generateLeafLayerCircleNoise(1.9, locX, locZ, locY+treeHeight + 1);

		if(!notifyFlag) {
			BlockHighlandsLeaves.allowLeavesDecay = oldAllowLeavesDecay;
		}

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













