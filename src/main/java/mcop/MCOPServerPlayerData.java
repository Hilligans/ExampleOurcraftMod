package mcop;

import dev.hilligans.ourcraft.GameInstance;
import dev.hilligans.ourcraft.data.other.server.ServerPlayerData;
import dev.hilligans.ourcraft.network.PacketBase;
import dev.hilligans.ourcraft.network.PacketByteArray;
import dev.hilligans.ourcraft.network.ServerNetworkHandler;
import dev.hilligans.ourcraft.network.debug.PacketTraceByteArray;
import dev.hilligans.ourcraft.tag.CompoundNBTTag;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import mcop.entities.PlayerEntity;
import org.json.JSONObject;

public class MCOPServerPlayerData extends ServerPlayerData {

    public String worldName;
    public int gamemode;


    public MCOPServerPlayerData(GameInstance gameInstance, String name) {
        super(gameInstance, name);
    }

    @Override
    public void handleDisconnect() {
    }

    @Override
    public void close() {
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
