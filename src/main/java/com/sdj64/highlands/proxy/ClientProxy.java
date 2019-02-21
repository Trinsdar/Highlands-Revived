package com.sdj64.highlands.proxy;

import com.sdj64.highlands.init.HighlandsBlocks;
import com.sdj64.highlands.init.HighlandsItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy{
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        for (int i = 0; i < HighlandsBlocks.NUM_TREE_TYPES; i++) {
            registerModel(HighlandsItems.planks[i]);
            registerModel(HighlandsItems.logs[i]);
            registerModel(HighlandsItems.leaves[i]);
            registerModel(HighlandsItems.saplings[i]);
            registerModel(HighlandsItems.stairs[i]);
        }

        for (int i = 0; i < HighlandsBlocks.NUM_PLANTS; i++) {
            registerModel(HighlandsItems.plants[i]);
        }
    }

    private void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
