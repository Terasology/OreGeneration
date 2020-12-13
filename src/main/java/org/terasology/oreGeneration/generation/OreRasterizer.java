/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.oreGeneration.generation;

import com.google.common.collect.Maps;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.math.JomlUtil;
import org.terasology.math.geom.Vector3i;
import org.terasology.oreGeneration.CustomOreGen;
import org.terasology.oreGeneration.OreGenRegistry;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.ChunkConstants;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;
import org.terasology.world.generation.WorldRasterizerPlugin;
import org.terasology.world.generator.plugin.RegisterPlugin;

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
                StructureNodeType nodeType = oreFacet.get(oreGenCreator, JomlUtil.from(position));
                if (nodeType != null
                    && oreGenCreator.canReplaceBlock(chunk.chunkToWorldPosition(JomlUtil.from(position), new org.joml.Vector3i()), chunkRegion)
                    && chunk.getBlock(position).getBlockFamily().hasCategory("rock")
                ) {
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
