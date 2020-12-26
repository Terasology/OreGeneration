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

import org.joml.Vector3ic;
import org.terasology.customOreGen.Structure;
import org.terasology.customOreGen.StructureDefinition;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.oreGeneration.CustomOreGen;
import org.terasology.oreGeneration.OreGenRegistry;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.generation.FacetProviderPlugin;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generator.plugin.RegisterPlugin;

import java.util.Collection;

@RegisterPlugin
@Produces(OreFacet.class)
public class OreFacetProvider implements FacetProviderPlugin {
    private long seed;

    @Override
    public void setSeed(long seed) {
        this.seed = seed;
    }

    @Override
    public void process(GeneratingRegion region) {
        OreGenRegistry oreGenRegistrySystem = CoreRegistry.get(OreGenRegistry.class);

        final OreFacet facet = new OreFacet(region.getRegion(), region.getBorderForFacet(OreFacet.class));

        final GeneratingRegion finalRegion = region;
        for (final CustomOreGen creator : oreGenRegistrySystem.iterateDefinitions()) {
            StructureDefinition structureDefinition = creator.createStructureDefinition(region);
            if (structureDefinition != null) {
                Collection<Structure> structures = structureDefinition.generateStructures(seed + creator.getSalt(),
                    region.getRegion());

                for (Structure structure : structures) {
                    structure.generateStructure(new Structure.StructureCallback() {
                        @Override
                        public void replaceBlock(Vector3ic position, StructureNodeType structureNodeType, Vector3ic distanceToCenter) {
                            facet.set(creator, position, structureNodeType);
                        }

                        @Override
                        public boolean canReplace(int x, int y, int z) {
                            return facet.getRelativeRegion().contains(x, y, z);
                        }
                    });
                }
            }
        }

        region.setRegionFacet(OreFacet.class, facet);
    }
}
