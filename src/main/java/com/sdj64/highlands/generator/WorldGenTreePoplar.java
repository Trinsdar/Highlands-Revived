package com.sdj64.highlands.generator;

import com.sdj64.highlands.block.BlockHighlandsLeaves;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenTreePoplar extends WorldGenMTreeBase
{

    public WorldGenTreePoplar(Block leafBlock, Block woodBlock,
			int leafBlockMeta, int woodBlockMeta, int minH, int maxH,
			boolean notify) {
		super(leafBlock, woodBlock, leafBlockMeta, woodBlockMeta, minH, maxH, notify);
	}

	@Override
	public boolean generate(World wor, Random rand,  BlockPos pos)
    {
    	world = wor;
    	random = rand;
    	
    	int locX = pos.getX();
        int locY = pos.getY();
        int locZ = pos.getZ();
        
        if(!isLegalTreePosition(pos, false, false))return false;
        if(!isCubeClear(pos.down(-2), 1, 8))return false;
    	
        //generates the trunk
    	int treeHeight = minHeight + random.nextInt(maxHeight-minHeight);

		if(pos.getY() + treeHeight + 1 > world.getHeight() || pos.getY() < 1) {
			return false;
		}

		boolean oldAllowLeavesDecay = BlockHighlandsLeaves.allowLeavesDecay;
		if(!notifyFlag) {
			BlockHighlandsLeaves.allowLeavesDecay = false;
		}

    	for(int i = 0; i < treeHeight; i++){
    		setBlockLog(pos.down(-i), 0);
    	}
    	
    	//generates the leaves.
    	int h = locY + 3;
    	generateLeafLayerCircle(1, locX, locZ, h);
    	h++;
    	generateLeafLayerCircle(1.5, locX, locZ, h);
    	
    	for(h = h + 1; h < locY + treeHeight; h++){
    		generateLeafLayerCircleNoise(2.1, locX, locZ, h);
    	}
    	generateLeafLayerCircleNoise(1.5, locX, locZ, h);
    	h++;
    	generateLeafLayerCircleNoise(1, locX, locZ, h);
    	h++;
    	setBlockLeaf(pos.down(locY - h));
    	h++;
    	setBlockLeaf(pos.down(locY - h));

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













