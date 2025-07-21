package mcop.net.login;

import dev.hilligans.ourcraft.network.engine.ClientNetworkEntity;
import dev.hilligans.ourcraft.network.engine.NetworkEntity;
import mcop.net.ServerToClientPacketType;
import dev.hilligans.ourcraft.util.IByteArray;

public class SDisconnect extends ServerToClientPacketType {

    public static SDisconnect instance = new SDisconnect();

    public static void send(NetworkEntity entity, String text) {
        entity.sendPacket(instance.encode(entity, text));
    }

    public IByteArray encode(NetworkEntity entity, String text) {
        IByteArray array = getWriteArray(entity);
        array.writeUTF8(text);
        return array;
    }

    @Override
    public void decode(ClientNetworkEntity clientNetworkEntity, IByteArray iByteArray) {
        String message = iByteArray.readUTF8();
    }
}
