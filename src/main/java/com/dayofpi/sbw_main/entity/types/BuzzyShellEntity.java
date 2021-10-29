package com.dayofpi.sbw_main.entity.types;

import com.dayofpi.sbw_main.Client;
import com.dayofpi.sbw_main.item.registry.ModItems;
import com.dayofpi.sbw_main.misc.SpawnPacket;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

public class BuzzyShellEntity extends AbstractShell {
    public BuzzyShellEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return SpawnPacket.create(this, Client.PacketID);
    }

    public Item asItem() {
        return ModItems.BUZZY_SHELL;
    }
}
