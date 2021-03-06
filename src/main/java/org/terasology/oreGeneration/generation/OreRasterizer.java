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
import org.joml.Vector3ic;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.engine.registry.CoreRegistry;
import org.terasology.engine.world.block.Block;
import org.terasology.engine.world.chunks.Chunk;
import org.terasology.engine.world.chunks.Chunks;
import org.terasology.engine.world.generation.Region;
import org.terasology.engine.world.generation.WorldRasterizer;
import org.terasology.engine.world.generation.WorldRasterizerPlugin;
import org.terasology.engine.world.generator.plugin.RegisterPlugin;
import org.terasology.oreGeneration.CustomOreGen;
import org.terasology.oreGeneration.OreGenRegistry;

import java.util.Map;

@RegisterPlugin
public class OreRasterizer implements WorldRasterizer, WorldRasterizerPlugin {
    @Override
    public void initialize() {
    }

    @Override
    public void generateChunk(Chunk chunk, Region chunkRegion) {
        OreFacet oreFacet = chunkRegion.getFacet(OreFacet.class);
        OreGenRegistry oreGenRegistrySystem = CoreRegistry.get(OreGenRegistry.class);

        for (CustomOreGen oreGenCreator : oreGenRegistrySystem.iterateDefinitions()) {
            Map<StructureNodeType, Block> nodeTypeToBlocks = Maps.newHashMap();
            for (Vector3ic position : Chunks.CHUNK_REGION) {
                StructureNodeType nodeType = oreFacet.get(oreGenCreator, position);
                if (nodeType != null
                    && oreGenCreator.canReplaceBlock(chunk.chunkToWorldPosition(position, new org.joml.Vector3i()), chunkRegion)
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
