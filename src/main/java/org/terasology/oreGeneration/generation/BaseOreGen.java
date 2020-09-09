// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.generation;

import org.terasology.customOreGen.StructureDefinition;
import org.terasology.engine.world.generation.GeneratingRegion;
import org.terasology.engine.world.generation.Region;
import org.terasology.math.geom.Vector3i;
import org.terasology.oreGeneration.CustomOreGen;

public abstract class BaseOreGen implements CustomOreGen {
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
        return true;
    }

    protected abstract StructureDefinition getStructureDefinition(GeneratingRegion region, float scaleFactor);

    @Override
    public boolean canReplaceBlock(Vector3i worldPosition, Region region) {
        return true;
    }
}
