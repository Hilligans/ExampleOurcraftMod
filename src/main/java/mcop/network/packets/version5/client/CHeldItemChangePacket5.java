package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CHeldItemChangePacket5 extends PacketBase<IServerPacketHandler> {

    short slot; //The slot which the player has selected (0-8)

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeShort(slot);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        slot = packetData.readShort();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
