package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;
import mcop.network.packets.version5.ItemStack;

public class CPlayerBlockPlacementPacket5 extends PacketBase<IServerPacketHandler> {

    int x;
    int y;
    int z;
    byte direction;
    ItemStack stack;
    byte cursorX;
    byte cursorY;
    byte cursorZ;


    /*
    In normal operation (ie placing a block), this packet is sent once, with the values set normally.

    This packet has a special case where X, Y, Z, and Direction are all -1. (Note that Y is unsigned so set to 255.) This special packet indicates that the currently held item for the player should have its state updated such as eating food, shooting bows, using buckets, etc.

    In a Notchian Beta client, the block or item ID corresponds to whatever the client is currently holding, and the client sends one of these packets any time a right-click is issued on a surface, so no assumptions can be made about the safety of the ID. However, with the implementation of server-side inventory, a Notchian server seems to ignore the item ID, instead operating on server-side inventory information and holding selection. The client has been observed (1.2.5 and 1.3.2) to send both real item IDs and -1 in a single session.

    Special note on using buckets: When using buckets, the Notchian client might send two packets: first a normal and then a special case. The first normal packet is sent when you're looking at a block (e.g. the water you want to scoop up). This normal packet does not appear to do anything with a Notchian server. The second, special case packet appears to perform the action - based on current position/orientation and with a distance check - it appears that buckets can only be used within a radius of 6 units.

     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(x);
        packetData.writeUByte(y);
        packetData.writeInt(z);
        packetData.writeByte(direction);
        stack.write(packetData);
        packetData.writeByte(cursorX);
        packetData.writeByte(cursorY);
        packetData.writeByte(cursorZ);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        x = packetData.readInt();
        y = packetData.readUByte();
        z = packetData.readInt();
        direction = packetData.readByte();
        stack = new ItemStack(packetData);
        cursorX = packetData.readByte();
        cursorY = packetData.readByte();
        cursorZ = packetData.readByte();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
