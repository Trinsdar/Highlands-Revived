package com.sdj64.highlands.init;

import com.sdj64.highlands.biome.BiomeHighlandsBase.Properties;

public class HighlandsBiomeProperties {
    public static Properties ADIRONDACKS;
    public static Properties ADIRONDACKS_FOOTHILLS;
    public static Properties ALPS;
    public static Properties ALPS_FOOTHILLS;
    public static Properties BADLANDS;
    public static Properties BADLANDS_FOOTHILLS;
    public static Properties BALD_HILL;
    public static Properties BAMBOO_FOREST;
    public static Properties DESERT_MOUNTAINS;
    public static Properties DRY_FOREST;
    public static Properties DUNES;
    public static Properties FROZEN_LAKE;
    public static Properties GLACIER;
    public static Properties GREY_MOUNTAINS;
    public static Properties GREY_MOUNTAINS_FOOTHILLS;
    public static Properties HIGHLANDS;
    public static Properties LAKE;
    public static Properties LOWLANDS;
    public static Properties MEADOW;
    public static Properties MOJAVE;
    public static Properties PINELANDS;
    public static Properties POPLAR_HILLS;
    public static Properties REDWOOD_FOREST;
    public static Properties ROCK_MOUNTAINS;
    public static Properties SNOW_MOUNTAINS;
    public static Properties TROPICAL_HILLS;
    public static Properties TROPICAL_ISLANDS;
    public static Properties TUNDRA;


    public static void init(){
        ADIRONDACKS = new Properties("Adirondacks", 0.8F, 0.8F, 0.5F, 0.6F, false);
        ADIRONDACKS_FOOTHILLS = new Properties("Adirondacks Foothills", (0.8F / 2F), (0.8F / 2), 0.5F, 0.6F, false);
        ALPS = new Properties("Alps", 1.5F, 1.0F, 0.0F, 0.7F, true);
        ALPS_FOOTHILLS = new Properties("Alps Foothills", (1.5F / 2F), (1.0F / 2F), 0.0F, 0.7F, true);
        BADLANDS = new Properties("Badlands", 0.8F, 0.6F, 0.6F, 0.1F, false);
        BADLANDS_FOOTHILLS = new Properties("Badlands Foothills", (0.8F / 2F), (0.6F / 2F), 0.6F, 0.1F, false);
        BALD_HILL = new Properties("Bald Hill", 1.5F, 0.4F, 0.5F, 0.7F, false);
        BAMBOO_FOREST = new Properties("Bamboo Forest", 0.3F, 0.2F, 1.1F, 0.3F, false);
        DESERT_MOUNTAINS = new Properties("Desert Mountains", 1.5F, 0.8F, 1.7F, 0.0F, false);
        DRY_FOREST = new Properties("Dry Forest", 0.3F, 0.2F, 1.1F, 0.3F, false);
        DUNES = new Properties("Dunes", -0.15F, 0.5F, 0.95F, 0.4F, false);
        GLACIER = new Properties("Glacier", 1.3F, 0.2F, 0.0F, 0.5F, true);
        GREY_MOUNTAINS = new Properties("Grey Mountains", 1.8F, 1.0F, 0.6F, 0.1F, false);
        GREY_MOUNTAINS_FOOTHILLS = new Properties("Grey Mountains Foothills", (1.8F / 2F), (1.0F / 2F), 0.6F, 0.1F, false);
        HIGHLANDS = new Properties("Highlands", 0.7F, 0.4F, 0.6F, 0.2F, false);
        LAKE = new Properties("Lake", -0.7F, 0.01F, 0.8F, 0.8F, false);
        FROZEN_LAKE = new Properties("Frozen Lake", -0.7F, 0.01F, 0.0F, 0.8F, true);
        LOWLANDS = new Properties("Lowlands", -0.1F, 0.2F, 0.5F, 1.2F, false);
        MEADOW = new Properties("Meadow", 0.15F, 0.15F, 0.7F, 0.8F, false);
        MOJAVE = new Properties("Mojave", 0.2F, 0.4F, 1.6F, 0.1F, false);
        PINELANDS = new Properties("Pinelands", 0.4F, 0.6F, 0.5F, 0.6F, false);
        POPLAR_HILLS = new Properties("Poplar Hills", -0.1F, 0.4F, 0.6F, 0.8F, false);
        REDWOOD_FOREST = new Properties("Redwood Forest", 0.5F, 0.2F, 0.6F, 0.2F, false);
        ROCK_MOUNTAINS = new Properties("Rock Mountains", 1.5F, 0.8F, 0.2F, 0.3F, false);
        SNOW_MOUNTAINS = new Properties("Snow Mountains", 1.5F, 0.8F, 0.0F, 0.5F, true);
        TROPICAL_HILLS = new Properties("Tropical Hills", 0.4F, 0.5F, 0.95F, 0.7F, false);
        TROPICAL_ISLANDS = new Properties("Tropical Islands", -0.2F, 0.2F, 0.95F, 1.2F, false);
        TUNDRA = new Properties("Tundra", 0.125F, 0.0005F, 0.0F, 0.5F, true);
    }
}
