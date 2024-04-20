package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CTabCompletePacket5 extends PacketBase<IServerPacketHandler> {

    //Sent when the user presses [tab] while writing text.
    //The payload contains all text behind the cursor.

    String text;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(text);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        text = packetData.readUTF8();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
