package mcop.net.login;

import dev.hilligans.ourcraft.network.engine.ServerNetworkEntity;
import mcop.net.ClientToServerPacketType;
import dev.hilligans.ourcraft.util.IByteArray;

import java.util.UUID;

public class CLoginStart extends ClientToServerPacketType {

    public static final CLoginStart instance = new CLoginStart();

    @Override
    public void decode(ServerNetworkEntity serverNetworkEntity, IByteArray iByteArray) {
        String username = iByteArray.readUTF8();
        UUID uuid = new UUID(iByteArray.readLong(), iByteArray.readLong());

        System.out.println("Starting login: " + username);
        SLoginSuccess.send(serverNetworkEntity, uuid, username);
    }
}
