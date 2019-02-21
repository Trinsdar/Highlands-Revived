package com.sdj64.highlands.block;

import com.sdj64.highlands.init.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;

public class BlockHighlandsStair extends BlockStairs {

    public BlockHighlandsStair(Block base) {

        super(base.getDefaultState());
        setHardness(2.0F);
        setResistance(0.5F);
        setSoundType(SoundType.WOOD);
    }
}
