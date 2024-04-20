package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CEnchantItemPacket5 extends PacketBase<IServerPacketHandler> {

    byte windowID;
    byte enchantment;
    //The position of the enchantment on the enchantment table window,
    // starting with 0 as the topmost one.

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeByte(windowID);
        packetData.writeByte(enchantment);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        windowID = packetData.readByte();
        enchantment = packetData.readByte();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
