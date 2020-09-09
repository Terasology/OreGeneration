// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.generation;

import org.terasology.engine.math.Region3i;
import org.terasology.engine.world.generation.Border3D;

public class OreFacet extends BaseMappedStructureNodeTypeFieldFacet3D {
    public OreFacet(Region3i targetRegion, Border3D border) {
        super(targetRegion, border);
    }
}
