#OreGeneration
=============

A plugin system for adding randomized ore generation to the world


##PocketOreGen
Pocket ore generation is small clumps of blocks that occur underground

###Minimal usage on a prefab

```
{
    "OreGenDefinition" :  {},
    "PocketOreGen" :
    {
        "block" : "Core:IronOre", //  What block will be placed
        "frequency" : 1 // How often this pocket will happen in 10 vertical blocks in the region
    }
}
```

###Defaults

```
{
    "OreGenDefinition" :  {},
    "PocketOreGen" :
    {
        "frequency" : 1,
        "frequencyRange" : 0,
        "radius" : 2,
        "radiusRange" : 1,
        "thickness" : 6,
        "thicknessRange" : 3,
        "angle" : 1,
        "angleRange" : 1,
        "multiplier" : 1,
        "multiplierRange" : 0,
        "density" : 0.7,
        "densityRange" : 0.2,
        "densityCutoff": 2,
        "noiseLevel" : 0.2,
        "noiseLevelRange" : 0.2,
        "noiseCutoff" : 0,
        "noiseCutoffRange" : 0,
        "densityCutoff" : 2
    }
}
```


##DepthPocketOreGen
This pocket ore gen is contained within a certain range of depth underground.

###Minimal usage on a prefab

```
{
    "OreGenDefinition" :  {},
    "DepthPocketOreGen" :
    {
        "block" : "Core:IronOre", //  What block will be placed
        "frequency" : 1, // How often this pocket will happen in 10 vertical blocks in the region
        "minDepth" : 4, // The minimum depth that this pocket will occur
        "maxDepth" : 80 // The maximum depth that this pocket will occur
    }
}
```

###Defaults

```
{
    "OreGenDefinition" :  {},
    "DepthPocketOreGen" :
    {    
        "block" : Null,
        "minDepth" : 0,
        "maxDepth" : 0,
        "frequency" : 1,
        "frequencyRange" : 0,
        "radius" : 2,
        "radiusRange" : 1,
        "thickness" : 6,
        "thicknessRange" : 3,
        "angle" : 1,
        "angleRange" : 1,
        "multiplier" : 1,
        "multiplierRange" : 0,
        "density" : 0.7,
        "densityRange" : 0.2,
        "densityCutoff": 2,
        "noiseLevel" : 0.2,
        "noiseLevelRange" : 0.2,
        "noiseCutoff" : 0,
        "noiseCutoffRange" : 0,
        "densityCutoff" : 2
    }
}
```

##Veins Ore Generation
The Veins ore generation creates a round central "motherlode" with branches radiating in all directions. Branches may twist and turn, and also may fork to create a more tree-like structure.

###Minimal usage on a prefab

```
{
    "OreGenDefinition" :  {},
    "VeinsOreGen" :
    {
        "block" : "Core:IronOre", //  What block will be placed
        "frequency" : 1 // How often this ore gen will happen in 10 vertical blocks in the region
    }
}
```

###Defaults
```
{
    "OreGenDefinition" :  {},
    "VeinsOreGen" :
    {
        "block" : Null,   
        "frequency" : 1,
        "frequencyRange" : 0,
        "motherLodeRadius" : 2,
        "motherLodeRadiusRange" : 1,
        "motherlodeRangeLimit" : 32,
        "motherlodeRangeLimitRange" : 32,
        "branchFrequency" : 4,
        "branchFrequencyRange" : 1,
        "branchInclination" : 0,
        "branchInclinationRange" : 0.1,
        "branchLength" : 40,
        "branchLengthRange" : 10,
        "branchHeightLimit" : 100,
        "branchHeightLimitRange" : 0,
        "density" : 1,
        "densityRange" : 0,
        "segmentForkFrequency" : 0.3,
        "segmentForkFrequencyRange" : 0.1,
        "segmentForkLengthMultiplier" : 0.25,
        "segmentForkLengthMultiplierRange" : 0,
        "segmentLength" : 5,
        "segmentLengthRange" : 0,
        "segmentAngle" : 0.5,
        "segmentAngleRange" : 0.5,
        "segmentRadius" : 4,
        "segmentRadiusRange" : 1,
        "blockRadiusMultiplier" : 1,
        "blockRadiusMultiplierRange" : 0,
        "densityCutoff" : 2
    }
}
```


##Depth Veins Ore Generation
The Veins ore generation creates a round central "motherlode" with branches radiating in all directions. Branches may twist and turn, and also may fork to create a more tree-like structure.

###Minimal usage on a prefab

```
{
    "OreGenDefinition" :  {},
    "DepthVeinsOreGen" :
    {
        "block" : "Core:IronOre", //  What block will be placed
        "frequency" : 1, // How often this ore gen will happen in 10 vertical blocks in the region
        "minDepth" : 4, // The minimum depth that this pocket will occur
        "maxDepth" : 80 // The maximum depth that this pocket will occur
    }
}
```

###Defaults
```
{
    "OreGenDefinition" :  {},
    "DepthVeinsOreGen" :
    {
        "block" : Null,
        "minDepth" : 0,
        "maxDepth" : 0,  
        "frequency" : 1,
        "frequencyRange" : 0,
        "motherLodeRadius" : 2,
        "motherLodeRadiusRange" : 1,
        "motherlodeRangeLimit" : 32,
        "motherlodeRangeLimitRange" : 32,
        "branchFrequency" : 4,
        "branchFrequencyRange" : 1,
        "branchInclination" : 0,
        "branchInclinationRange" : 0.1,
        "branchLength" : 40,
        "branchLengthRange" : 10,
        "branchHeightLimit" : 100,
        "branchHeightLimitRange" : 0,
        "density" : 1,
        "densityRange" : 0,
        "segmentForkFrequency" : 0.3,
        "segmentForkFrequencyRange" : 0.1,
        "segmentForkLengthMultiplier" : 0.25,
        "segmentForkLengthMultiplierRange" : 0,
        "segmentLength" : 5,
        "segmentLengthRange" : 0,
        "segmentAngle" : 0.5,
        "segmentAngleRange" : 0.5,
        "segmentRadius" : 4,
        "segmentRadiusRange" : 1,
        "blockRadiusMultiplier" : 1,
        "blockRadiusMultiplierRange" : 0,
        "densityCutoff" : 2
    }
}
```
