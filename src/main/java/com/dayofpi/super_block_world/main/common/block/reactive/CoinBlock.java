package com.dayofpi.super_block_world.main.common.block.reactive;

import com.dayofpi.super_block_world.main.common.block_entity.CoinBlockBE;
import com.dayofpi.super_block_world.main.client.sound.ModSounds;
import com.dayofpi.super_block_world.main.registry.block.BlockRegistry;
import com.dayofpi.super_block_world.main.registry.item.ItemRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
@SuppressWarnings("deprecation")
public class CoinBlock extends ReactiveBlock implements BlockEntityProvider {
    public static final IntProperty TYPE;

    public CoinBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(TYPE, 0));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
    }

    static {
        // 0 = ? Block; 1 = Toadstone; 2 = Gloomstone; 3 = Gold; 4 = Crystal; 5 = Invisible
        TYPE = IntProperty.of("type", 0, 5);
    }

    @Override
    public void activate(BlockState state, World world, BlockPos blockPos) {
        world.createAndScheduleBlockTick(blockPos, this, 2);
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context instanceof EntityShapeContext) {
            if (state.get(TYPE) == 5) {
                return VoxelShapes.empty();
            }
        } return super.getCollisionShape(state, world, pos, context);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return state.get(TYPE) == 5 ? BlockRenderType.INVISIBLE : BlockRenderType.MODEL;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos blockPos, Random random) {
        Inventory blockEntity = (Inventory) world.getBlockEntity(blockPos);
        if (blockEntity != null) {
            int coinCount = blockEntity.getStack(0).getCount();
            BlockPos pos = blockPos.add(0, 1, 0);
            if (state.isSolidBlock(world, blockPos.up())) {
                if (!state.isSolidBlock(world, blockPos.down())) {
                    pos = blockPos.add(0, -1, 0);
                }
            }
            if (coinCount != 0) {
                Block.dropStack(world, pos, new ItemStack(ItemRegistry.COIN));
                world.playSound(null, blockPos, ModSounds.BLOCK_ITEM_BLOCK_COIN, SoundCategory.NEUTRAL, 0.8F, 1.0F);

                ParticleUtil.spawnParticle(world, blockPos.up(), ParticleTypes.WAX_OFF, UniformIntProvider.create(1, 2));
                if (coinCount > 1) {
                    blockEntity.getStack(0).setCount(coinCount - 1);
                    if (state.get(TYPE) == 5)
                        world.setBlockState(blockPos, pushEntitiesUpBeforeBlockChange(state, state.with(TYPE, 0), world, blockPos));
                } else {
                    world.setBlockState(blockPos, pushEntitiesUpBeforeBlockChange(state, BlockRegistry.EMPTY_BLOCK.getDefaultState(), world, blockPos));
                    world.removeBlockEntity(blockPos);
                }
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CoinBlockBE(pos, state);
    }
}
