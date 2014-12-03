#OreGeneration
=============

A plugin system for adding randomized ore generation to the world

##DepthPocketOreGen
###Minimal usage on a prefab

```
{
    "OreGenDefinition" :  {},
    "DepthPocketOreGen" :
    {
        "block" : "Core:Water",
        "frequency" : 1,
        "depth" : 8,
        "depthRange" : 15
    }
}
```

Frequency is how many times it would happen in the range of depths specified.  Depth is how far below the surface the ore will generate.  DepthRange is how far above and below generation will happen.

###Defaults

```
{
    "OreGenDefinition" :  {},
    "DepthPocketOreGen" :
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
        "noiseLevel" : 0.2,
        "noiseLevelRange" : 0.2,
        "noiseCutoff" : 0,
        "noiseCutoffRange" : 0
    }
}
```