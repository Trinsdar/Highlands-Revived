package com.sdj64.highlands;

import com.sdj64.highlands.generator.HighlandsGenerators;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HLEventManager {
	
	@SubscribeEvent
	public void onDecorateTree(Decorate e)
	{
		if(e.getType() == Decorate.EventType.TREE){
			
			Biome biome = e.getWorld().getBiome(e.getPos());
			
			/*
			if(biome.equals(BiomeGenBase.forest) && e.rand.nextInt(18) == 1){
				e.setResult(Event.Result.DENY);
				HighlandsGenerators.ashGen.generate(e.world, e.rand, e.pos;
			}
			*/
			
			/*
			if(biome.equals(BiomeGenBase.forest) && e.rand.nextInt(20) == 1){
				e.setResult(Event.Result.DENY);
				HighlandsGenerators.greatOakGen.generate(e.world, e.rand, e.pos);
			}
			if(biome.equals(BiomeGenBase.forest) && e.rand.nextInt(7) == 1){
				e.setResult(Event.Result.DENY);
				new WorldGenBigTree(false).generate(e.world, e.rand, e.pos);
			}
			
			if(biome.equals(BiomeGenBase.birchForest) && e.rand.nextInt(12) == 1){
				e.setResult(Event.Result.DENY);
				HighlandsGenerators.poplarGen.generate(e.world, e.rand, e.pos);
			}
			*/
			if(biome.equals(Biomes.SAVANNA) && e.getRand().nextInt(3) != 1){
				e.setResult(Event.Result.DENY);
			}
		}
	}
	
	//used to grow Great Oak from a 2x2 oak sapling square
	@SubscribeEvent
	public void onSapling(SaplingGrowTreeEvent e)
	{
		boolean flagSquare = false;
		BlockPos treeGrowPos = e.getPos();
		
		//great oak grows on the northwest corner
		if(((BlockSapling)Blocks.SAPLING).isTypeAt(e.getWorld(), e.getPos(), BlockPlanks.EnumType.OAK)){
			//Sapling is in southwest corner
			if(((BlockSapling)Blocks.SAPLING).isTypeAt(e.getWorld(), e.getPos().north(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.SAPLING).isTypeAt(e.getWorld(), e.getPos().east(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.SAPLING).isTypeAt(e.getWorld(), e.getPos().north().east(), BlockPlanks.EnumType.OAK)){
				flagSquare = true;
				treeGrowPos = treeGrowPos.north();
			}
			//Sapling is in southeast corner
			if(((BlockSapling)Blocks.SAPLING).isTypeAt(e.getWorld(), e.getPos().north(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.SAPLING).isTypeAt(e.getWorld(), e.getPos().west(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.SAPLING).isTypeAt(e.getWorld(), e.getPos().north().west(), BlockPlanks.EnumType.OAK)){
				flagSquare = true;
				treeGrowPos = treeGrowPos.west().north();
			}
			//Sapling is in northwest corner
			if(((BlockSapling)Blocks.SAPLING).isTypeAt(e.getWorld(), e.getPos().south(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.SAPLING).isTypeAt(e.getWorld(), e.getPos().east(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.SAPLING).isTypeAt(e.getWorld(), e.getPos().south().east(), BlockPlanks.EnumType.OAK)){
				flagSquare = true;
				//treeGrowPos is good
			}
			//Sapling is in northeast corner
			if(((BlockSapling)Blocks.SAPLING).isTypeAt(e.getWorld(), e.getPos().south(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.SAPLING).isTypeAt(e.getWorld(), e.getPos().west(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.SAPLING).isTypeAt(e.getWorld(), e.getPos().south().west(), BlockPlanks.EnumType.OAK)){
				flagSquare = true;
				treeGrowPos = treeGrowPos.west();
			}
			
			if(flagSquare){
				e.setResult(Event.Result.DENY);
				HighlandsGenerators.greatOakSapling.generate(e.getWorld(), e.getRand(), treeGrowPos);
			}
			
		}
	}
	
	
}




