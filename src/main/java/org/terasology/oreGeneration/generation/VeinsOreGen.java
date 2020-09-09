// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.generation;

import org.terasology.customOreGen.PDist;
import org.terasology.customOreGen.StructureDefinition;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.customOreGen.VeinsStructureDefinition;
import org.terasology.engine.registry.In;
import org.terasology.engine.world.block.Block;
import org.terasology.engine.world.block.BlockManager;
import org.terasology.engine.world.generation.GeneratingRegion;
import org.terasology.oreGeneration.components.BaseVeinsOreGenComponent;

public abstract class VeinsOreGen extends BaseOreGen {
    private final BaseVeinsOreGenComponent component;
    @In
    BlockManager blockManager;

    public VeinsOreGen(BaseVeinsOreGenComponent component) {
        this.component = component;
    }

    protected StructureDefinition getStructureDefinition(GeneratingRegion region, float scaleFactor) {
        return new VeinsStructureDefinition(
                new PDist(component.frequency * scaleFactor, component.frequencyRange * scaleFactor),
                new PDist(component.motherLodeRadius, component.motherLodeRadiusRange),
                new PDist(component.motherlodeRangeLimit, component.motherlodeRangeLimitRange),
                new PDist(component.branchFrequency, component.branchFrequencyRange),
                new PDist(component.branchInclination, component.branchInclinationRange),
                new PDist(component.branchLength, component.branchLengthRange),
                new PDist(component.branchHeightLimit, component.branchHeightLimitRange),
                new PDist(component.segmentForkFrequency, component.segmentForkFrequencyRange),
                new PDist(component.segmentForkLengthMultiplier, component.segmentForkLengthMultiplierRange),
                new PDist(component.segmentLength, component.segmentLengthRange),
                new PDist(component.segmentAngle, component.segmentAngleRange),
                new PDist(component.segmentRadius, component.segmentRadiusRange),
                new PDist(component.density, component.densityRange),
                new PDist(component.blockRadiusMultiplier, component.blockRadiusMultiplierRange));
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
