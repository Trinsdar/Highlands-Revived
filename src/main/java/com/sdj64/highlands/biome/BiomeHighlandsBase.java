package com.sdj64.highlands.biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sdj64.highlands.HighlandsSettings;
import com.sdj64.highlands.generator.WorldGenPlants;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;

public abstract class BiomeHighlandsBase extends Biome
{
	public static int minTreeHeight = 4;
	
	public ArrayList<Biome> subBiomes;
	public ArrayList<WorldGenPlants> plants;
	public int plantsPerChunk;

	public BiomeHighlandsBase(Properties properties){
		super(properties.biomeProperties);
		subBiomes = new ArrayList<Biome>();
		plants = new ArrayList<WorldGenPlants>();
		plantsPerChunk = 0;
	}
	
	public void setSpawnLists(List monster, List creature, List waterCreature){
		this.spawnableCreatureList = creature;
		this.spawnableMonsterList = monster;
		this.spawnableWaterCreatureList = waterCreature;
	}
	
	/**
	 * because BiomeDecorator won't let me see it...
	 * @param blobsPerChunk
	 * @param oreGenerator
	 * @param minHeight
	 * @param maxHeight
	 */
    protected void genStandardOre(int blobsPerChunk, WorldGenerator oreGenerator, int minHeight, int maxHeight, World world, Random rng, BlockPos pos)
    {
    	if(!HighlandsSettings.useOreGens)return;
        int l;

        if (maxHeight < minHeight)
        {
            l = minHeight;
            minHeight = maxHeight;
            maxHeight = l;
        }
        else if (maxHeight == minHeight)
        {
            if (minHeight < 255)
            {
                ++maxHeight;
            }
            else
            {
                --minHeight;
            }
        }

        for (l = 0; l < blobsPerChunk; ++l)
        {
            BlockPos blockpos = pos.add(rng.nextInt(16), rng.nextInt(maxHeight - minHeight) + minHeight, rng.nextInt(16));
            oreGenerator.generate(world, rng, blockpos);
        }
    }
    
    /**
     * Adds a creature to spawn in a certain biome.  Not compatible with DrZhark's CustomMobSpawner.
     * @param creature the creature to add
     */
    public void addCreature(EntityCreature creature){
    	List creatureList = this.getSpawnableList(EnumCreatureType.CREATURE);
    	creatureList.add(creature);
    	this.setSpawnLists(
				this.getSpawnableList(EnumCreatureType.MONSTER),
				creatureList,
				this.getSpawnableList(EnumCreatureType.WATER_CREATURE)
				);
    }
    
    public void addMob(EntityMob mob){
    	List mobList = this.getSpawnableList(EnumCreatureType.MONSTER);
    	mobList.add(mob);
    	this.setSpawnLists(
				mobList,
				this.getSpawnableList(EnumCreatureType.CREATURE),
				this.getSpawnableList(EnumCreatureType.WATER_CREATURE)
				);
    }
    
    public void addWaterCreature(EntityCreature creature){
    	List waterCreatureList = this.getSpawnableList(EnumCreatureType.WATER_CREATURE);
    	waterCreatureList.add(creature);
    	this.setSpawnLists(
				this.getSpawnableList(EnumCreatureType.MONSTER),
				this.getSpawnableList(EnumCreatureType.CREATURE),
				waterCreatureList
				);
    }

    public static class Properties {
		public BiomeProperties biomeProperties;

		public Properties(String name, float baseHeight, float heightVariation, float temperature, float rainfall, boolean snowEnabled) {
			biomeProperties = new BiomeProperties(name)
					.setBaseHeight(baseHeight)
					.setHeightVariation(heightVariation)
					.setTemperature(temperature)
					.setRainfall(rainfall);
			if (snowEnabled){
				biomeProperties.setSnowEnabled();
			}

		}
	}
}
