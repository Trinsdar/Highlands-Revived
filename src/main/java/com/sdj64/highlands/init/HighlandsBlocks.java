package com.sdj64.highlands.init;

import com.sdj64.highlands.References;
import com.sdj64.highlands.block.BlockHighlandsDoubleSlab;
import com.sdj64.highlands.block.BlockHighlandsLeaves;
import com.sdj64.highlands.block.BlockHighlandsLog;
import com.sdj64.highlands.block.BlockHighlandsPlanks;
import com.sdj64.highlands.block.BlockHighlandsPlant;
import com.sdj64.highlands.block.BlockHighlandsSapling;
import com.sdj64.highlands.block.BlockHighlandsSlab;
import com.sdj64.highlands.block.BlockHighlandsStair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class HighlandsBlocks {

	public static final int NUM_TREE_TYPES = 7;
	public static final int NUM_PLANTS = 9;
	
	public static final CreativeTabs tabHighlands = new CreativeTabs(References.MOD_ID) {
		@Override
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(Blocks.SAPLING);
        }
    };
	
    //tree blocks
	public static Block[] planks;
	public static Block[] logs;
	public static Block[] leaves;
	public static Block[] saplings;
	
	//wood products
	public static Block[] doors;
	public static Block[] fences;
	public static Block[] fenceGates;
	public static BlockSlab[] slabs;
	public static BlockSlab[] doubleSlabs;
	public static Block[] stairs;
	
	//plants
	public static Block[] plants;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		//initialize EnumType meta lookup
		EnumTypeTree.ASPEN.setMetaLookup();
		EnumTypeTree.POPLAR.setMetaLookup();
		EnumTypeTree.EUCA.setMetaLookup();
		EnumTypeTree.PALM.setMetaLookup();
		EnumTypeTree.FIR.setMetaLookup();
		EnumTypeTree.REDWOOD.setMetaLookup();
		EnumTypeTree.BAMBOO.setMetaLookup();
		
		EnumTypePlant.BLUEFLOWER.setMetaLookup();
		EnumTypePlant.CATTAIL.setMetaLookup();
		EnumTypePlant.COTTON.setMetaLookup();
		EnumTypePlant.BLUEBERRYBUSH.setMetaLookup();
		EnumTypePlant.RASPBERRYBUSH.setMetaLookup();
		EnumTypePlant.THORNBUSH.setMetaLookup();
		EnumTypePlant.LAVENDER.setMetaLookup();
		EnumTypePlant.GREENLEAF.setMetaLookup();
		EnumTypePlant.DUNEGRASS.setMetaLookup();
		
		//initialize arrays
		planks = new Block[NUM_TREE_TYPES];
		logs = new Block[NUM_TREE_TYPES];
		leaves = new Block[NUM_TREE_TYPES];
		saplings = new Block[NUM_TREE_TYPES];
		doors = new Block[NUM_TREE_TYPES];
		fences = new Block[NUM_TREE_TYPES];
		fenceGates = new Block[NUM_TREE_TYPES];
		slabs = new BlockSlab[NUM_TREE_TYPES];
		doubleSlabs = new BlockSlab[NUM_TREE_TYPES];
		stairs = new Block[NUM_TREE_TYPES];
		
		plants = new Block[NUM_PLANTS];
		
		//initialize blocks within arrays
		for(int i = 0; i < NUM_TREE_TYPES; i++)
		{
			planks[i] = register(event, new BlockHighlandsPlanks(), EnumTypeTree.META_LOOKUP[i].getName() + "_planks");
			OreDictionary.registerOre("plankWood", planks[i]);
			Blocks.FIRE.setFireInfo(planks[i], 5, 20);
		}
		for(int i = 0; i < NUM_TREE_TYPES; i++){
			slabs[i] = register(event, new BlockHighlandsSlab(), EnumTypeTree.META_LOOKUP[i].getName() + "_slab");
			OreDictionary.registerOre("slabWood", slabs[i]);
			Blocks.FIRE.setFireInfo(slabs[i], 5, 20);
		}
		for(int i = 0; i < NUM_TREE_TYPES; i++){
			doubleSlabs[i] = register(event, new BlockHighlandsDoubleSlab(), EnumTypeTree.META_LOOKUP[i].getName() + "_double_slab");
			OreDictionary.registerOre("slabWood", doubleSlabs[i]);
			Blocks.FIRE.setFireInfo(doubleSlabs[i], 5, 20);
		}
		for(int i = 0; i < NUM_TREE_TYPES; i++){
			stairs[i] = register(event, new BlockHighlandsStair(planks[i]), EnumTypeTree.META_LOOKUP[i].getName() + "_stairs");
			OreDictionary.registerOre("stairWood", stairs[i]);
			Blocks.FIRE.setFireInfo(stairs[i], 5, 20);
		}
		for(int i = 0; i < NUM_TREE_TYPES; i++){
			logs[i] = register(event, new BlockHighlandsLog(EnumTypeTree.META_LOOKUP[i]), EnumTypeTree.META_LOOKUP[i].getName() + "_log");
			OreDictionary.registerOre("logWood", logs[i]);
			Blocks.FIRE.setFireInfo(logs[i], 5, 5);
		}
		for(int i = 0; i < NUM_TREE_TYPES; i++){
			leaves[i] = register(event, new BlockHighlandsLeaves(EnumTypeTree.META_LOOKUP[i]), EnumTypeTree.META_LOOKUP[i].getName() + "_leaves");
			OreDictionary.registerOre("treeLeaves", leaves[i]);
			Blocks.FIRE.setFireInfo(leaves[i], 30, 60);
		}
		for(int i = 0; i < NUM_TREE_TYPES; i++){
			saplings[i] = register(event, new BlockHighlandsSapling(EnumTypeTree.META_LOOKUP[i]), EnumTypeTree.META_LOOKUP[i].getName() + "_saplings");
			OreDictionary.registerOre("treeSapling", saplings[i]);
		}

		for(int i = 0; i < NUM_PLANTS; i++){
			plants[i] = register(event, new BlockHighlandsPlant(), EnumTypePlant.META_LOOKUP[i].name);
			
			Blocks.FIRE.setFireInfo(plants[i], 60, 100);
		}
		((BlockHighlandsPlant)plants[EnumTypePlant.THORNBUSH.meta]).thornbush = true;
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void registerModels(ModelRegistryEvent event) {
		for(int i = 0; i < NUM_TREE_TYPES; i++){
			ModelLoader.setCustomStateMapper(slabs[i], new StateMap.Builder().ignore(BlockHighlandsSlab.VARIANT).build());
			ModelLoader.setCustomStateMapper(doubleSlabs[i], new StateMap.Builder().ignore(BlockHighlandsSlab.VARIANT).build());
		}
	}

	private static <T extends Block> T register(RegistryEvent.Register<Block> event, T block, String name) {
		block.setRegistryName(new ResourceLocation(References.MOD_ID, name));
		block.setUnlocalizedName(References.MOD_ID + "." + name);
		block.setCreativeTab(tabHighlands);

		event.getRegistry().register(block);

		return block;
	}
	
	
	
	
	public static enum EnumTypeTree implements IStringSerializable
    {
        ASPEN(0, "aspen"),
        POPLAR(1, "poplar"),
        EUCA(2, "eucalyptus"),
        PALM(3, "palm"),
        FIR(4, "fir"),
        REDWOOD(5, "redwood"),
		BAMBOO(6, "bamboo");
        public static final EnumTypeTree[] META_LOOKUP = new EnumTypeTree[values().length];
        private final int meta;
        private final String name;

        private EnumTypeTree(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
        }
        
        public void setMetaLookup(){ EnumTypeTree.META_LOOKUP[this.meta] = this;}

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }
	
	public static enum EnumTypePlant implements IStringSerializable
    {
        BLUEFLOWER(0, "blue_flower"),
        CATTAIL(1, "cattail"),
        COTTON(2, "cotton"),
        RASPBERRYBUSH(3, "raspberry_bush"),
        BLUEBERRYBUSH(4, "blueberry_bush"),
        THORNBUSH(5, "thorn_bush"),
        LAVENDER(6, "lavender"),
        GREENLEAF(7, "green_leaf"),
		DUNEGRASS(8, "dune_grass");
        private static final EnumTypePlant[] META_LOOKUP = new EnumTypePlant[values().length];
        private final int meta;
        private final String name;

        private EnumTypePlant(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
        }
        
        public void setMetaLookup(){ EnumTypePlant.META_LOOKUP[this.meta] = this;}

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
