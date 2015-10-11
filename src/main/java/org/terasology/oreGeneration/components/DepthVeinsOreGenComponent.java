/*
 * Copyright 2015 MovingBlocks
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
package org.terasology.oreGeneration.components;

import org.terasology.customOreGen.StructureNodeType;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.facets.DensityFacet;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

public class DepthVeinsOreGenComponent extends VeinsOreGenComponent {
    public int minDepth;
    public int maxDepth;

    @Override
    protected boolean isInRange(GeneratingRegion region) {
        // find the average surface height
        SurfaceHeightFacet surfaceHeightFacet = region.getRegionFacet(SurfaceHeightFacet.class);
        float averageSurfaceHeight = PocketOreGenComponent.getAverageSurfaceHeight(surfaceHeightFacet);
        // see if this region is even in range of this ore gen
        float depthMaxY = averageSurfaceHeight - minDepth;
        float depthMinY = averageSurfaceHeight - maxDepth;

        return depthMaxY > region.getRegion().minY() || depthMinY > region.getRegion().maxY();
    }

    @Override
    public boolean canReplaceBlock(Vector3i worldPosition, Region region) {
        DensityFacet densityFacet = region.getFacet(DensityFacet.class);
        float densityFacetValue = densityFacet.getWorld(worldPosition);
        return densityFacetValue > minDepth && densityFacetValue < maxDepth;
    }

    @Override
    public Block getReplacementBlock(StructureNodeType structureNodeType) {
        BlockManager blockManager = CoreRegistry.get(BlockManager.class);
        return blockManager.getBlock(block);
    }

    @Override
    public int getSalt() {
        return block.hashCode();
    }
}
