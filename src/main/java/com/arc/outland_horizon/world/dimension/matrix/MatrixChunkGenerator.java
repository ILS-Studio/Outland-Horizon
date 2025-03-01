package com.arc.outland_horizon.world.dimension.matrix;

import com.arc.outland_horizon.registry.OHBiomes;
import com.arc.outland_horizon.world.dimension.matrix.rooms.EmptyRoom;
import com.arc.outland_horizon.world.dimension.matrix.rooms.MonsterRoom;
import com.arc.outland_horizon.world.dimension.matrix.rooms.RewardRoom;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadLocalRandom;

public class MatrixChunkGenerator extends ChunkGenerator {
    RoomRandom random;

    public static final Codec<MatrixChunkGenerator> CODEC = RecordCodecBuilder.create((p_255576_) -> {
        return p_255576_.group(RegistryOps.retrieveElement(OHBiomes.MATRIX)).apply(p_255576_, p_255576_.stable(MatrixChunkGenerator::new));
    });


    public MatrixChunkGenerator(Holder.Reference<Biome> biomeReference) {
        super(new FixedBiomeSource(biomeReference));
        random = new RoomRandom(ThreadLocalRandom.current().nextLong());
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void createStructures(RegistryAccess pRegistryAccess, ChunkGeneratorStructureState pStructureState, StructureManager pStructureManager, ChunkAccess pChunk, StructureTemplateManager pStructureTemplateManager) {

    }

    @Override
    public void applyBiomeDecoration(WorldGenLevel pLevel, ChunkAccess pChunk, StructureManager pStructureManager) {

    }

    @Override
    public void applyCarvers(WorldGenRegion pLevel, long pSeed, RandomState pRandom, BiomeManager pBiomeManager, StructureManager pStructureManager, ChunkAccess pChunk, GenerationStep.Carving pStep) {

    }

    @Override
    public void buildSurface(WorldGenRegion level, StructureManager pStructureManager, RandomState pRandom, ChunkAccess chunk) {
        BlockPos chunkPos = chunk.getPos().getWorldPosition().offset(0, 0, -1);
        BlockPos pos1 = chunkPos.offset(1, 1, 2);
        BlockPos pos2 = chunkPos.offset(14, 15, 15);
        chunk.setBlockState(pos1, Blocks.GRASS_BLOCK.defaultBlockState(), false);
        chunk.setBlockState(pos2, Blocks.STONE.defaultBlockState(), false);
        RoomType roomType = random.sample(chunkPos.getX(), chunkPos.getZ());
        switch (roomType) {
            case EMPTY -> new EmptyRoom().place(chunk, chunkPos);
            case REWARD -> new RewardRoom(level.getSeed()).place(chunk, chunkPos);
            case MONSTER -> new MonsterRoom(level.getSeed()).place(chunk, chunkPos);
        }
    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion level) {

    }

    @Override
    public int getGenDepth() {
        return 384;
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunk) {

        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public WeightedRandomList<MobSpawnSettings.SpawnerData> getMobsAt(Holder<Biome> pBiome, StructureManager pStructureManager, MobCategory pCategory, BlockPos pPos) {
        return WeightedRandomList.create();
    }

    @Override
    public int getSeaLevel() {
        return 0;
    }

    @Override
    public int getMinY() {
        return 0;
    }

    @Override
    public int getBaseHeight(int pX, int pZ, Heightmap.Types pType, LevelHeightAccessor pLevel, RandomState pRandom) {
        return 16;
    }

    @Override
    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor heightAccessor, RandomState randomState) {
        return new NoiseColumn(0, new BlockState[0]);
    }

    @Override
    public void addDebugScreenInfo(List<String> infos, RandomState randomState, BlockPos blockPos) {

    }
}
