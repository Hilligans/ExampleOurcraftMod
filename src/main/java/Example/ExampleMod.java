package Example;

import dev.hilligans.ourcraft.block.Block;
import dev.hilligans.ourcraft.data.other.BlockProperties;
import dev.hilligans.ourcraft.mod.handler.ModClass;
import dev.hilligans.ourcraft.mod.handler.content.ModContainer;

public class ExampleMod extends ModClass {
    @Override
    public String getModID() {
        return "example_mod";
    }

    //creating a new block is as simple as this, the textureSource method takes in a modID indicating what mod to grab the texture from.
    //if left blank it will first try to grab from the ourcraft textures then from all other mods and then default to no texture
    public static final Block CUSTOM_BLOCK = new Block("custom_block",new BlockProperties().withTexture("texture.png").textureSource("example_mod"));


    @Override
    public void registerContent(ModContainer container) {
        System.out.println("Hello from my example mod!");

        //and once the block has been created we can register it
        container.registerBlock(CUSTOM_BLOCK);
    }
}
