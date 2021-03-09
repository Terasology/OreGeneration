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

import org.joml.Vector3i;
import org.terasology.engine.world.generation.GeneratingRegion;
import org.terasology.engine.world.generation.Region;
import org.terasology.engine.world.generation.facets.DensityFacet;
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
