package com.sdj64.highlands.init;

import com.sdj64.highlands.References;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HighlandsItems {
    public static Item[] planks;
    public static Item[] logs;
    public static Item[] leaves;
    public static Item[] saplings;

    //wood products
    public static Item[] doors;
    public static Item[] fences;
    public static Item[] slabs;
    public static Item[] stairs;

    public static Item[] plants;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        planks = new Item[HighlandsBlocks.NUM_TREE_TYPES];
        logs = new Item[HighlandsBlocks.NUM_TREE_TYPES];
        leaves = new Item[HighlandsBlocks.NUM_TREE_TYPES];
        saplings = new Item[HighlandsBlocks.NUM_TREE_TYPES];
        doors = new Item[HighlandsBlocks.NUM_TREE_TYPES];
        fences = new Item[HighlandsBlocks.NUM_TREE_TYPES];
        slabs = new Item[HighlandsBlocks.NUM_TREE_TYPES];
        stairs = new Item[HighlandsBlocks.NUM_TREE_TYPES];

        plants = new Item[HighlandsBlocks.NUM_PLANTS];

        for (int i = 0; i < HighlandsBlocks.NUM_TREE_TYPES; i++) {
            planks[i] = register(event, HighlandsBlocks.planks[i]);
        }
        for(int i = 0; i < HighlandsBlocks.NUM_TREE_TYPES; i++){
            stairs[i] = register(event, HighlandsBlocks.stairs[i]);
        }
        for(int i = 0; i < HighlandsBlocks.NUM_TREE_TYPES; i++){
            slabs[i] = register(event, new ItemSlab(HighlandsBlocks.slabs[i], HighlandsBlocks.slabs[i], HighlandsBlocks.doubleSlabs[i]), HighlandsBlocks.EnumTypeTree.META_LOOKUP[i].getName() + "_slab");
        }
        for(int i = 0; i < HighlandsBlocks.NUM_TREE_TYPES; i++){
            logs[i] = register(event, HighlandsBlocks.logs[i]);
        }
        for(int i = 0; i < HighlandsBlocks.NUM_TREE_TYPES; i++){
            leaves[i] = register(event, HighlandsBlocks.leaves[i]);
        }
        for(int i = 0; i < HighlandsBlocks.NUM_TREE_TYPES; i++){
            saplings[i] = register(event, HighlandsBlocks.saplings[i]);
        }

        for (int i = 0; i < HighlandsBlocks.NUM_PLANTS; i++) {
            plants[i] = register(event, HighlandsBlocks.plants[i]);
        }
    }

    private static Item register(RegistryEvent.Register<Item> event, Block block) {
        ResourceLocation name = block.getRegistryName();

        if(name == null) {
            throw new NullPointerException();
        }

        Item item = new ItemBlock(block);
        item.setRegistryName(name);
        item.setUnlocalizedName(block.getUnlocalizedName());
        item.setCreativeTab(HighlandsBlocks.tabHighlands);

        event.getRegistry().register(item);

        return item;
    }

    private static Item register(RegistryEvent.Register<Item> event, Item item, String name) {
        item.setRegistryName(new ResourceLocation(References.MOD_ID, name));
        item.setUnlocalizedName(References.MOD_ID + "." + name);
        item.setCreativeTab(HighlandsBlocks.tabHighlands);

        event.getRegistry().register(item);

        return item;
    }
}
