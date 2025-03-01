package com.arc.outland_horizon.core;

import java.util.Random;

public class SeedXZRandom {
    protected Random random;
    long seed;

    public SeedXZRandom(long seed) {
        this.seed = seed;
        random = new Random();
    }

    public int sample(int x, int z, int min, int max) {
        random.setSeed((seed & 0xFFFFFFFF00000000L) ^
                ((long) x << 32) ^
                (z & 0x00000000FFFFFFFFL));
        return random.nextInt(min, max + 1);
    }
}
