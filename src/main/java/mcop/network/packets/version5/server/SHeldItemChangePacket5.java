package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SHeldItemChangePacket5 extends PacketBase<IClientPacketHandler> {

    byte slot;

    //Sent to change the player's slot selection

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeByte(slot);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        slot = packetData.readByte();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO handle
    }
}
