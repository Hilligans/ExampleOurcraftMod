package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SChatMessagePacket5 extends PacketBase<IClientPacketHandler> {

    String message;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(message);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        message = packetData.readUTF8();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO handle
    }
}
