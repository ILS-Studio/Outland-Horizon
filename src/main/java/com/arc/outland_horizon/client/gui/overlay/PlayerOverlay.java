package com.arc.outland_horizon.client.gui.overlay;

import com.fho4565.brick_lib.variables.PlayerVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.HashSet;

public class PlayerOverlay implements IGuiOverlay {
    public static final HashSet<HudSection> hudSections = new HashSet<>();
    private static final PlayerOverlay playerOverlay = new PlayerOverlay();

    private PlayerOverlay() {
        hudSections.add(new HealthBar());
        hudSections.add(new ManaBar());
        hudSections.add(new SkillBar());
        hudSections.add(new HudSection() {
            @Override
            public void render(Minecraft minecraft, GuiGraphics guiGraphics) {
                PlayerVariables.clientIntValue("k").ifPresent(integer -> {
                    guiGraphics.drawString(minecraft.font, String.valueOf(integer), 30, 30, -1);
                });
            }
        });
    }

    public static PlayerOverlay of() {
        return playerOverlay;
    }

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        hudSections.forEach(hudSection -> hudSection.render(gui.getMinecraft(), guiGraphics));
    }
}
