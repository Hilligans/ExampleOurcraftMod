package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;
import mcop.network.packets.version5.ItemStack;

public class SSetSlotPacket5 extends PacketBase<IClientPacketHandler> {

    //Sent by the server when an item in a slot (in a window) is added/removed.

    int windowID;
    short slot;
    ItemStack itemStack;


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUByte(windowID);
        packetData.writeShort(slot);
        itemStack.write(packetData);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        windowID = packetData.readUByte();
        slot = packetData.readShort();
        new ItemStack(packetData);
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
