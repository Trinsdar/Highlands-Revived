package com.sdj64.highlands.biome;

import com.sdj64.highlands.init.HighlandsBiomeProperties;
import net.minecraft.init.Blocks;

public class BiomeTundra extends BiomeHighlandsBase{
    public BiomeTundra() {
        super(HighlandsBiomeProperties.TUNDRA);
        decorator.treesPerChunk = 0;
        decorator.grassPerChunk = 0;
        decorator.flowersPerChunk = 0;

        this.topBlock = Blocks.SNOW.getDefaultState();
        this.fillerBlock = Blocks.SNOW.getDefaultState();
    }
}
