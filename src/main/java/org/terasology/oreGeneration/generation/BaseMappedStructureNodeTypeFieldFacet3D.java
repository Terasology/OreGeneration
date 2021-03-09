/*
 * Copyright 2020 MovingBlocks
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

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.engine.world.block.BlockRegion;
import org.terasology.engine.world.generation.Border3D;
import org.terasology.engine.world.generation.facets.base.BaseFacet3D;
import org.terasology.oreGeneration.CustomOreGen;

import java.util.Map;

public abstract class BaseMappedStructureNodeTypeFieldFacet3D extends BaseFacet3D implements MappedStructureNodeTypeFieldFacet3D {

    private final Vector3i size;
    private Map<CustomOreGen, StructureNodeType[]> data = Maps.newHashMap();

    public BaseMappedStructureNodeTypeFieldFacet3D(BlockRegion targetRegion, Border3D border) {
        super(targetRegion, border);
        size = getRelativeRegion().getSize(new Vector3i());
    }

    private StructureNodeType[] createData() {
        return new StructureNodeType[size.x * size.y * size.z];
    }

    @Override
    public StructureNodeType get(CustomOreGen oreGenCreator, int x, int y, int z) {
        return getData(oreGenCreator)[getRelativeIndex(x, y, z)];
    }

    @Override
    public StructureNodeType get(CustomOreGen oreGenCreator, Vector3ic pos) {
        return get(oreGenCreator, pos.x(), pos.y(), pos.z());
    }

    @Override
    public StructureNodeType getWorld(CustomOreGen oreGenCreator, int x, int y, int z) {
        return getData(oreGenCreator)[getWorldIndex(x, y, z)];
    }

    @Override
    public StructureNodeType getWorld(CustomOreGen oreGenCreator, Vector3ic pos) {
        return getWorld(oreGenCreator, pos.x(), pos.y(), pos.z());
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
    public void set(CustomOreGen oreGenCreator, Vector3ic pos, StructureNodeType value) {
        set(oreGenCreator, pos.x(), pos.y(), pos.z(), value);
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
