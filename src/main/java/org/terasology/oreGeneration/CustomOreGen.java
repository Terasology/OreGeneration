// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration;

import org.terasology.customOreGen.StructureDefinition;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.engine.world.block.Block;
import org.terasology.engine.world.generation.GeneratingRegion;
import org.terasology.engine.world.generation.Region;
import org.terasology.math.geom.Vector3i;

public interface CustomOreGen {
    /**
     * Called at facet provider time
     */
    StructureDefinition createStructureDefinition(GeneratingRegion region);

    /**
     * Called at rasterize time
     */
    boolean canReplaceBlock(Vector3i worldPosition, Region region);

    /**
     * Called at rasterize time
     */
    Block getReplacementBlock(StructureNodeType structureNodeType);

    int getSalt();
}
