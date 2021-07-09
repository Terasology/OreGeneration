// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
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

    protected void copy(BaseVeinsOreGenComponent other) {
        this.block = other.block;
        this.frequency = other.frequency;
        this.frequencyRange = other.frequencyRange;
        this.motherLodeRadius = other.motherLodeRadius;
        this.motherLodeRadiusRange = other.motherLodeRadiusRange;
        this.motherlodeRangeLimit = other.motherlodeRangeLimit;
        this.motherlodeRangeLimitRange = other.motherlodeRangeLimitRange;
        this.branchFrequency = other.branchFrequency;
        this.branchFrequencyRange = other.branchFrequencyRange;
        this.branchInclination = other.branchInclination;
        this.branchInclinationRange = other.branchInclinationRange;
        this.branchLength = other.branchLength;
        this.branchLengthRange = other.branchLengthRange;
        this.branchHeightLimit = other.branchHeightLimit;
        this.branchHeightLimitRange = other.branchHeightLimitRange;
        this.density = other.density;
        this.densityRange = other.densityRange;
        this.segmentForkFrequency = other.segmentForkFrequency;
        this.segmentForkFrequencyRange = other.segmentForkFrequencyRange;
        this.segmentForkLengthMultiplier = other.segmentForkLengthMultiplier;
        this.segmentForkLengthMultiplierRange = other.segmentForkLengthMultiplierRange;
        this.segmentLength = other.segmentLength;
        this.segmentLengthRange = other.segmentLengthRange;
        this.segmentAngle = other.segmentAngle;
        this.segmentAngleRange = other.segmentAngleRange;
        this.segmentRadius = other.segmentRadius;
        this.segmentRadiusRange = other.segmentRadiusRange;
        this.blockRadiusMultiplier = other.blockRadiusMultiplier;
        this.blockRadiusMultiplierRange = other.blockRadiusMultiplierRange;
    }
}
