package mcop.network.packets.version5.status;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CPingStatus5 extends PacketBase<IServerPacketHandler> {

    long time;


    public CPingStatus5() {}

    public CPingStatus5(long time) {
        this.time = time;
    }

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeLong(time);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        time = packetData.readLong();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {
        iServerPacketHandler.sendPacket(new SPingStatus5(time), ctx);
    }
}
