package com.sdj64.highlands.block;

import com.sdj64.highlands.init.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockHighlandsPlanks extends Block
{
	
    public BlockHighlandsPlanks()
    {
    	super(Material.WOOD);
    	setHardness(2.0F);
    	setResistance(0.5F);
    	setSoundType(SoundType.WOOD);
    }
}