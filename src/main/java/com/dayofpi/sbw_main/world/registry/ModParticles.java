package com.dayofpi.sbw_main.world.registry;

import com.dayofpi.sbw_main.Main;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModParticles {
    public static final DefaultParticleType POISON_BUBBLE = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Main.MOD_ID, "poison_bubble"), POISON_BUBBLE);
    }
}
