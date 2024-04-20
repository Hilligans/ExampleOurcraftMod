package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CConfirmTransactionPacket5 extends PacketBase<IServerPacketHandler> {

    byte windowID; //The id of the window that the action occurred in.
    short actionNumber; //Every action that is to be accepted has a unique number. This field corresponds to that number.
    boolean accepted;//Whether the action was accepted.

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeByte(windowID);
        packetData.writeShort(actionNumber);
        packetData.writeBoolean(accepted);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        windowID = packetData.readByte();
        actionNumber = packetData.readShort();
        accepted = packetData.readBoolean();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {
        //todo handle
    }
}
