package com.isl.outland_horizon;

import com.isl.outland_horizon.config.Configs;
import com.isl.outland_horizon.utils.MaterialPack;
import com.isl.outland_horizon.utils.Utils;
import com.isl.outland_horizon.world.block.BlockRegistry;
import com.isl.outland_horizon.world.effect.EffectRegistry;
import com.isl.outland_horizon.world.entity.EntityRegistry;
import com.isl.outland_horizon.world.item.ItemRegistry;
import com.isl.outland_horizon.world.sound.SoundEventRegister;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Utils.MOD_ID)
public class OutlandHorizon {
    public static IEventBus bus;
    public OutlandHorizon() {
        bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Configs.COMMON_CONFIG);

        MaterialPack.create(MaterialPack.MaterialType.GEM, "blue", 1);
        MaterialPack.create(MaterialPack.MaterialType.CUSTOM, "blood_stone", 1);

        ItemRegistry.register(bus);
        BlockRegistry.register(bus);
        BlockRegistry.FluidRegistry.FLUIDS.register(bus);
        BlockRegistry.FluidTypeRegistry.FLUID_TYPES.register(bus);
        EffectRegistry.register(bus);
        EntityRegistry.EntityRenders.init();
        EntityRegistry.register(bus);
        SoundEventRegister.init();
        SoundEventRegister.REGISTRY.register(bus);
    }

}
