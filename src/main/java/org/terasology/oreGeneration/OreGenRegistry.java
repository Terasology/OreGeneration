// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration;

import org.terasology.engine.entitySystem.Component;

import java.util.function.Function;

public interface OreGenRegistry {
    <T extends Component> void registrationComponentTrigger(Class<T> c, Function<T, CustomOreGen> factory);

    Iterable<CustomOreGen> iterateDefinitions();

    String reloadOreGenRegistry();
}
