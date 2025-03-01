package com.arc.outland_horizon.network.packets;

import com.arc.outland_horizon.world.item.ISkillItem;
import com.fho4565.brick_lib.network.PacketContent;
import com.fho4565.brick_lib.network.core.C2SNetworkContext;
import com.fho4565.brick_lib.network.core.C2SPacket;
import net.minecraft.world.item.ItemStack;

public class TriggerItemSkill extends C2SPacket {

    public TriggerItemSkill(PacketContent content) {

    }

    @Override
    public void serverHandle(C2SNetworkContext c2SNetworkContext) {
        c2SNetworkContext.getSenderAnd(serverPlayer -> {
            ItemStack itemStack = serverPlayer.getMainHandItem();
            if (itemStack.getItem() instanceof ISkillItem skillItem) {
                skillItem.triggerSkill(serverPlayer, itemStack);
            }
        });
    }

    @Override
    public void encoder(PacketContent packetContent) {

    }
}