###OreGeneration
=============

A plugin system for adding randomized ore generation to the world

##DepthPocketOreGen
Add a prefab to your mod that looks something like this for basic functionality:

'''
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
'''
Frequency is how many times it would happen in the range of depths specified.  Depth is how far below the surface the ore will generate.  DepthRange is how far above and below generation will happen.
