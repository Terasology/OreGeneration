// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.generation;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.engine.math.Region3i;
import org.terasology.engine.world.generation.Border3D;
import org.terasology.engine.world.generation.facets.base.BaseFacet3D;
import org.terasology.math.geom.Vector3i;
import org.terasology.oreGeneration.CustomOreGen;

import java.util.Map;

public abstract class BaseMappedStructureNodeTypeFieldFacet3D extends BaseFacet3D implements MappedStructureNodeTypeFieldFacet3D {

    private final Vector3i size;
    private final Map<CustomOreGen, StructureNodeType[]> data = Maps.newHashMap();

    public BaseMappedStructureNodeTypeFieldFacet3D(Region3i targetRegion, Border3D border) {
        super(targetRegion, border);
        size = getRelativeRegion().size();
    }

    private StructureNodeType[] createData() {
        return new StructureNodeType[size.x * size.y * size.z];
    }

    @Override
    public StructureNodeType get(CustomOreGen oreGenCreator, int x, int y, int z) {
        return getData(oreGenCreator)[getRelativeIndex(x, y, z)];
    }

    @Override
    public StructureNodeType get(CustomOreGen oreGenCreator, Vector3i pos) {
        return get(oreGenCreator, pos.x, pos.y, pos.z);
    }

    @Override
    public StructureNodeType getWorld(CustomOreGen oreGenCreator, int x, int y, int z) {
        return getData(oreGenCreator)[getWorldIndex(x, y, z)];
    }

    @Override
    public StructureNodeType getWorld(CustomOreGen oreGenCreator, Vector3i pos) {
        return getWorld(oreGenCreator, pos.x, pos.y, pos.z);
    }

    @Override
    public StructureNodeType[] getInternal(CustomOreGen oreGenCreator) {
        return data.get(oreGenCreator);
    }

    @Override
    public void set(CustomOreGen oreGenCreator, int x, int y, int z, StructureNodeType value) {
        getData(oreGenCreator)[getRelativeIndex(x, y, z)] = value;
    }

    private StructureNodeType[] getData(CustomOreGen oreGenCreator) {
        if (!data.containsKey(oreGenCreator)) {
            set(oreGenCreator, createData());
        }
        return data.get(oreGenCreator);
    }

    @Override
    public void set(CustomOreGen oreGenCreator, Vector3i pos, StructureNodeType value) {
        set(oreGenCreator, pos.x, pos.y, pos.z, value);
    }

    @Override
    public void setWorld(CustomOreGen oreGenCreator, int x, int y, int z, StructureNodeType value) {
        getData(oreGenCreator)[getWorldIndex(x, y, z)] = value;
    }

    @Override
    public void setWorld(CustomOreGen oreGenCreator, Vector3i pos, StructureNodeType value) {
        setWorld(oreGenCreator, pos.x, pos.y, pos.z, value);
    }

    @Override
    public void set(CustomOreGen oreGenCreator, StructureNodeType[] newData) {
        if (!data.containsKey(oreGenCreator)) {
            data.put(oreGenCreator, newData);
        } else {
            Preconditions.checkArgument(newData.length == data.get(oreGenCreator).length);
            System.arraycopy(newData, 0, data.get(oreGenCreator), 0, newData.length);
        }
    }

}
