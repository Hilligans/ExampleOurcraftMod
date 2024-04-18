package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SBlockChangePacket5 extends PacketBase<IClientPacketHandler> {

    int x;
    int y;
    int z;
    int blockID;
    int blockMeta;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(x);
        packetData.writeUByte(y);
        packetData.writeInt(z);
        packetData.writeVarInt(blockID);
        packetData.writeUByte(blockMeta);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        x = packetData.readInt();
        y = packetData.readUByte();
        z = packetData.readInt();
        blockID = packetData.readVarInt();
        blockMeta = packetData.readUByte();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
