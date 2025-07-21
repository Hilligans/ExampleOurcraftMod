package mcop.net.login;

import dev.hilligans.ourcraft.network.engine.ClientNetworkEntity;
import mcop.net.ServerToClientPacketType;
import dev.hilligans.ourcraft.util.IByteArray;

public class SEncryptionRequest extends ServerToClientPacketType {

    public static SEncryptionRequest instance = new SEncryptionRequest();



    @Override
    public void decode(ClientNetworkEntity clientNetworkEntity, IByteArray iByteArray) {

    }
}
