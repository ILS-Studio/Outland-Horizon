package com.isl.oh.setup;

import com.isl.oh.registry.KeyRegistry;
import com.isl.oh.utils.Utils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        KeyRegistry.register(event);
    }
}
