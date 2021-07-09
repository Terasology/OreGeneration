// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.systems;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import org.terasology.engine.entitySystem.prefab.Prefab;
import org.terasology.engine.entitySystem.prefab.PrefabManager;
import org.terasology.engine.entitySystem.systems.BaseComponentSystem;
import org.terasology.engine.entitySystem.systems.RegisterSystem;
import org.terasology.engine.logic.console.commandSystem.annotations.Command;
import org.terasology.engine.registry.In;
import org.terasology.engine.registry.InjectionHelper;
import org.terasology.engine.registry.Share;
import org.terasology.gestalt.entitysystem.component.Component;
import org.terasology.oreGeneration.CustomOreGen;
import org.terasology.oreGeneration.OreGenRegistry;
import org.terasology.oreGeneration.components.OreGenDefinitionComponent;
import org.terasology.oreGeneration.components.PocketDensityOreGenComponent;
import org.terasology.oreGeneration.components.VeinsDensityOreGenComponent;
import org.terasology.oreGeneration.generation.PocketDensityOreGen;
import org.terasology.oreGeneration.generation.VeinsDensityOreGen;

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
