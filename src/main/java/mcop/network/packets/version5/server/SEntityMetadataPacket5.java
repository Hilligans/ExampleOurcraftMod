package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SEntityMetadataPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
//todo rest

    @Override
    public void encode(IPacketByteArray packetData) {

    }

    @Override
    public void decode(IPacketByteArray packetData) {

    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
