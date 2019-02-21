package com.sdj64.highlands.block;

import com.sdj64.highlands.init.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;

public class BlockHighlandsStair extends BlockStairs {
    private HighlandsBlocks.EnumTypeTree treeType;

    public BlockHighlandsStair(HighlandsBlocks.EnumTypeTree type, Block base) {

        super(base.getDefaultState());
        setHardness(2.0F);
        setResistance(0.5F);
        setSoundType(SoundType.WOOD);

        this.setCreativeTab(HighlandsBlocks.tabHighlands);

        treeType = type;
    }
}
