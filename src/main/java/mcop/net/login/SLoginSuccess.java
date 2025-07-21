package mcop.net.login;

import dev.hilligans.ourcraft.network.engine.ClientNetworkEntity;
import dev.hilligans.ourcraft.network.engine.NetworkEntity;
import mcop.net.ServerToClientPacketType;
import dev.hilligans.ourcraft.util.IByteArray;

import java.util.UUID;

public class SLoginSuccess extends ServerToClientPacketType {

    public static SLoginSuccess instance = new SLoginSuccess();

    public static void send(NetworkEntity entity, UUID uuid, String username) {
        entity.sendPacket(instance.encode(entity, uuid, username));
    }

    public IByteArray encode(NetworkEntity networkEntity, UUID uuid, String username) {
        IByteArray array = getWriteArray(networkEntity);
        array.writeLong(uuid.getMostSignificantBits());
        array.writeLong(uuid.getLeastSignificantBits());
        array.writeUTF8(username);
        array.writeVarInt(0);
        return array;
    }

    @Override
    public void decode(ClientNetworkEntity clientNetworkEntity, IByteArray iByteArray) {

    }
}
