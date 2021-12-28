package com.dayofpi.super_block_world.main.common.block.reactive;

import com.dayofpi.super_block_world.client.sound.ModSounds;
import com.dayofpi.super_block_world.main.common.block_entity.QuestionBlockBE;
import com.dayofpi.super_block_world.main.registry.block.BlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class QuestionBlock extends ReactiveBlock implements BlockEntityProvider {
    public static final BooleanProperty HIDDEN;

    static {
        HIDDEN = BooleanProperty.of("hidden");
    }

    public QuestionBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HIDDEN, false));

    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HIDDEN);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new QuestionBlockBE(pos, state);
    }

    public static List<ItemStack> defaultItems(ServerWorld world, BlockState state, BlockPos blockPos) {
        LootContext lootContext = new LootContext.Builder(world).parameter(LootContextParameters.BLOCK_STATE, state).parameter(LootContextParameters.TOOL, ItemStack.EMPTY).parameter(LootContextParameters.ORIGIN, Vec3d.ofCenter(blockPos)).build(LootContextTypes.BLOCK);
        ServerWorld serverWorld = lootContext.getWorld();

        LootTable lootTable = serverWorld.getServer().getLootManager().getTable(state.getBlock().getLootTableId());
        return lootTable.generateLoot(lootContext);
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context instanceof EntityShapeContext) {
            if (state.get(HIDDEN)) {
                return VoxelShapes.empty();
            }
        } return super.getCollisionShape(state, world, pos, context);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return state.get(HIDDEN) ? BlockRenderType.INVISIBLE : BlockRenderType.MODEL;
    }

    @Override
    public void activate(BlockState state, World world, BlockPos blockPos) {
        Inventory blockEntity = (Inventory) world.getBlockEntity(blockPos);
        BlockPos pos = blockPos.up();
        if (state.isSolidBlock(world, blockPos.up())) {
            if (!state.isSolidBlock(world, blockPos.down())) {
                pos = blockPos.down();
            }
        }

        if (!world.isClient) {
            if (blockEntity != null) {
                if (!blockEntity.getStack(0).isEmpty()) {
                    Block.dropStack(world, pos, blockEntity.getStack(0));
                    blockEntity.removeStack(0);
                } else {
                    Block.dropStack(world, pos, defaultItems((ServerWorld) world, state, blockPos).iterator().next());
                }
                world.setBlockState(blockPos, pushEntitiesUpBeforeBlockChange(state, BlockRegistry.EMPTY_BLOCK.getDefaultState(), world, blockPos));
                world.removeBlockEntity(blockPos);
                world.playSound(null, blockPos, ModSounds.BLOCK_QUESTION_BLOCK_HIT, SoundCategory.NEUTRAL, 2.0F, 1.0F);
            }
        }
    }
}
