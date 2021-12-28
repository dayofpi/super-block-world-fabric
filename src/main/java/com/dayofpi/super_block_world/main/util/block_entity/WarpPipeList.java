package com.dayofpi.super_block_world.main.util.block_entity;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WarpPipeList {
    public final List<BlockPos> warpList;
    final int chunkX, chunkZ;

    public WarpPipeList(int chunkX, int chunkZ) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        warpList = new ArrayList<>();
    }

    public int getX() { return chunkX;}
    public int getZ() { return chunkZ;}

    public boolean isPipeInList(BlockPos pipe) {
        return warpList.contains(pipe);
    }

    public void add(BlockPos warp) {
        if (!isPipeInList(warp))
            warpList.add(warp);
    }

    public void remove(BlockPos warp) {
        warpList.remove(warp);
    }

    @Nullable
    private BlockPos findNearestBlockFromPosition(BlockPos ignore, World world, Vec3d pos) {
        //Find the nearest block by looking direction
        BlockPos pipe = null;
        double dist = 999D; //Max distance, we only check 16x256x16, so we shouldn't really hit it.
        for(BlockPos warp : warpList) {
            //Ignore if start block
            if (warp.getX() == ignore.getX() && warp.getY() == ignore.getY() && warp.getZ() == ignore.getZ())
                continue;
            //Ignore if not air above block
            if (world.getBlockState(warp.add(0, 1, 0)) != Blocks.AIR.getDefaultState())
                continue;

            //Ignore if not same color
            if (!world.getBlockState(warp).isOf(world.getBlockState(ignore).getBlock()))
                continue;

            //Compare squared distance
            Vec3d _warp = new Vec3d(warp.getX(), warp.getY(), warp.getZ());
            double newDist = pos.squaredDistanceTo(_warp);
            if (newDist < dist) {
                //Update pipe and distance if new dist is smaller
                pipe = warp;
                dist = newDist;
            }
        }
        return pipe;
    }

    @Nullable
    public BlockPos findNearestBlock(BlockPos pos, World world) {
        BlockPos pipe = null;
        //loop through warps to find the nearest warp to blockPos
        for(BlockPos warp : warpList) {
            //Ignore if same block
            if (warp.getX() == pos.getX() && warp.getY() == pos.getY() && warp.getZ() == pos.getZ())
                continue;

            //Ignore if no air above
            if (!world.getBlockState(warp.add(0, 1, 0)).isAir())
                continue;

            //Ignore if not same color
            if (!world.getBlockState(warp).isOf(world.getBlockState(pos).getBlock()))
                continue;

            //Immediately accept if no pipe found
            if (pipe == null) {
                pipe = warp;
                continue;
            }

            //In manhattan distance is smaller, set as new pipe. Manhattan distance is only addition by block count.
            int manhattanA, manhattanB;
            manhattanA = warp.getManhattanDistance(pos);
            manhattanB = pipe.getManhattanDistance(pos);
            if (manhattanA < manhattanB)
                pipe = warp;
            else if (manhattanA == manhattanB) {
                Vec3d forward = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
                BlockPos nearestInFront = findNearestBlockFromPosition(pos, world, forward);
                if (nearestInFront != null)
                    pipe = nearestInFront;
            }
        }
        return pipe;
    }
}