package com.arc.outland_horizon.network.packets;

import com.arc.outland_horizon.registry.OHMobEffects;
import com.arc.outland_horizon.utils.CapabilityUtils;
import com.arc.outland_horizon.world.capability.provider.OhAttributeProvider;
import com.fho4565.brick_lib.network.PacketContent;
import com.fho4565.brick_lib.network.core.C2SNetworkContext;
import com.fho4565.brick_lib.network.core.C2SPacket;
import net.minecraft.world.effect.MobEffectInstance;

public class TriggerRagePacket extends C2SPacket {

    public TriggerRagePacket(PacketContent content) {

    }

    @Override
    public void serverHandle(C2SNetworkContext c2SNetworkContext) {
        c2SNetworkContext.getSenderAnd(serverPlayer -> {
            if (CapabilityUtils.Rage.isRageFull(serverPlayer)) {
                serverPlayer.addEffect(new MobEffectInstance(OHMobEffects.RAGE.get(), OhAttributeProvider.madTime, OhAttributeProvider.madDamageBonus));
                CapabilityUtils.Rage.setRage(serverPlayer, 0);
            }
        });
    }

    @Override
    public void encoder(PacketContent packetContent) {

    }
}