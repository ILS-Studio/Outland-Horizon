package com.arc.outland_horizon.world.dimension.matrix.rooms;

import com.arc.outland_horizon.core.ModDataManager;
import com.arc.outland_horizon.core.SeedXZRandom;
import com.fho4565.brick_lib.tools.placer.Placer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class MonsterRoom extends RoomBase {
    protected ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
    SeedXZRandom random;

    public MonsterRoom(long seed) {
        random = new SeedXZRandom(seed);
        int[] counts = switch (ModDataManager.modDifficulties) {
            case DEATH -> new int[]{2, 4};
            case TRIBULATION -> new int[]{4, 6};
            case ETERNAL -> new int[]{6, 10};
            default -> new int[]{1, 3};
        };
        RandomSource source = RandomSource.create();
        int count = source.nextIntBetweenInclusive(counts[0], counts[1]);
        final int dimension = 4;
        final int total = dimension * dimension;

        Set<Integer> indices = new HashSet<>();
        while (indices.size() < count) {
            int randIndex = ThreadLocalRandom.current().nextInt(total);
            indices.add(randIndex);
        }

        for (int index : indices) {
            int x = index / dimension;
            int y = index % dimension;
            list.add(Pair.of(x, y));
        }
    }

    @Override
    protected void placeAddition(Placer placer) {
        list.forEach(pair -> {
            placer.savepoint();
            placer.offsetAndPlaceBlock(new BlockPos(6 + pair.getLeft(), 1, 7 + pair.getRight()),
                    Blocks.MOSSY_COBBLESTONE.defaultBlockState());
            placer.rollback();
        });
    }
}
