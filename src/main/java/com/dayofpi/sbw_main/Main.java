package com.dayofpi.sbw_main;

import com.dayofpi.sbw_main.block.registry.ModBlocks;
import com.dayofpi.sbw_main.entity.registry.ModEffects;
import com.dayofpi.sbw_main.entity.registry.ModEntities;
import com.dayofpi.sbw_main.item.registry.ModItems;
import com.dayofpi.sbw_main.misc.DispenserBehaviors;
import com.dayofpi.sbw_main.world.registry.*;
import net.fabricmc.api.ModInitializer;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.kyrptonaught.customportalapi.util.CPASoundEventData;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.MusicSound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionType;

public class Main implements ModInitializer {
    /* Changelog 1.0.8
    Added thwomps!
    -Thwomps spawn semi-rarely across the world
    -They're mobs, but you can walk on them
    -They try to attack players, but can damage any mobs or bricks on impact
    Added cloud stairs.
    Fixed cloud slab loot table.
    Added block lines.
    The dimension now has a bit of ambient light.
    Munchers are now animated.
    Fossil falls now have falls.
    Giant brown mushrooms now generate in mushroom grasslands.
    Giant mushrooms in vanilla Mushroom Fields and Dark Forest biomes are now replaced with the mod ones.
    Buzzy beetles and spike tops now destroy bricks when landing.
    Buzzy beetles and spike tops are now able to spawn on the ceiling again.
    Paratroopas no longer spawn underground.
    Fire and ice flowers now properly work with dispensers.
    Mobs are now set on fire if killed by a fireball, ensuring a cooked drop.
    Thrown hammers now only destroy brick blocks.

    TODO:
      Add shell entities
      Add toads
      Add block lines
      Give fake blocks an attack animation and unique sound/events

      Make mushroom block textures varied.
      Make more spawn eggs unique
      Fix bottled ghost ID
      Add missing slabs/stairs/walls.
      Add cerise.
      Remove experimental world warning.
    */
    public static final String MOD_ID = "super_block_world";
    public static final Identifier DIMENSION_ID = new Identifier(MOD_ID, "mushroom_kingdom");
    public static RegistryKey<DimensionType> DIMENSION_KEY;

    public static final BlockSoundGroup TOADSTONE = new BlockSoundGroup(1.0F, 1.0F, ModSounds.BLOCK_TOADSTONE_BREAK, ModSounds.BLOCK_TOADSTONE_STEP, ModSounds.BLOCK_TOADSTONE_PLACE, ModSounds.BLOCK_TOADSTONE_HIT, ModSounds.BLOCK_TOADSTONE_FALL);
    public static final BlockSoundGroup GRASSY_TOADSTONE = new BlockSoundGroup(1.0F, 1.0F, ModSounds.BLOCK_GRASSY_STONE_BREAK, ModSounds.BLOCK_GRASSY_STONE_STEP, ModSounds.BLOCK_GRASSY_STONE_PLACE, ModSounds.BLOCK_GRASSY_STONE_HIT, ModSounds.BLOCK_GRASSY_STONE_FALL);
    public static final BlockSoundGroup FROSTED_STONE = new BlockSoundGroup(1.0F, 1.0F, ModSounds.BLOCK_FROSTED_STONE_BREAK, ModSounds.BLOCK_FROSTED_STONE_STEP, ModSounds.BLOCK_FROSTED_STONE_PLACE, ModSounds.BLOCK_FROSTED_STONE_HIT, ModSounds.BLOCK_FROSTED_STONE_FALL);

    public static final MusicSound GRASSLAND = new MusicSound(ModSounds.MUSIC_GRASSLAND, 12000, 24000, false);
    public static final MusicSound CAVE = new MusicSound(ModSounds.MUSIC_CAVE, 10000, 20000, false);

    @Override
    public void onInitialize() {
        DIMENSION_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, DIMENSION_ID);
        ModBlocks.registerBlocks();
        ModEntities.registerEntities();
        ModItems.registerItems();
        ModEffects.registerEffects();
        ModFluid.registerFluids();
        ModFeature.registerFeatures();
        ModConfiguredFeature.registerConfiguredFeatures();
        ModTags.registerTags();
        ModKeys.registerKeys();
        ModStructure.registerStructures();
        ModParticle.registerParticles();
        ModSounds.registerSounds();
        DispenserBehaviors.addDispenserBehaviors();
        CustomPortalBuilder.beginPortal()
                .frameBlock(ModBlocks.WARP_FRAME)
                .tintColor(188, 112, 255).destDimID(DIMENSION_ID)
                .registerInPortalAmbienceSound(playerEntity -> new CPASoundEventData(ModSounds.BLOCK_PORTAL_TRIGGER, 1.0F, 1.0F))
                .registerPostTPPortalAmbience(playerEntity -> new CPASoundEventData(ModSounds.BLOCK_PORTAL_TRAVEL, 1.0F, 1.0F))
                .registerPortal();

    }
}
