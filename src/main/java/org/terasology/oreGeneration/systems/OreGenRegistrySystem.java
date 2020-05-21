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
package org.terasology.oreGeneration.systems;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import org.terasology.entitySystem.Component;
import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.entitySystem.prefab.PrefabManager;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.console.commandSystem.annotations.Command;
import org.terasology.oreGeneration.CustomOreGen;
import org.terasology.oreGeneration.OreGenRegistry;
import org.terasology.oreGeneration.components.OreGenDefinitionComponent;
import org.terasology.oreGeneration.components.PocketDensityOreGenComponent;
import org.terasology.oreGeneration.components.VeinsDensityOreGenComponent;
import org.terasology.oreGeneration.generation.PocketDensityOreGen;
import org.terasology.oreGeneration.generation.VeinsDensityOreGen;
import org.terasology.registry.In;
import org.terasology.registry.InjectionHelper;
import org.terasology.registry.Share;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@RegisterSystem
@Share(OreGenRegistry.class)
public class OreGenRegistrySystem extends BaseComponentSystem implements OreGenRegistry {
    @In
    PrefabManager prefabManager;

    Multimap<Class, Function<Component, CustomOreGen>> registrationComponentTriggers = HashMultimap.create();
    Set<CustomOreGen> registry;

    public void initialise() {
        super.initialise();
        registrationComponentTrigger(VeinsDensityOreGenComponent.class, x -> new VeinsDensityOreGen(x));
        registrationComponentTrigger(PocketDensityOreGenComponent.class, x -> new PocketDensityOreGen(x));
    }

    @Override
    public void postBegin() {
        super.postBegin();
        createRegistry();
    }

    private void createRegistry() {
        registry = Sets.newHashSet();
        for (Prefab prefab : prefabManager.listPrefabs(OreGenDefinitionComponent.class)) {
            for (Map.Entry<Class, Function<Component, CustomOreGen>> entry : registrationComponentTriggers.entries()) {
                Component component = prefab.getComponent(entry.getKey());
                if (component != null) {
                    CustomOreGen customOreGen = entry.getValue().apply(component);
                    InjectionHelper.inject(customOreGen);
                    registry.add(customOreGen);
                }
            }
        }
    }

    @Override
    public <T extends Component> void registrationComponentTrigger(Class<T> c, Function<T, CustomOreGen> factory) {
        registrationComponentTriggers.put(c, (Function<Component, CustomOreGen>) factory);
    }

    @Override
    public Iterable<CustomOreGen> iterateDefinitions() {
        return registry;
    }

    @Override
    @Command(shortDescription = "Reloads the ore gen registry")
    public String reloadOreGenRegistry() {
        createRegistry();
        return "Ore registry reloaded";
    }
}
