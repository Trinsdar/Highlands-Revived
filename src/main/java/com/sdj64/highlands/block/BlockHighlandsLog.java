package com.sdj64.highlands.block;

import com.sdj64.highlands.init.HighlandsBlocks;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockHighlandsLog extends BlockLog
{
    protected static final AxisAlignedBB BAMBOO_BOUNDING_BOXX = new AxisAlignedBB(0.0F, 0.375F, 0.375F, 1.0F, 0.625F, 0.625F);
    protected static final AxisAlignedBB BAMBOO_BOUNDING_BOXY = new AxisAlignedBB(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
    protected static final AxisAlignedBB BAMBOO_BOUNDING_BOXZ = new AxisAlignedBB(0.375F, 0.375F, 0.0F, 0.625F, 0.625F, 1.0F);

	private HighlandsBlocks.EnumTypeTree treeType;
	
    public BlockHighlandsLog(HighlandsBlocks.EnumTypeTree type)
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
        setHardness(2.0F);
    	setResistance(0.5F);
    	setSoundType(SoundType.WOOD);

        
        treeType = type;
        if (type == HighlandsBlocks.EnumTypeTree.BAMBOO){
            useNeighborBrightness = true;
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        if (this.treeType.equals(HighlandsBlocks.EnumTypeTree.BAMBOO)) {

            if (state.getActualState(source, pos) == getStateFromMeta(4)){
                return BAMBOO_BOUNDING_BOXX;
            }
            if (state.getActualState(source, pos) == getStateFromMeta(8)){
                return BAMBOO_BOUNDING_BOXZ;
            }
            return BAMBOO_BOUNDING_BOXY;
        }

        return FULL_BLOCK_AABB;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        if (treeType == null) {
            return true;
        }
        return !treeType.equals(HighlandsBlocks.EnumTypeTree.BAMBOO);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        if (treeType == null) {
            return true;
        }
        return !treeType.equals(HighlandsBlocks.EnumTypeTree.BAMBOO);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        switch (meta & 12)
        {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
    	int i = 0;
    	
        switch (BlockHighlandsLog.SwitchEnumAxis.AXIS_LOOKUP[((BlockLog.EnumAxis)state.getValue(LOG_AXIS)).ordinal()])
        {
            case 1:
                i = 4;
                break;
            case 2:
                i = 8;
                break;
            case 3:
                i = 12;
        }

        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS);
    }

    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, 0);
    }

    /**
     * Get the damage value that this Block should drop
     */
    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    static final class SwitchEnumAxis
        {
            static final int[] AXIS_LOOKUP = new int[BlockLog.EnumAxis.values().length];

            static
            {
                try
                {
                    AXIS_LOOKUP[BlockLog.EnumAxis.X.ordinal()] = 1;
                }
                catch (NoSuchFieldError var3)
                {
                    ;
                }

                try
                {
                    AXIS_LOOKUP[BlockLog.EnumAxis.Z.ordinal()] = 2;
                }
                catch (NoSuchFieldError var2)
                {
                    ;
                }

                try
                {
                    AXIS_LOOKUP[BlockLog.EnumAxis.NONE.ordinal()] = 3;
                }
                catch (NoSuchFieldError var1)
                {
                    ;
                }
            }
        }
}