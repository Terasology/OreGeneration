// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.generation;

import com.google.common.collect.Maps;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.engine.registry.CoreRegistry;
import org.terasology.engine.world.block.Block;
import org.terasology.engine.world.block.BlockManager;
import org.terasology.engine.world.chunks.ChunkConstants;
import org.terasology.engine.world.chunks.CoreChunk;
import org.terasology.engine.world.generation.Region;
import org.terasology.engine.world.generation.WorldRasterizer;
import org.terasology.engine.world.generation.WorldRasterizerPlugin;
import org.terasology.engine.world.generator.plugin.RegisterPlugin;
import org.terasology.math.geom.Vector3i;
import org.terasology.oreGeneration.CustomOreGen;
import org.terasology.oreGeneration.OreGenRegistry;

import java.util.Map;

@RegisterPlugin
public class OreRasterizer implements WorldRasterizer, WorldRasterizerPlugin {
    @Override
    public void initialize() {
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        OreFacet oreFacet = chunkRegion.getFacet(OreFacet.class);

        BlockManager blockManager = CoreRegistry.get(BlockManager.class);
        OreGenRegistry oreGenRegistrySystem = CoreRegistry.get(OreGenRegistry.class);

        for (CustomOreGen oreGenCreator : oreGenRegistrySystem.iterateDefinitions()) {
            Map<StructureNodeType, Block> nodeTypeToBlocks = Maps.newHashMap();
            for (Vector3i position : ChunkConstants.CHUNK_REGION) {
                StructureNodeType nodeType = oreFacet.get(oreGenCreator, position);
                if (nodeType != null && oreGenCreator.canReplaceBlock(chunk.chunkToWorldPosition(position),
                        chunkRegion)) {
                    if (!nodeTypeToBlocks.containsKey(nodeType)) {
                        nodeTypeToBlocks.put(nodeType, oreGenCreator.getReplacementBlock(nodeType));
                    }
                    Block block = nodeTypeToBlocks.get(nodeType);
                    chunk.setBlock(position, block);
                }
            }
        }
    }
}
