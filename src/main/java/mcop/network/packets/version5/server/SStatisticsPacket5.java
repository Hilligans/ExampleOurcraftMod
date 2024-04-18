package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SStatisticsPacket5 extends PacketBase<IClientPacketHandler> {

    int count;


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeVarInt(0);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        count = packetData.readVarInt();
        for(int x = 0; x < count; x++) {
            packetData.readUTF8(); //Statistic's name
            //https://gist.github.com/thinkofdeath/a1842c21a0cf2e1fb5e0
            packetData.readVarInt(); //value
        }
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
