package org.terasology.oreGeneration.components;

import org.terasology.customOreGen.StructureDefinition;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.math.Vector3i;
import org.terasology.world.block.Block;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Region;

public interface CustomOreGenCreator {
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
