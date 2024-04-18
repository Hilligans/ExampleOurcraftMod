package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;
import mcop.network.packets.version5.ItemStack;

public class SWindowItemsPacket5 extends PacketBase<IClientPacketHandler> {

    //Sent by the server when an item in a slot (in a window) is added/removed. This includes the main inventory, equipped armour and crafting slots.
    //https://wiki.vg/File:Inventory-slots.png

    int windowID;
    short count;
    ItemStack[] slots;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUByte(windowID);
        packetData.writeShort(count);
        for(ItemStack itemStack : slots) {
            itemStack.write(packetData);
        }
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        windowID = packetData.readUByte();
        count = packetData.readShort();
        slots = new ItemStack[count];
        for(int x = 0; x < count; x++) {
            slots[x] = new ItemStack(packetData);
        }
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
