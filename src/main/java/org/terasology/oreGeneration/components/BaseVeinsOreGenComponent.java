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

public abstract class BaseVeinsOreGenComponent {
    public String block;
    // frequency for every 10 cubed blocks
    public float frequency = 1f;
    public float frequencyRange;
    public float motherLodeRadius = 2f;
    public float motherLodeRadiusRange = 1f;
    public float motherlodeRangeLimit = 32;
    public float motherlodeRangeLimitRange = 32;
    public float branchFrequency = 4f;
    public float branchFrequencyRange = 1f;
    public float branchInclination = 0f;
    public float branchInclinationRange = 0.1f;
    public float branchLength = 40f;
    public float branchLengthRange = 10f;
    public float branchHeightLimit = 100f;
    public float branchHeightLimitRange = 0f;
    public float density = 1f;
    public float densityRange = 0f;
    public float segmentForkFrequency = 0.3f;
    public float segmentForkFrequencyRange = 0.1f;
    public float segmentForkLengthMultiplier = 0.25f;
    public float segmentForkLengthMultiplierRange = 0f;
    public float segmentLength = 5f;
    public float segmentLengthRange = 0f;
    public float segmentAngle = 0.5f;
    public float segmentAngleRange = 0.5f;
    public float segmentRadius = 4f;
    public float segmentRadiusRange = 1f;
    public float blockRadiusMultiplier = 1f;
    public float blockRadiusMultiplierRange = 0f;
/*


    public float segmentForkFrequency = 0.2f;
    public float segmentForkFrequencyRange;
    public float segmentForkLengthMultiplier = 0.75f;
    public float segmentForkLengthMultiplierRange = 0.25f;
    public float segmentLength = 15f;
    public float segmentLengthRange = 6f;
    public float segmentAngle = 0.5f;
    public float segmentAngleRange = 0.5f;
    public float segmentRadius = 0.5f;
    public float segmentRadiusRange = 0.3f;
    public float blockRadiusMultiplier = 1f;
    public float blockRadiusMultiplierRange = 0.1f;
 */
}
