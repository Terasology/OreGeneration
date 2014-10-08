package org.terasology.oreGeneration.components;

import org.terasology.customOreGen.PDist;
import org.terasology.customOreGen.PocketStructureDefinition;
import org.terasology.customOreGen.StructureDefinition;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.entitySystem.Component;
import org.terasology.math.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.facets.DensityFacet;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

public class DepthPocketOreGenComponent implements Component, CustomOreGenCreator {
    public String block;
    public int depth;
    public int depthRange;
    public float frequency = 1f;
    public float frequencyRange;
    public float radius = 2f;
    public float radiusRange = 1f;
    public float thickness = 6f;
    public float thicknessRange = 3f;
    public float angle = 1f;
    public float angleRange = 1f;
    public float multiplier = 1f;
    public float multiplierRange = 0f;
    public float density = 0.7f;
    public float densityRange = 0.2f;
    public float noiseLevel = 0.2f;
    public float noiseLevelRange = 0.2f;
    public float noiseCutoff = 0f;
    public float noiseCutoffRange = 0f;


    @Override
    public StructureDefinition createStructureDefinition(GeneratingRegion region) {
        // find the average surface height
        SurfaceHeightFacet surfaceHeightFacet = region.getRegionFacet(SurfaceHeightFacet.class);
        float[] values = surfaceHeightFacet.getInternal();
        float total = 0;
        // averaging every single value takes too much time, only use some of the values in our average
        int sampleRate = 4;
        for (int i = 0; i < values.length; i = i + sampleRate) {
            total += values[i];
        }
        float averageSurfaceHeight = total / (values.length / sampleRate);

        // see if this region is even in range of this ore gen
        PDist depthDist = new PDist(depth, depthRange);
        float depthMaxY = averageSurfaceHeight - depthDist.getMin();
        float depthMinY = averageSurfaceHeight - depthDist.getMax();
        if (depthMaxY < region.getRegion().minY()
                || depthMinY > region.getRegion().maxY()) {
            // shortcut early, this region is completely out of range
            return null;
        }


        // generate structures for the whole chunk, let the densityFacet filter out the depth stuff
        float amountOfDepthRangeCovered = (float) region.getRegion().sizeY() / (depthRange * 2);
        StructureDefinition structureDefinition = new PocketStructureDefinition(
                new PDist(frequency * amountOfDepthRangeCovered, frequencyRange * amountOfDepthRangeCovered), new PDist(radius, radiusRange), new PDist(thickness, thicknessRange),
                new PDist(region.getRegion().sizeY() / 2, region.getRegion().sizeY() / 2), new PDist(angle, angleRange),
                new PDist(multiplier, multiplierRange), new PDist(density, densityRange),
                new PDist(noiseLevel, noiseLevelRange), new PDist(noiseCutoff, noiseCutoffRange));

        return structureDefinition;
    }

    @Override
    public boolean canReplaceBlock(Vector3i worldPosition, Region region) {
        DensityFacet densityFacet = region.getFacet(DensityFacet.class);
        float densityFacetValue = densityFacet.getWorld(worldPosition);
        PDist depthDist = new PDist(depth, depthRange);
        return densityFacetValue > depthDist.getMin() && densityFacetValue < depthDist.getMax();
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
