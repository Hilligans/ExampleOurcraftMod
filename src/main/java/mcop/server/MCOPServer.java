package mcop.server;

import dev.hilligans.ourcraft.GameInstance;
import dev.hilligans.ourcraft.data.other.server.ServerPlayerData;
import dev.hilligans.ourcraft.entity.living.entities.PlayerEntity;
import dev.hilligans.ourcraft.network.PacketBase;
import dev.hilligans.ourcraft.network.ServerNetworkHandler;
import dev.hilligans.ourcraft.server.IServer;
import dev.hilligans.ourcraft.world.newworldsystem.IServerWorld;

public class MCOPServer implements IServer {

    public GameInstance gameInstance;
    public int versionNumber = 5;
    public boolean isOnline = false;

    public MCOPServer(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }


    public int getVersion() {
        return versionNumber;
    }


    @Override
    public void addWorld(IServerWorld world) {

    }

    @Override
    public IServerWorld getWorld(ServerPlayerData serverPlayerData) {
        return null;
    }

    @Override
    public long getTime() {
        return 0;
    }

    @Override
    public void setTime(long time) {

    }

    @Override
    public Object executeCommand(String command) {
        return null;
    }

    @Override
    public ServerNetworkHandler getServerNetworkHandler() {
        return null;
    }

    @Override
    public void sendPacket(PacketBase packetBase) {

    }

    @Override
    public void sendPacket(PacketBase packetBase, PlayerEntity playerEntity) {

    }

    @Override
    public GameInstance getGameInstance() {
        return gameInstance;
    }

    @Override
    public void stop() {

    }
}
