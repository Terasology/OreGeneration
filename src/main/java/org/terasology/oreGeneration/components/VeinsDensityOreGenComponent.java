// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.components;

import org.terasology.gestalt.entitysystem.component.Component;

public class VeinsDensityOreGenComponent extends BaseVeinsOreGenComponent implements Component<VeinsDensityOreGenComponent> {
    public int minDensity = 2;
    public int maxDensity = Integer.MAX_VALUE;

    @Override
    public void copyFrom(VeinsDensityOreGenComponent other) {
        super.copy(other);
        this.minDensity = other.minDensity;
        this.maxDensity = other.maxDensity;
    }
}
