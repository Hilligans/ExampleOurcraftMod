package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SCloseWindowPacket5 extends PacketBase<IClientPacketHandler> {

    int windowID; //This is the id of the window that was closed. 0 for inventory.

    /*
    This packet is sent from the server to the client when a window is forcibly closed,
    such as when a chest is destroyed while it's open.

    Note, notchian clients send a close window message with window id 0
     to close their inventory even though there is never an Open Window message for inventory.

     */


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUByte(windowID);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        windowID = packetData.readUByte();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
