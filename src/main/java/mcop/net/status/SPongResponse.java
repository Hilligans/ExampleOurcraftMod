package mcop.net.status;

import dev.hilligans.ourcraft.network.engine.ClientNetworkEntity;
import dev.hilligans.ourcraft.network.engine.NetworkEntity;
import mcop.net.ServerToClientPacketType;
import dev.hilligans.ourcraft.util.IByteArray;

public class SPongResponse extends ServerToClientPacketType {

    public static final SPongResponse instance = new SPongResponse();

    public static void send(NetworkEntity entity, long id) {
        entity.sendPacket(instance.encode(entity, id));
    }

    public IByteArray encode(NetworkEntity entity, long id) {
        IByteArray array = getWriteArray(entity);
        array.writeLong(id);
        return array;
    }

    @Override
    public void decode(ClientNetworkEntity clientNetworkEntity, IByteArray iByteArray) {
        long id = iByteArray.readLong();
    }
}
