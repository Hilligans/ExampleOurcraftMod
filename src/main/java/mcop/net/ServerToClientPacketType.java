package mcop.net;

import dev.hilligans.ourcraft.network.engine.NetworkEntity;
import dev.hilligans.ourcraft.util.IByteArray;

public abstract class ServerToClientPacketType extends dev.hilligans.ourcraft.network.packet.ServerToClientPacketType {
    public IByteArray getWriteArray(NetworkEntity entity) {
        IByteArray array = entity.allocByteArray();
        array.writeVarInt(this.getPacketID(entity.getSendProtocol()));
        return array;
    }
}
