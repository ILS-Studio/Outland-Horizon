package com.isl.outland_horizon.setup;

import com.isl.outland_horizon.client.gui.overlay.PlayerOverlay;
import com.isl.outland_horizon.client.key.KeyRegistry;
import com.isl.outland_horizon.utils.Utils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.isl.outland_horizon.world.block.BlockRegistry.FluidRegistry.BLOOD;
import static com.isl.outland_horizon.world.block.BlockRegistry.FluidRegistry.BLOOD_FLOWING;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        KeyRegistry.register(event);
    }
}
