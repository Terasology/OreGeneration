/*
 * Copyright 2014 MovingBlocks
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
package org.terasology.oreGeneration.components;

import org.joml.Vector3ic;
import org.terasology.customOreGen.StructureDefinition;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.engine.world.block.Block;
import org.terasology.engine.world.generation.GeneratingRegion;
import org.terasology.engine.world.generation.Region;

public interface CustomOreGenCreator {
    /**
     * Called at facet provider time
     */
    StructureDefinition createStructureDefinition(GeneratingRegion region);

    /**
     * Called at rasterize time
     */
    boolean canReplaceBlock(Vector3ic worldPosition, Region region);

    /**
     * Called at rasterize time
     */
    Block getReplacementBlock(StructureNodeType structureNodeType);

    int getSalt();
}
