// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.components;

import org.terasology.engine.entitySystem.Component;

public class BasePocketOreGenComponent implements Component {
    public String block;
    // frequency for every 10 cubed blocks
    public float frequency = 1f;
    public float frequencyRange;
    public float radius = 2f;
    public float radiusRange = 1f;
    public float thickness = 6f;
    public float thicknessRange = 3f;
    public float angle = 1f;
    public float angleRange = 1f;
    public float multiplier = 1f;
    public float multiplierRange;
    public float density = 0.7f;
    public float densityRange = 0.2f;
    public float noiseLevel = 0.2f;
    public float noiseLevelRange = 0.2f;
    public float noiseCutoff;
    public float noiseCutoffRange;
}
