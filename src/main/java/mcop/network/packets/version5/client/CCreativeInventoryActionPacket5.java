package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;
import mcop.network.packets.version5.ItemStack;

public class CCreativeInventoryActionPacket5 extends PacketBase<IServerPacketHandler> {

    /*While the user is in the standard inventory (i.e., not a crafting bench) on a creative-mode server then the server will send this packet:

    If an item is dropped into the quick bar
    If an item is picked up from the quick bar (item id is -1)

     */

    short slot;
    ItemStack clickedSlot;


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeShort(slot);
        clickedSlot.write(packetData);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        slot = packetData.readShort();
        clickedSlot = new ItemStack(packetData);
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
