package com.dayofpi.super_block_world.common.entities.projectile;


import com.dayofpi.super_block_world.registry.ModBlocks;
import com.dayofpi.super_block_world.registry.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.world.World;

public class BlackPaintEntity extends GoopEntity {
    public BlackPaintEntity(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    public BlackPaintEntity(World world, LivingEntity owner) {
        super(ModEntities.BLACK_PAINT, owner, world);
    }

    @Override
    protected BlockState getLayerState() {
        return ModBlocks.BLACK_PAINT.getDefaultState();
    }

    @Override
    protected BlockState getBlockState() {
        return ModBlocks.BLACK_PAINT_BLOCK.getDefaultState();
    }
}
