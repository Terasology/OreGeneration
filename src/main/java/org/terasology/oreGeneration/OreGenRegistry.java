// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration;

import org.terasology.gestalt.entitysystem.component.Component;

import java.util.function.Function;

public interface OreGenRegistry {
    <T extends Component> void registrationComponentTrigger(Class<T> c, Function<T, CustomOreGen> factory);

    Iterable<CustomOreGen> iterateDefinitions();

    String reloadOreGenRegistry();
}
