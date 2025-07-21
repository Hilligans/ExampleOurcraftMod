package mcop.net.login;

import dev.hilligans.ourcraft.network.engine.ServerNetworkEntity;
import mcop.net.ClientToServerPacketType;
import dev.hilligans.ourcraft.util.IByteArray;

public class CLoginAcknowledged extends ClientToServerPacketType {

    public static final CLoginAcknowledged instance = new CLoginAcknowledged();

    @Override
    public void decode(ServerNetworkEntity serverNetworkEntity, IByteArray iByteArray) {
        //switch formats
        System.err.println("Switch to configuration");
    }
}
