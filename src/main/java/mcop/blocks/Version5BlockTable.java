package mcop.blocks;

import dev.hilligans.ourcraft.GameInstance;
import dev.hilligans.ourcraft.block.Block;
import dev.hilligans.ourcraft.block.Blocks;
import dev.hilligans.ourcraft.block.blockstate.IBlockState;
import dev.hilligans.ourcraft.data.other.BlockProperties;
import dev.hilligans.ourcraft.mod.handler.content.ModContainer;
import dev.hilligans.ourcraft.resource.ResourceLocation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Version5BlockTable implements IBlockConversionTable {

    String path = "versions/5/blocks.json";
    GameInstance gameInstance;

    public HashMap<Integer, Block> mapping = new HashMap<>();
    public int[] mappings;
    public ArrayList<Block> blocks = new ArrayList<>();

    public Version5BlockTable(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }

    public void loadBlocks(ModContainer modContainer) {
        JSONObject val = (JSONObject) gameInstance.RESOURCE_LOADER.getResource(new ResourceLocation(path, "mcop"));
        JSONArray arr = val.getJSONArray("blocks");

        for(int x = 0; x < arr.length(); x++) {
            JSONObject object = arr.getJSONObject(x);
            String name = object.getString("name");
            int id = object.getInt("id");

            if(object.has("variations")) {
                JSONArray array = object.getJSONArray("variations");
                for(int y = 0; y < array.length(); y++) {
                    JSONObject obj1 = array.getJSONObject(y);
                    //System.err.println(obj1);
                    addBlock(new Block(obj1.getString("displayName").toLowerCase().replace(" ", "_").trim(), new BlockProperties()), id, obj1.getInt("metadata"));
                }
            } else {
                addBlock(new Block(name, new BlockProperties()), id, 0);
            }
        }
        for(Block block : blocks) {
           // System.out.println(block);
            modContainer.registerBlock(block);
        }
    }

    public void addBlock(Block block, int id, int variation) {
       // System.out.println(block.getName());
       // System.out.println(variation);
        blocks.add(block);
        mapping.put(id << 4 | variation, block);
    }

    public void buildMappings() {
        mappings = new int[blocks.size()];
        for(Map.Entry<Integer, Block> entry : mapping.entrySet()) {
         //   System.out.println(entry.getValue());
            mappings[entry.getValue().getDefaultState().getBlockID()] = entry.getKey();
        }
    }

    @Override
    public IBlockState getBlockState(int id) {
        return mapping.get(id).getDefaultState();
    }

    @Override
    public int getBlockID(IBlockState blockState) {
        //System.out.println(blockState.getBlock().getName() + ":" + blockState.getBlockID());
        if(blockState.getBlock() == Blocks.AIR) {
            return 0;
        }
        try {
            return mappings[blockState.getBlockID()];
        } catch (Exception e) {
            System.err.println(blockState);
            return 0;
        }
    }
}
