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
package org.terasology.oreGeneration.generation;

import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.engine.world.generation.WorldFacet3D;
import org.terasology.oreGeneration.CustomOreGen;

public interface MappedStructureNodeTypeFieldFacet3D extends WorldFacet3D {

    StructureNodeType get(CustomOreGen oreGenCreator, int x, int y, int z);

    default StructureNodeType get(CustomOreGen oreGenCreator, Vector3ic pos) {
        return get(oreGenCreator, pos.x(), pos.y(), pos.z());
    }

    StructureNodeType getWorld(CustomOreGen oreGenCreator, int x, int y, int z);

    default StructureNodeType getWorld(CustomOreGen oreGenCreator, Vector3ic pos) {
        return getWorld(oreGenCreator, pos.x(), pos.y(), pos.z());
    }

    StructureNodeType[] getInternal(CustomOreGen oreGenCreator);

    void set(CustomOreGen oreGenCreator, int x, int y, int z, StructureNodeType value);

    void set(CustomOreGen oreGenCreator, Vector3ic pos, StructureNodeType value);

    void setWorld(CustomOreGen oreGenCreator, int x, int y, int z, StructureNodeType value);

    void setWorld(CustomOreGen oreGenCreator, Vector3i pos, StructureNodeType value);

    void set(CustomOreGen oreGenCreator, StructureNodeType[] newData);
}
