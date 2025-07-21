package mcop.net.status;

import dev.hilligans.ourcraft.network.engine.NetworkEntity;
import dev.hilligans.ourcraft.network.engine.ServerNetworkEntity;
import mcop.net.ClientToServerPacketType;
import dev.hilligans.ourcraft.util.IByteArray;

public class CPingRequest extends ClientToServerPacketType {

    public static final CPingRequest instance = new CPingRequest();

    public static void send(NetworkEntity entity) {
        send(entity, System.currentTimeMillis());
    }

    public static void send(NetworkEntity entity, long id) {
        entity.sendPacket(instance.encode(entity, id));
    }

    public IByteArray encode(NetworkEntity entity, long id) {
        IByteArray array = getWriteArray(entity);
        array.writeLong(id);
        return array;
    }

    @Override
    public void decode(ServerNetworkEntity serverNetworkEntity, IByteArray iByteArray) {
        System.err.println("Ping request");
        SPongResponse.send(serverNetworkEntity, iByteArray.readLong());
    }
}
