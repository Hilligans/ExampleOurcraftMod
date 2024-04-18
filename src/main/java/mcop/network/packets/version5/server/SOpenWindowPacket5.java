package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SOpenWindowPacket5 extends PacketBase<IClientPacketHandler> {

    //This is sent to the client when it should open an inventory, such as a chest, workbench, or furnace. This message is not sent anywhere for clients opening their own inventory.

    int windowID; //A unique id number for the window to be displayed. Notchian server implementation is a counter, starting at 1.
    int inventoryType; //The window type to use for display. Check below
    String windowTitle; //The title of the window.
    int numberSlots; //Number of slots in the window (excluding the number of slots in the player inventory).
    boolean useProvidedWindowTitle;//Bool 	If false, the client will look up a string like "window.minecart". If true, the client uses what the server provides.
    int entityID;//EntityHorse's entityId. Only sent when window type is equal to 11 (AnimalChest).

    //https://wiki.vg/Inventory#Windows

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUByte(windowID);
        packetData.writeUByte(inventoryType);
        packetData.writeUTF8(windowTitle);
        packetData.writeUByte(numberSlots);
        packetData.writeBoolean(useProvidedWindowTitle);
        packetData.writeInt(entityID);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        windowID = packetData.readUByte();
        inventoryType = packetData.readUByte();
        windowTitle = packetData.readUTF8();
        numberSlots = packetData.readUByte();
        useProvidedWindowTitle = packetData.readBoolean();
        entityID = packetData.readInt();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
