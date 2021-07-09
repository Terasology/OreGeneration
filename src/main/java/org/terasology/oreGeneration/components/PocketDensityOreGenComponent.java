// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.components;

public class PocketDensityOreGenComponent extends BasePocketOreGenComponent<PocketDensityOreGenComponent>  {
    public int minDensity = 2;
    public int maxDensity = Integer.MAX_VALUE;

    @Override
    public void copy(PocketDensityOreGenComponent other) {
        super.copy(other);
        this.minDensity = other.minDensity;
        this.maxDensity = other.maxDensity;
    }
}
