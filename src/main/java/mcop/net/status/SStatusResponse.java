package mcop.net.status;

import dev.hilligans.ourcraft.network.engine.ClientNetworkEntity;
import dev.hilligans.ourcraft.network.engine.NetworkEntity;
import dev.hilligans.ourcraft.util.IByteArray;
import mcop.net.ServerToClientPacketType;

public class SStatusResponse extends ServerToClientPacketType {

    public static final SStatusResponse instance = new SStatusResponse();

    public static void send(NetworkEntity networkEntity, String message) {
        networkEntity.sendPacket(instance.encode(networkEntity, message));
    }

    public IByteArray encode(NetworkEntity networkEntity, String message) {
        IByteArray array = getWriteArray(networkEntity);
        array.writeUTF8(message);
        return array;
    }

    @Override
    public void decode(ClientNetworkEntity clientNetworkEntity, IByteArray iByteArray) {
        String message = iByteArray.readUTF8();
    }
}
