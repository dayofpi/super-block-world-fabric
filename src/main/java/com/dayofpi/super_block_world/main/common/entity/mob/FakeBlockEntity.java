package com.dayofpi.super_block_world.main.common.entity.mob;

import com.dayofpi.super_block_world.client.GlobalReceivers;
import com.dayofpi.super_block_world.client.sound.ModSounds;
import com.dayofpi.super_block_world.main.common.block.reactive.ReactiveBlock;
import com.dayofpi.super_block_world.main.common.entity.EnemyEntity;
import com.dayofpi.super_block_world.main.registry.general.TagRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.SoundKeyframeEvent;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class FakeBlockEntity extends EnemyEntity implements IAnimatable {
    AnimationFactory factory = new AnimationFactory(this);
    private boolean twirling = false;

    public FakeBlockEntity(EntityType<? extends EnemyEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
    }

    protected void initGoals() {
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.5F));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new RevengeGoal(this));
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.BLOCK_TOADSTONE_BREAK;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(ModSounds.BLOCK_TOADSTONE_STEP, 0.15F, 1.0F);
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    public boolean tryAttack(Entity target) {
        if (this.isTwirling()) {
            return super.tryAttack(target);
        } else return false;
    }

    private boolean isTwirling() {
        return this.twirling;
    }

    public void setTwirling(boolean twirling) {
        this.twirling = twirling;
    }

    public void tickMovement() {
        super.tickMovement();
        if (this.isAlive()) {
            LivingEntity target = this.getTarget();
            if (!this.world.isClient && this.world.getServer() != null) {
                for (ServerPlayerEntity player : PlayerLookup.all(this.world.getServer())) {
                    this.setTwirling(target != null && this.distanceTo(target) < 2F);
                    PacketByteBuf buf = PacketByteBufs.create();
                    buf.writeInt(this.getId());
                    buf.writeBoolean(this.isTwirling());
                    ServerPlayNetworking.send(player, GlobalReceivers.FAKE_BLOCK_STATE, buf);
                }
            }
            if (!this.world.isClient() && this.isTwirling() && random.nextInt(4) == 0) {
                for (BlockPos blockPos : BlockPos.iterateOutwards(this.getBlockPos(), 1, 0, 1)) {
                    BlockState state = world.getBlockState(blockPos);
                    if (state.isIn(TagRegistry.BRICKS)) {
                        world.breakBlock(blockPos, true);
                    } else if (state.getBlock() instanceof ReactiveBlock reactiveBlock) {
                        reactiveBlock.activate(state, world, blockPos);
                    }
                }
            }

            // Slow falling
            Vec3d vec3d = this.getVelocity();
            if (!this.onGround && vec3d.y < 0.0D) {
                this.setVelocity(vec3d.multiply(1.0D, 0.6D, 1.0D));
            }
        }
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        if (this.isTwirling()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", true));
            return PlayState.CONTINUE;
        } return PlayState.STOP;
    }

    private <ENTITY extends IAnimatable> void soundListener(SoundKeyframeEvent<ENTITY> event) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            player.damage(DamageSource.mob(this), 3);
            player.playSound(ModSounds.ENTITY_MISC_TAIL_ATTACK, SoundCategory.HOSTILE, 1.0F, 1.0F);
        }
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        AnimationController<FakeBlockEntity> controller = new AnimationController<>(this, "controller", 0, this::predicate);
        controller.registerSoundListener(this::soundListener);
        animationData.addAnimationController(controller);
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
