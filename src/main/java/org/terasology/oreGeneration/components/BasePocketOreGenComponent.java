// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.oreGeneration.components;

import org.terasology.gestalt.entitysystem.component.Component;

public class BasePocketOreGenComponent<T extends BasePocketOreGenComponent<T>> implements Component<T> {
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

    @Override
    public void copyFrom(T other) {
        this.block = other.block;
        this.frequency = other.frequency;
        this.frequencyRange = other.frequencyRange;
        this.radius = other.radius;
        this.radiusRange = other.radiusRange;
        this.thickness = other.thickness;
        this.thicknessRange = other.thicknessRange;
        this.angle = other.angle;
        this.angleRange = other.angleRange;
        this.multiplier = other.multiplier;
        this.multiplierRange = other.multiplierRange;
        this.density = other.density;
        this.densityRange = other.densityRange;
        this.noiseLevel = other.noiseLevel;
        this.noiseLevelRange = other.noiseLevelRange;
        this.noiseCutoff = other.noiseCutoff;
        this.noiseCutoffRange = other.noiseCutoffRange;
    }
}
