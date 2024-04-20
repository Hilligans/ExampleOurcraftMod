package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CCloseWindowPacket5 extends PacketBase<IServerPacketHandler> {

    byte windowID; //This is the id of the window that was closed. 0 for inventory.


    //This packet is sent by the client when closing a window.
    //
    //Note, notchian clients send a close window message with window id 0 to close their
    // inventory even though there is never an Open Window message for inventory.

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeByte(windowID);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        windowID = packetData.readByte();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
