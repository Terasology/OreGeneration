// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.components;

import org.terasology.engine.entitySystem.Component;

public class PocketDensityOreGenComponent extends BasePocketOreGenComponent implements Component {
    public int minDensity = 2;
    public int maxDensity = Integer.MAX_VALUE;
}
