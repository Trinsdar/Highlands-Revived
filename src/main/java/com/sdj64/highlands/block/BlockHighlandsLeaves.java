package com.sdj64.highlands.block;

import com.sdj64.highlands.init.HighlandsBlocks;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockHighlandsLeaves extends BlockLeaves
{
	private HighlandsBlocks.EnumTypeTree treeType;

    public BlockHighlandsLeaves(HighlandsBlocks.EnumTypeTree type)
    {
    	treeType = type;
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setSoundType(SoundType.PLANT);
        if (type == HighlandsBlocks.EnumTypeTree.PALM){
            this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, false).withProperty(DECAYABLE, false));
        }else {
            this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
        }
        
        

    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getStateFromMeta(meta).withProperty(CHECK_DECAY, false).withProperty(DECAYABLE, false);
    }

    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
    {
        //does nothing, since Highlands trees don't drop apples.
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        boolean dec = meta < 4;
        boolean check = meta < 8;
        return this.getDefaultState().withProperty(DECAYABLE, dec).withProperty(CHECK_DECAY, check);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (!state.getValue(DECAYABLE))
        {
            i = 4;
        }

        if (state.getValue(CHECK_DECAY))
        {
            i |= 8;
        }

        return i;
    }
    
    public HighlandsBlocks.EnumTypeTree getTreeType()
    {
    	return treeType;
    }



    @Override
    protected int getSaplingDropChance(IBlockState state)
    {
        if (treeType.equals(HighlandsBlocks.EnumTypeTree.REDWOOD)){
            return 80;
        } else{
            return treeType.equals(HighlandsBlocks.EnumTypeTree.BAMBOO) ? 5 : super.getSaplingDropChance(state);
        }
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Override
    public EnumType getWoodType(int meta) {
        // returns Birch since it doesn't drop any apples. Probably safe, and safer than null.
        return EnumType.BIRCH;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(HighlandsBlocks.saplings[treeType.getMetadata()]);
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return NonNullList.withSize(1, new ItemStack(this));
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
        {
            player.addStat(StatList.getBlockStats(this));
            spawnAsEntity(worldIn, pos, new ItemStack(this));
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return Blocks.LEAVES.getBlockLayer();
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return Blocks.LEAVES.isOpaqueCube(state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        if (!Minecraft.getMinecraft().gameSettings.fancyGraphics) {
            return !(blockAccess.getBlockState(pos.offset(side)).getBlock() instanceof BlockHighlandsLeaves);
        } else {
            return true;
        }
    }



    public static boolean allowLeavesDecay = true;

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (treeType != HighlandsBlocks.EnumTypeTree.PALM){
            super.updateTick(worldIn, pos, state, rand);
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if(!allowLeavesDecay) {
            return;
        }
        if (treeType == HighlandsBlocks.EnumTypeTree.PALM){
            return;
        }

        if (worldIn.isAreaLoaded(pos.add(-5, -5, -5), pos.add(5, 5, 5)))
        {
            for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-4, -4, -4), pos.add(4, 4, 4)))
            {
                IBlockState iblockstate = worldIn.getBlockState(blockpos);

                if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos))
                {
                    iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
                }
            }
        }
    }


}