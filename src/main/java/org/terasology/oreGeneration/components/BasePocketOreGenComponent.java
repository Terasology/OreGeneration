/*
 * Copyright 2015 MovingBlocks
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
