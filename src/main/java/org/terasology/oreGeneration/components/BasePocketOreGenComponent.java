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

import org.terasology.customOreGen.PDist;
import org.terasology.customOreGen.PocketStructureDefinition;
import org.terasology.customOreGen.StructureDefinition;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.entitySystem.Component;
import org.terasology.math.geom.Vector3i;
import org.terasology.oreGeneration.CustomOreGen;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.facets.DensityFacet;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

public class BasePocketOreGenComponent implements Component, CustomOreGen {
    public String block;
    // frequency for every 10 cubed blocks
    public float frequency = 1f;
    public float frequencyRange;
    public float radius = 2f;
    public float radiusRange = 1f;
    public float thickness = 6f;
    public float thicknessRange = 3f;
    public float angle = 1f;
    public float angleRange = 1f;
    public float multiplier = 1f;
    public float multiplierRange;
    public float density = 0.7f;
    public float densityRange = 0.2f;
    public float noiseLevel = 0.2f;
    public float noiseLevelRange = 0.2f;
    public float noiseCutoff;
    public float noiseCutoffRange;
    // This is the density at which ore generation will stop generating. Useful to prevent generating ores above the surface
    public int densityCutoff = 2;

    @Override
    public StructureDefinition createStructureDefinition(GeneratingRegion region) {
        if (!isInRange(region)) {
            return null;
        }

        Vector3i regionSize = region.getRegion().size();
        float scaleFactor = regionSize.getY() / 10f;

        StructureDefinition structureDefinition = getStructureDefinition(region, scaleFactor);

        return structureDefinition;
    }

    protected boolean isInRange(GeneratingRegion region) {
        // find the average surface height
        SurfaceHeightFacet surfaceHeightFacet = region.getRegionFacet(SurfaceHeightFacet.class);
        float averageSurfaceHeight = getAverageSurfaceHeight(surfaceHeightFacet);

        // see if this region is even in range of this ore gen
        return region.getRegion().minY() < averageSurfaceHeight;
    }

    public static float getAverageSurfaceHeight(SurfaceHeightFacet surfaceHeightFacet) {
        float[] values = surfaceHeightFacet.getInternal();
        float total = 0;
        // averaging every single value takes too much time, only use some of the values in our average
        int sampleRate = 4;
        for (int i = 0; i < values.length; i = i + sampleRate) {
            total += values[i];
        }
        return total / (values.length / sampleRate);
    }

    private StructureDefinition getStructureDefinition(GeneratingRegion region, float scaleFactor) {
        return new PocketStructureDefinition(
                new PDist(frequency * scaleFactor, frequencyRange * scaleFactor),
                new PDist(radius, radiusRange),
                new PDist(thickness, thicknessRange),
                new PDist(region.getRegion().sizeY() / 2, region.getRegion().sizeY() / 2),
                new PDist(angle, angleRange),
                new PDist(multiplier, multiplierRange),
                new PDist(density, densityRange),
                new PDist(noiseLevel, noiseLevelRange),
                new PDist(noiseCutoff, noiseCutoffRange));
    }

    @Override
    public boolean canReplaceBlock(Vector3i worldPosition, Region region) {
        DensityFacet densityFacet = region.getFacet(DensityFacet.class);
        float densityFacetValue = densityFacet.getWorld(worldPosition);
        return densityFacetValue > densityCutoff;
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
