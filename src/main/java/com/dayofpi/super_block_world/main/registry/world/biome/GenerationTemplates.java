package com.dayofpi.super_block_world.main.registry.world.biome;

import com.dayofpi.super_block_world.main.registry.world.feature.placed.*;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.OceanPlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class GenerationTemplates {
    static void addMushroomGrasslandsFeatures(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.HORSETAIL);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.STUMP);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GRASSLAND_PLANTS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GRASSLAND_FLOWERS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GRASSLAND_MUSHROOMS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GRASSLAND_VEGETATION);
    }

    static void addSherbetLandFeatures(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, PlacedDecoration.AMETHYST_EXTRA);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.STUMP);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GRASSLAND_PLANTS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GRASSLAND_FLOWERS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GRASSLAND_MUSHROOMS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.SHERBET_LAND_VEGETATION);
    }

    static void addMooMooMeadowFeatures(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.HORSETAIL);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.STUMP);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.MEADOW_PLANTS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.MEADOW_FLOWERS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GRASSLAND_MUSHROOMS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.MEADOW_VEGETATION);
    }

    static void addAmanitaForestFeatures(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.LAKES, PlacedDecoration.LAKE_POISON_FOREST);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_TALL_GRASS_2);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.MUNCHER_MANY);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.STUMP);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.PIRANHA_LILY);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.WATER_PLANTS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.AMANITA_FOREST_MUSHROOMS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GRASSLAND_PLANTS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GRASSLAND_FLOWERS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.AMANITA_FOREST_VEGETATION);
    }

    static void addForestOfIllusionFeatures(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.LAKES, PlacedDecoration.LAKE_POISON_FOREST);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_TALL_GRASS_2);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.FUZZBUSH);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.PIRANHA_LILY);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.WATER_PLANTS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.AMANITA_FOREST_MUSHROOMS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GRASSLAND_PLANTS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.FOREST_OF_ILLUSION_FLOWERS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.FOREST_OF_ILLUSION_VEGETATION);
    }

    static void addAutumnForestFeatures(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.LAKES, PlacedDecoration.LAKE_POISON_FOREST);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_TALL_GRASS_2);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_NORMAL);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.STUMP);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.WATER_PLANTS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.AMANITA_CARPET);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.AUTUMN_FOREST_MUSHROOMS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.AUTUMN_FOREST_PLANTS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GRASSLAND_FLOWERS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.AUTUMN_FOREST_VEGETATION);
    }

    static void addMushroomGorgeFeatures(GenerationSettings.Builder builder) {
        builder.carver(GenerationStep.Carver.AIR, CustomCarvers.WIDE_CANYON);
        builder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, PlacedDecoration.AMETHYST_EXTRA);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.STUMP);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.FUZZBUSH);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GORGE_PLANTS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GORGE_FLOWERS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.GORGE_VEGETATION);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.CANYON_VEGETATION);
    }

    static void addDryDryDesertFeatures(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedDecoration.QUICKSAND);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.CACTUS_PATCH);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.DESERT_PLANTS);
    }

    static void addFossilFallsFeatures(GenerationSettings.Builder builder) {
        builder.carver(GenerationStep.Carver.AIR, CustomCarvers.WIDE_CANYON);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedOre.ORE_VANILLATE_COAL_EXTRA);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedOre.ORE_CHISELED_TOADSTONE);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.STUMP);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.AMANITA_CARPET);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.FOSSIL_FALLS_PLANTS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.FOSSIL_FALLS_FLOWERS);
    }

    public static void addCheepCheepReefFeatures(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, PlacedPipe.OCEAN_FLOOR_PIPE);
        builder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, PlacedBlock.JELLYBEAM);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.HORSETAIL);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.WATER_PLANTS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.AMANITA_CARPET);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.CORAL_MANY);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.KELP_WARM);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.AUTUMN_FOREST_PLANTS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.REEF_FLOWERS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedVegetation.REEF_VEGETATION);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.WARM_OCEAN_VEGETATION);
    }
}
