package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class STabCompletePacket5 extends PacketBase<IClientPacketHandler> {

    int count; //Number of following strings
    String match; //One eligible command, note that each command is sent separately instead of in a single string, hence the need for Count

    //The server responds with a list of auto-completions of the last word sent to it. In the case of regular chat, this is a player username. Command names and parameters are also supported.


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeVarInt(count);
        packetData.writeUTF8(match);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        count = packetData.readVarInt();
        match = packetData.readUTF8();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
