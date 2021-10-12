package Example;

import dev.Hilligans.Block.Block;
import dev.Hilligans.Data.Other.BlockProperties;
import dev.Hilligans.ModHandler.Content.ModContent;
import dev.Hilligans.ModHandler.Events.Client.GLInitEvent;
import dev.Hilligans.ModHandler.Mod;
import dev.Hilligans.Ourcraft;

//this is what you use to assign the main class of your mod
@Mod(modID = "test_mod")
public class Main {

    //registering a new block is as simple as this, the textureSource method takes in a modID indicating what mod to grab the texture from.
    //if left blank it will first try to grab from the ourcraft textures then from all other mods and then default to no texture
    public static final Block CUSTOM_BLOCK = new Block("custom_block",new BlockProperties().withTexture("texture.png").textureSource("test_mod"));

    //when the mod gets loaded it will make a new instance of main

    public Main(ModContent modContent) {
        System.out.println("Example mod loaded");
        //the first way to register an event on the event bus
        modContent.gameInstance.EVENT_BUS.register(GLInitEvent.class,this::event);
        //the second way to register an event on the event bus
        modContent.gameInstance.EVENT_BUS.register(new SecondClass());
    }

    public void event(GLInitEvent event) {
        System.out.println("OpenGL Started");
    }
}
