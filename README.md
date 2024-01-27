# GameEngine
## A simple game engine coded in Java.

## Features
Each of these features is explained in this README.
- Engine
- GameObject
- Module

These three components are important for using this game engine. If you want to learn more about the structure of the game engine, refer to the Java documentation [here](javaDoc) (Open the index.html file in your browser).

### Engine
The `Engine` class is a singleton that creates and handles the game. It maintains a list of `GameObjects` to which you can add your own objects for your game. The engine runs a game loop, and the duration between each loop iteration is determined by the frames per second (fps), which can be set using the `setFps()` method.

### GameObject
The `GameObject` class represents an object in the game scene. You can add it to the `Engine`. It provides useful methods for positioning, resizing, scaling, setting textures, adding child objects, and most importantly, adding modules. The `GameObject` serves as a building block for your game, upon which everything else is constructed.

### Module
The `Module` class adds additional functionality to the `GameObject`. It overrides the `update()` method, which is called during each iteration of the game loop. Modules can modify the behavior of the `GameObject`, such as movement, collision, gravity, and more.
