/*
 * Copyright (c) 2021 TeamMoeg
 *
 * This file is part of Frosted Heart.
 *
 * Frosted Heart is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * Frosted Heart is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Frosted Heart. If not, see <https://www.gnu.org/licenses/>.
 */

package com.teammoeg.frostedheart.world.unused.save;

import com.teammoeg.frostedheart.FHMain;
import com.teammoeg.frostedheart.climate.chunkdata.ChunkData;
import com.teammoeg.frostedheart.climate.chunkdata.ChunkDataCache;
import com.teammoeg.frostedheart.climate.chunkdata.ChunkMatrix;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

@Deprecated
public class WorldTemperatureData extends WorldSavedData {
    public static final String NAME = FHMain.MODID + "_temperature";
    private ChunkDataCache serverCache = ChunkDataCache.SERVER;

    public WorldTemperatureData() {
        super(NAME);
    }

    public ChunkDataCache getServerCache() {
        return serverCache;
    }

    public void setServerCache(ChunkDataCache cache) {
        serverCache = cache;
        markDirty();
    }

    @Override
    public void read(CompoundNBT nbt) {
        for (String chunkName : nbt.keySet()) {
            int chunkX = Integer.parseInt(chunkName.substring(chunkName.indexOf('x') + 2, chunkName.indexOf(".chunk_z")));
            int chunkZ = Integer.parseInt(chunkName.substring(chunkName.indexOf('z') + 2));
            ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
            ChunkData chunkData = serverCache.getOrCreate(chunkPos);
            byte[][][] matrix = chunkData.getChunkMatrix().getMatrix();
            CompoundNBT blocksNBT = nbt.getCompound(chunkName);
            for (int x = 0; x < 16; x++)
                for (int z = 0; z < 16; z++) {
                    blocksNBT.putByteArray("block_x_" + x + ".block_z_" + z, matrix[x][z]);
                }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        for (ChunkPos chunkPos : serverCache.getCache().keySet()) {
            ChunkData chunkData = serverCache.getCache().get(chunkPos);
            ChunkMatrix chunkMatrix = chunkData.getChunkMatrix();
            byte[][][] matrix = chunkMatrix.getMatrix();
            CompoundNBT blocks = new CompoundNBT();
            for (int x = 0; x < 16; x++)
                for (int z = 0; z < 16; z++) {
                    blocks.putByteArray("block_x_" + x + ".block_z_" + z, matrix[x][z]);
                }
            compound.put("chunk_x_" + chunkPos.x + ".chunk_z_" + chunkPos.z, blocks);
        }
        return compound;
    }

    public static WorldTemperatureData get(World worldIn) {
        if (!(worldIn instanceof ServerWorld)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }
        ServerWorld world = worldIn.getServer().getWorld(World.OVERWORLD);
        DimensionSavedDataManager storage = world.getSavedData();
        return storage.getOrCreate(WorldTemperatureData::new, NAME);
    }
}
