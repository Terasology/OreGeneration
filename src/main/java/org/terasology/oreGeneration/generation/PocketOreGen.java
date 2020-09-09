// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.generation;

import org.terasology.customOreGen.PDist;
import org.terasology.customOreGen.PocketStructureDefinition;
import org.terasology.customOreGen.StructureDefinition;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.engine.registry.In;
import org.terasology.engine.world.block.Block;
import org.terasology.engine.world.block.BlockManager;
import org.terasology.engine.world.generation.GeneratingRegion;
import org.terasology.oreGeneration.components.BasePocketOreGenComponent;

public abstract class PocketOreGen extends BaseOreGen {
    private final BasePocketOreGenComponent component;
    @In
    BlockManager blockManager;

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
    public Block getReplacementBlock(StructureNodeType structureNodeType) {
        return blockManager.getBlock(component.block);
    }

    @Override
    public int getSalt() {
        return component.block.hashCode();
    }
}
