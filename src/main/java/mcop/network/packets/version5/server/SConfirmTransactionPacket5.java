package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SConfirmTransactionPacket5 extends PacketBase<IClientPacketHandler> {

    //A packet from the server indicating whether a request from the client was accepted, or whether there was a conflict (due to lag).
    //This packet is also sent from the client to the server in response to a server transaction rejection packet.

    int windowID;
    short actionSlot;
    boolean accepted;


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUByte(windowID);
        packetData.writeShort(actionSlot);
        packetData.writeBoolean(accepted);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        windowID = packetData.readUByte();
        actionSlot = packetData.readShort();
        accepted = packetData.readBoolean();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
