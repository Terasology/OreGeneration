// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.components;

public class DepthPocketOreGenComponent extends BasePocketOreGenComponent<DepthPocketOreGenComponent> {
    public int minDepth;
    public int maxDepth;

    @Override
    public void copyFrom(DepthPocketOreGenComponent other) {
        super.copyFrom(other);
        this.minDepth = other.minDepth;
        this.maxDepth = other.maxDepth;
    }
}
