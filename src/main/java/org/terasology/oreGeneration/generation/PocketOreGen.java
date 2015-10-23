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
package org.terasology.oreGeneration.generation;

import org.terasology.customOreGen.PDist;
import org.terasology.customOreGen.PocketStructureDefinition;
import org.terasology.customOreGen.StructureDefinition;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.math.geom.Vector3i;
import org.terasology.oreGeneration.components.BasePocketOreGenComponent;
import org.terasology.registry.In;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.facets.DensityFacet;

public abstract class PocketOreGen extends BaseOreGen {
    @In
    BlockManager blockManager;

    private final BasePocketOreGenComponent component;

    public PocketOreGen(BasePocketOreGenComponent component) {
        this.component = component;
    }

    protected StructureDefinition getStructureDefinition(GeneratingRegion region, float scaleFactor) {
        return new PocketStructureDefinition(
                new PDist(component.frequency * scaleFactor, component.frequencyRange * scaleFactor),
                new PDist(component.radius, component.radiusRange),
                new PDist(component.thickness, component.thicknessRange),
                new PDist(region.getRegion().sizeY() / 2, region.getRegion().sizeY() / 2),
                new PDist(component.angle, component.angleRange),
                new PDist(component.multiplier, component.multiplierRange),
                new PDist(component.density, component.densityRange),
                new PDist(component.noiseLevel, component.noiseLevelRange),
                new PDist(component.noiseCutoff, component.noiseCutoffRange));
    }

    @Override
    public boolean canReplaceBlock(Vector3i worldPosition, Region region) {
        DensityFacet densityFacet = region.getFacet(DensityFacet.class);
        float densityFacetValue = densityFacet.getWorld(worldPosition);
        return densityFacetValue > component.densityCutoff;
    }

    @Override
    public Block getReplacementBlock(StructureNodeType structureNodeType) {
        return blockManager.getBlock(component.block);
    }

    @Override
    public int getSalt() {
        return component.block.hashCode();
    }
}
