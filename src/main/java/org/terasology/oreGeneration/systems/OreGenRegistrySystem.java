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
package org.terasology.oreGeneration.systems;

import com.google.common.collect.Sets;
import org.terasology.entitySystem.Component;
import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.entitySystem.prefab.PrefabManager;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.oreGeneration.components.CustomOreGenCreator;
import org.terasology.oreGeneration.components.OreGenDefinitionComponent;
import org.terasology.registry.In;
import org.terasology.registry.Share;

import java.util.Set;

@RegisterSystem
@Share(OreGenRegistrySystem.class)
public class OreGenRegistrySystem extends BaseComponentSystem {
    @In
    PrefabManager prefabManager;

    Set<CustomOreGenCreator> registry = Sets.newHashSet();

    public void initialise() {
        super.initialise();

        for (Prefab prefab : prefabManager.listPrefabs(OreGenDefinitionComponent.class)) {
            for (Component component : prefab.iterateComponents()) {
                if (component instanceof CustomOreGenCreator) {
                    registry.add((CustomOreGenCreator) component);
                }
            }
        }
    }

    public Iterable<CustomOreGenCreator> iterateDefinitions() {
        return registry;

    }
}
