package mcop;

import dev.hilligans.ourcraft.GameInstance;
import dev.hilligans.ourcraft.block.Blocks;
import dev.hilligans.ourcraft.data.other.server.ServerPlayerData;
import dev.hilligans.ourcraft.network.PacketBase;
import dev.hilligans.ourcraft.network.PacketByteArray;
import dev.hilligans.ourcraft.network.ServerNetworkHandler;
import dev.hilligans.ourcraft.network.debug.PacketTraceByteArray;
import dev.hilligans.ourcraft.tag.CompoundNBTTag;
import dev.hilligans.ourcraft.world.newworldsystem.IServerWorld;
import dev.hilligans.ourcraft.world.newworldsystem.IWorld;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import mcop.blocks.IBlockConversionTable;
import mcop.entities.PlayerEntity;
import mcop.network.PacketInterface;
import mcop.network.packets.version5.server.SBlockChangePacket5;
import mcop.network.packets.version5.server.SPlayerPositionAndLookPacket5;
import org.json.JSONObject;

public class MCOPServerPlayerData extends ServerPlayerData {

    public String worldName;
    public int gamemode = 0; //0 = survival, 1 = creative

    public int digBlockX;
    public int digBlockY;
    public int digBlockZ;
    public IWorld digWorld;

    public long digStartTime;
    public IBlockConversionTable table;
    public PacketInterface packetInterface;
    public int heldItemSlot;

    public MCOPServerPlayerData(GameInstance gameInstance, String name) {
        super(gameInstance, name);
    }

    @Override
    public void handleDisconnect() {
    }

    @Override
    public void close() {
    }

    public void startDigging(int x, int y, int z, IServerWorld world) {
        if(gamemode == 1) {
            world.setBlockState(x, y, z, Blocks.AIR.getDefaultState());
           // world.getServer().sendPacketToAllVisible(packetInterface.getBlockChangePacket(x, y, z, world, Blocks.AIR.getDefaultState()), x, y, z, world);
        } else {
            this.digBlockX = x;
            this.digBlockY = y;
            this.digBlockZ = z;
            this.digWorld = world;
            this.digStartTime = world.getServer().getTime();
        }
    }

    public void cancelDigging() {
        digWorld = null;
    }

    public void finishDigging(int x, int y, int z, IServerWorld world) {
        if(world == digWorld && x == digBlockX && y == digBlockY && z == digBlockZ) {
            //todo check dig time
            world.setBlockState(digBlockX, digBlockY, digBlockZ, Blocks.AIR.getDefaultState());
           // world.getServer().sendPacketToAllVisible(packetInterface.getBlockChangePacket(x, y, z, world, Blocks.AIR.getDefaultState()), x, y, z, world);
        } else {
            digWorld = null;
        }
    }

    public void setBlock(int x, int y, int z, IServerWorld world) {}

    public void setPosition(float x, float y, float z) {
        entity.setPosition(x, y, z);
    }

    public void setRot(float pitch, float yaw) {
        entity.setRot(pitch, yaw, 0);
    }

    public void changeHeldItemSlot(int slot) {
        this.heldItemSlot = slot;
    }

    public void save(CompoundNBTTag tag) {
        tag.putString("name", playerName);
        tag.putString("worldName", worldName);

        tag.putFloat("pitch", entity.getPitch());
        tag.putFloat("yaw", entity.getYaw());

        tag.putFloat("velX", entity.getVelX());
        tag.putFloat("velY", entity.getVelY());
        tag.putFloat("velZ", entity.getVelZ());

        tag.putDouble("x", entity.getX());
        tag.putDouble("y", entity.getY());
        tag.putDouble("z", entity.getZ());
    }

    public static MCOPServerPlayerData load(CompoundNBTTag tag) {
        MCOPServerPlayerData data = new MCOPServerPlayerData(null, tag.getString("name"));
        data.worldName = tag.getString("worldName");

        PlayerEntity entity = new PlayerEntity(data.playerName);
        entity.setRot(tag.getFloat("pitch"), tag.getFloat("yaw"), 0);
        entity.setVel(tag.getFloat("velX"), tag.getFloat("velY"), tag.getFloat("velZ"));
        entity.setPosition(tag.getDouble("x"), tag.getDouble("y"), tag.getDouble("z"));

        return data;
    }

    public ChannelFuture sendPacket(PacketBase<?> packetBase, ChannelHandlerContext ctx) {
        return getServerNetworkHandler().sendPacket(packetBase, ctx);
    }
}
