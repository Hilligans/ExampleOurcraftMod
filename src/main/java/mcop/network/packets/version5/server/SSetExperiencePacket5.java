package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SSetExperiencePacket5 extends PacketBase<IClientPacketHandler> {

    float experienceLevel; //between 0 and 1
    short level;
    short totalExperience;


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeFloat(experienceLevel);
        packetData.writeShort(level);
        packetData.writeShort(totalExperience);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        experienceLevel = packetData.readFloat();
        level = packetData.readShort();
        totalExperience = packetData.readShort();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
