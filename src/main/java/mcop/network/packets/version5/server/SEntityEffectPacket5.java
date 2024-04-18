package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SEntityEffectPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    byte effectID;
    byte amplifier;
    short duration;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeByte(effectID);
        packetData.writeByte(amplifier);
        packetData.writeShort(duration);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        effectID = packetData.readByte();
        amplifier = packetData.readByte();
        duration = packetData.readShort();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
