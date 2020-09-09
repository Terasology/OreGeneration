// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.generation;

import org.terasology.customOreGen.StructureNodeType;
import org.terasology.engine.world.generation.WorldFacet3D;
import org.terasology.math.geom.Vector3i;
import org.terasology.oreGeneration.CustomOreGen;

public interface MappedStructureNodeTypeFieldFacet3D extends WorldFacet3D {

    StructureNodeType get(CustomOreGen oreGenCreator, int x, int y, int z);

    StructureNodeType get(CustomOreGen oreGenCreator, Vector3i pos);

    StructureNodeType getWorld(CustomOreGen oreGenCreator, int x, int y, int z);

    StructureNodeType getWorld(CustomOreGen oreGenCreator, Vector3i pos);

    StructureNodeType[] getInternal(CustomOreGen oreGenCreator);

    void set(CustomOreGen oreGenCreator, int x, int y, int z, StructureNodeType value);

    void set(CustomOreGen oreGenCreator, Vector3i pos, StructureNodeType value);

    void setWorld(CustomOreGen oreGenCreator, int x, int y, int z, StructureNodeType value);

    void setWorld(CustomOreGen oreGenCreator, Vector3i pos, StructureNodeType value);

    void set(CustomOreGen oreGenCreator, StructureNodeType[] newData);
}
