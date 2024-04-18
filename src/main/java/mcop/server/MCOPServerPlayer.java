package mcop.server;

import dev.hilligans.ourcraft.data.other.server.ServerPlayerData;
import dev.hilligans.ourcraft.entity.living.entities.PlayerEntity;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.Network;
import dev.hilligans.ourcraft.network.ServerNetworkHandler;
import dev.hilligans.ourcraft.server.IServer;
import dev.hilligans.ourcraft.world.newworldsystem.IServerWorld;

public class MCOPServerPlayer implements IServerPacketHandler {

    public MCOPServer server;
    public IServerWorld world;

    public MCOPServerPlayer(MCOPServer server) {
        this.server = server;
    }

    public void setWorld(IServerWorld world) {
        this.world = world;
    }

    @Override
    public IServerWorld getWorld() {
        return world;
    }

    @Override
    public ServerPlayerData getServerPlayerData() {
        return null;
    }

    @Override
    public PlayerEntity getPlayerEntity() {
        return null;
    }

    @Override
    public IServer getServer() {
        return server;
    }

    @Override
    public ServerNetworkHandler getServerNetworkHandler() {
        return null;
    }

    @Override
    public void disconnect(String s) {

    }

    @Override
    public Network getNetwork() {
        return null;
    }
}
