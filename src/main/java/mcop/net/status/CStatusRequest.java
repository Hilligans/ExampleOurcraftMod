package mcop.net.status;

import dev.hilligans.ourcraft.network.engine.NetworkEntity;
import dev.hilligans.ourcraft.network.engine.ServerNetworkEntity;
import mcop.net.ClientToServerPacketType;
import dev.hilligans.ourcraft.util.IByteArray;

public class CStatusRequest extends ClientToServerPacketType {

    public static final CStatusRequest instance = new CStatusRequest();

    public static void send(NetworkEntity entity) {
        entity.sendPacket(instance.encode(entity));
    }

    public IByteArray encode(NetworkEntity entity) {
        return getWriteArray(entity);
    }

    public static final String status = """
                {
                "version": {
                    "name": "1.21.8",
                    "protocol": 772
                },
                "players": {
                    "max": 100,
                    "online": 5,
                    "sample": [
                        {
                            "name": "thinkofdeath",
                            "id": "4566e69f-c907-48ee-8d71-d7ba5aa00d20"
                        }
                    ]
                },
                "description": {
                    "text": "Hello, world!"
                },
                "favicon": "data:image/png;base64,<data>",
                "enforcesSecureChat": false
            }""";

    @Override
    public void decode(ServerNetworkEntity serverNetworkEntity, IByteArray iByteArray) {
        SStatusResponse.send(serverNetworkEntity, status);
    }
}
