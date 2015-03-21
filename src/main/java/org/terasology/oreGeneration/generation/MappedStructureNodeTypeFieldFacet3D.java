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

import org.terasology.customOreGen.StructureNodeType;
import org.terasology.math.geom.Vector3i;
import org.terasology.oreGeneration.components.CustomOreGenCreator;
import org.terasology.world.generation.WorldFacet3D;

public interface MappedStructureNodeTypeFieldFacet3D extends WorldFacet3D {

    StructureNodeType get(CustomOreGenCreator oreGenCreator, int x, int y, int z);

    StructureNodeType get(CustomOreGenCreator oreGenCreator, Vector3i pos);

    StructureNodeType getWorld(CustomOreGenCreator oreGenCreator, int x, int y, int z);

    StructureNodeType getWorld(CustomOreGenCreator oreGenCreator, Vector3i pos);

    StructureNodeType[] getInternal(CustomOreGenCreator oreGenCreator);

    void set(CustomOreGenCreator oreGenCreator, int x, int y, int z, StructureNodeType value);

    void set(CustomOreGenCreator oreGenCreator, Vector3i pos, StructureNodeType value);

    void setWorld(CustomOreGenCreator oreGenCreator, int x, int y, int z, StructureNodeType value);

    void setWorld(CustomOreGenCreator oreGenCreator, Vector3i pos, StructureNodeType value);

    void set(CustomOreGenCreator oreGenCreator, StructureNodeType[] newData);
}
