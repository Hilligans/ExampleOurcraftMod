package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;
import mcop.network.packets.version5.ItemStack;

public class SEntityEquipmentPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    short slot;
    ItemStack itemStack;


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeShort(slot);
        itemStack.write(packetData);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        slot = packetData.readShort();
        itemStack = new ItemStack(packetData);
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO handle
    }
}
