package com.sdj64.highlands.block;

import com.sdj64.highlands.generator.HighlandsGenerators;
import com.sdj64.highlands.init.HighlandsBlocks;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockHighlandsSapling extends BlockBush implements IGrowable{

	private HighlandsBlocks.EnumTypeTree treeType;

	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

	private static float f = 0.4F;
	private static final AxisAlignedBB SAPLING_BOUNDING_BOX = new AxisAlignedBB(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);

	public BlockHighlandsSapling(HighlandsBlocks.EnumTypeTree type) {
		super();
		treeType = type;
		this.setSoundType(SoundType.PLANT);
		this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, Integer.valueOf(0)));
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return SAPLING_BOUNDING_BOX;
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			super.updateTick(worldIn, pos, state, rand);

			if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
				this.grow(worldIn, pos, state, rand);
			}
		}
	}

	public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (((Integer) state.getValue(STAGE)).intValue() == 0) {
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
		} else {
			this.generateTree(worldIn, pos, state, rand);
		}
	}

	public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		WorldGenAbstractTree gen;

		boolean flag;
		int dX = 0;
		int dZ;
		boolean mega = false;

		outer:
		for(dZ = 0; dZ >= -1; dZ--) {
			for(dX = 0; dX >= -1; dX--) {
				if(isTwoByTwoOfType(worldIn, pos, dX, dZ)) {
					mega = true;
					break outer;
				}
			}
		}

		if (!mega){
			switch (treeType) {
				case ASPEN:
					gen = HighlandsGenerators.aspenSapling;
					break;
				case POPLAR:
					gen = HighlandsGenerators.poplarSapling;
					break;
				case EUCA:
					gen = HighlandsGenerators.eucalyptusSapling;
					break;
				case PALM:
					gen = HighlandsGenerators.palmSapling;
					break;
				case FIR:
					gen = HighlandsGenerators.firSapling;
					break;
				case REDWOOD:
					gen = HighlandsGenerators.redwoodSapling;
					break;
				case BAMBOO:
					gen = HighlandsGenerators.bambooSapling;
					break;
				default:
					return;
			}
			flag = gen.generate(worldIn, rand, pos);
			// if tree is not in legal position, reset sapling.
			if (!flag){
				worldIn.setBlockState(pos, state);
			}
		}else{
			gen = HighlandsGenerators.firMegaSapling;

			IBlockState[][] oldStates = new IBlockState[2][2];

			BlockPos generatePos = pos.add(dX, 0, dZ);

			for(dZ = 0; dZ < 2; dZ++) {
				for(dX = 0; dX < 2; dX++) {
					BlockPos sapling = generatePos.add(dX, 0, dZ);

					oldStates[dZ][dX] = worldIn.getBlockState(sapling);
					worldIn.setBlockToAir(sapling);
				}
			}

			if(!gen.generate(worldIn, rand, generatePos)) {
				for(dZ = 0; dZ < 2; dZ++) {
					for(dX = 0; dX < 2; dX++) {
						BlockPos sapling = generatePos.add(dX, 0, dZ);

						worldIn.setBlockState(sapling, oldStates[dZ][dX]);
					}
				}
			}
		}








	}

	private boolean isTwoByTwoOfType(World worldIn, BlockPos pos, int dX, int dZ)
	{
		return this.isTypeAt(worldIn, pos.add(dX, 0, dZ))
				&& this.isTypeAt(worldIn, pos.add(dX + 1, 0, dZ))
				&& this.isTypeAt(worldIn, pos.add(dX, 0, dZ + 1))
				&& this.isTypeAt(worldIn, pos.add(dX + 1, 0, dZ + 1));
	}

	private boolean isTypeAt(World worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos).getBlock() == this;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		list.add(new ItemStack(itemIn, 1, 0));
	}

	/**
	 * Whether this IGrowable can grow
	 */
	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return (double) worldIn.rand.nextFloat() < 0.45D;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		this.grow(worldIn, pos, state, rand);
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i |= ((Integer) state.getValue(STAGE)).intValue() << 3;
		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {

		return new BlockStateContainer(this, STAGE);
	}
}
