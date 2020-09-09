// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.generation;

import org.terasology.customOreGen.Structure;
import org.terasology.customOreGen.StructureDefinition;
import org.terasology.customOreGen.StructureNodeType;
import org.terasology.engine.registry.CoreRegistry;
import org.terasology.engine.world.generation.FacetProviderPlugin;
import org.terasology.engine.world.generation.GeneratingRegion;
import org.terasology.engine.world.generation.Produces;
import org.terasology.engine.world.generator.plugin.RegisterPlugin;
import org.terasology.math.geom.Vector3i;
import org.terasology.oreGeneration.CustomOreGen;
import org.terasology.oreGeneration.OreGenRegistry;

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
                        public void replaceBlock(Vector3i position, StructureNodeType structureNodeType,
                                                 Vector3i distanceToCenter) {
                            facet.set(creator, position, structureNodeType);
                        }

                        @Override
                        public boolean canReplace(int x, int y, int z) {
                            return facet.getRelativeRegion().encompasses(x, y, z);
                        }
                    });
                }
            }
        }

        region.setRegionFacet(OreFacet.class, facet);
    }
}
