package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SDestroyEntitiesPacket5 extends PacketBase<IClientPacketHandler> {

    byte count;
    int[] values;

    //Sent by the server when an list of Entities is to be destroyed on the client.

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeByte(count);
        for(int x = 0; x < values.length; x++) {
            packetData.writeInt(values[x]);
        }
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        count = packetData.readByte();
        values = packetData.readInts(count);
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        for(int x = 0; x < count; x++) {
            iClientPacketHandler.getClient().getWorld().removeEntity(values[x], 0);
        }
    }
}
