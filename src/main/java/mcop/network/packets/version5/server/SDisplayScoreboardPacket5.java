package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SDisplayScoreboardPacket5 extends PacketBase<IClientPacketHandler> {

    byte position; //The position of the scoreboard. 0 = list, 1 = sidebar, 2 = belowName.
    String scoreName; //The unique name for the scoreboard to be displayed.

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeByte(position);
        packetData.writeUTF8(scoreName);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        position = packetData.readByte();
        scoreName = packetData.readUTF8();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
