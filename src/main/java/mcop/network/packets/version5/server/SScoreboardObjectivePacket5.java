package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SScoreboardObjectivePacket5 extends PacketBase<IClientPacketHandler> {

    String objectiveName; //An unique name for the objective
    String objectiveValue; //The text to be displayed for the score.
    byte createOrRemove; //0 to create the scoreboard. 1 to remove the scoreboard. 2 to update the display text.


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(objectiveName);
        packetData.writeUTF8(objectiveValue);
        packetData.writeByte(createOrRemove);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        objectiveName = packetData.readString();
        objectiveValue = packetData.readString();
        createOrRemove = packetData.readByte();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
