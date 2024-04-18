package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SUpdateScorePacket5 extends PacketBase<IClientPacketHandler> {

    //This is sent to the client when it should update a scoreboard item.

    String itemName; //An unique name to be displayed in the list.
    byte updateOrRemove; //0 to create/update an item. 1 to remove an item.
    String scoreName; //The unique name for the scoreboard to be updated. Only sent when Update/Remove does not equal 1.
    int value; //The score to be displayed next to the entry. Only sent when Update/Remove does not equal 1.

    //The final String and Int are only sent if the Update/Remove Byte does not equal 1

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(itemName);
        packetData.writeByte(updateOrRemove);
        packetData.writeUTF8(scoreName);
        packetData.writeInt(value);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        itemName = packetData.readUTF8();
        updateOrRemove = packetData.readByte();
        scoreName = packetData.readUTF8();
        value = packetData.readInt();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
