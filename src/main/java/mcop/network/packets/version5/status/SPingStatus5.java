package mcop.network.packets.version5.status;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SPingStatus5 extends PacketBase<IClientPacketHandler> {

    public long time;

    public SPingStatus5(long time) {
        this.time = time;
    }

    public SPingStatus5() {}

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeLong(time);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        time = packetData.readLong();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        System.out.println("TIME:" + time);
        //TODO handle
    }
}
