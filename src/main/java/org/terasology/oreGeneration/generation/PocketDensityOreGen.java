// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.generation;

import org.terasology.engine.world.generation.GeneratingRegion;
import org.terasology.engine.world.generation.Region;
import org.terasology.engine.world.generation.facets.DensityFacet;
import org.terasology.math.geom.Vector3i;
import org.terasology.oreGeneration.components.PocketDensityOreGenComponent;

public class PocketDensityOreGen extends PocketOreGen {
    private final PocketDensityOreGenComponent component;

    public PocketDensityOreGen(PocketDensityOreGenComponent component) {
        super(component);
        this.component = component;
    }

    protected boolean isInRange(GeneratingRegion region) {
        return true;
    }

    @Override
    public boolean canReplaceBlock(Vector3i worldPosition, Region region) {
        DensityFacet densityFacet = region.getFacet(DensityFacet.class);
        float densityFacetValue = densityFacet.getWorld(worldPosition);
        return densityFacetValue > component.minDensity && densityFacetValue < component.maxDensity;
    }
}
