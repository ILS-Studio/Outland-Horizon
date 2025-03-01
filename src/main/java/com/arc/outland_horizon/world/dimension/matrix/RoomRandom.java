package com.arc.outland_horizon.world.dimension.matrix;

import com.arc.outland_horizon.core.SeedXZRandom;

class RoomRandom {
    protected long seed;
    protected SeedXZRandom random;

    protected RoomRandom(long seed) {
        this.seed = seed;
        random = new SeedXZRandom(seed);
    }

    protected RoomType sample(int chunkX, int chunkZ) {
        return switch (random.sample(chunkX, chunkZ, 0, 8)) {
            case 0 -> RoomType.MONSTER;
            case 1 -> RoomType.REWARD;
            default -> RoomType.EMPTY;
        };
    }

}
