package mcop.net.handshake;

import dev.hilligans.ourcraft.network.Protocol;
import dev.hilligans.ourcraft.network.engine.ServerNetworkEntity;
import mcop.net.ClientToServerPacketType;
import dev.hilligans.ourcraft.util.IByteArray;

public class CHandshake extends ClientToServerPacketType {

    public static final CHandshake instance = new CHandshake();


    @Override
    public void decode(ServerNetworkEntity serverNetworkEntity, IByteArray iByteArray) {
        int protocolVersion = iByteArray.readVarInt();
        String address = iByteArray.readUTF8();
        int port = iByteArray.readUShort();
        int intention = iByteArray.readVarInt();

        System.out.println("Client " + address + " width version " + protocolVersion + " intention " + intention);

        if(intention == 1) {
            serverNetworkEntity.switchProtocol(serverNetworkEntity.getGameInstance().getExcept("mcop:status-client-bound", Protocol.class), serverNetworkEntity.getGameInstance().getExcept("mcop:status-server-bound", Protocol.class));
        } else if(intention == 2) {
            serverNetworkEntity.switchProtocol(serverNetworkEntity.getGameInstance().getExcept("mcop:login-client-bound", Protocol.class), serverNetworkEntity.getGameInstance().getExcept("mcop:login-server-bound", Protocol.class));
        }
    }
}
