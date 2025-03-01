package com.arc.outland_horizon.world.dimension.matrix.rooms;

import com.arc.outland_horizon.core.SeedXZRandom;
import com.arc.outland_horizon.registry.OHBlocks;
import com.fho4565.brick_lib.tools.placer.Placer;
import net.minecraft.util.RandomSource;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class RewardRoom extends RoomBase {
    protected ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
    protected SeedXZRandom random;

    public RewardRoom(long seed) {
        ArrayList<Pair<Integer, Integer>> poses = new ArrayList<>(List.of(
                Pair.of(0, 0),
                Pair.of(0, 1),
                Pair.of(1, 0),
                Pair.of(1, 1)
        ));
        int count = RandomSource.create().nextIntBetweenInclusive(1, 4);
        for (int i = 0; i < count; i++) {
            list.add(poses.get(i));
        }
    }

    @Override
    protected void placeAddition(Placer placer) {
        list.forEach(pair -> {
            placer.savepoint();
            placer.move(Placer.MoveDirection.FORMER, 6 + pair.getLeft())
                    .move(Placer.MoveDirection.LEFT, 6 + pair.getRight())
                    .move(Placer.MoveDirection.UP, 1);
            placer.placeBlock(OHBlocks.Functional.MATRIX_REWARD_BLOCK.get().defaultBlockState());
            placer.rollback();
        });
    }

}
