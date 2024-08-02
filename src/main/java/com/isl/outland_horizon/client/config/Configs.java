package com.isl.outland_horizon.client.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Configs {
    public static final ForgeConfigSpec COMMON_CONFIG;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("Common");
        builder.pop();
        COMMON_CONFIG = builder.build();
    }
}
