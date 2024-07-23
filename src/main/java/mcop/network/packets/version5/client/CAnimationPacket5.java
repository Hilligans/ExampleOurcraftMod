package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CAnimationPacket5 extends PacketBase<IServerPacketHandler> {

    int entityID;
    byte animationID;

    /*
    Animation can be one of the following values:
    ID 	Animation
    0 	No animation
    1 	Swing arm
    2 	Damage animation
    3 	Leave bed
    5 	Eat food
    6 	Critical effect
    7 	Magic critical effect
    102 	(unknown)
    104 	Crouch
    105 	Uncrouch
     */


    public CAnimationPacket5() {}

    public CAnimationPacket5(int entityID, byte animationID) {
        this.entityID = entityID;
        this.animationID = animationID;
    }

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeByte(animationID);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        animationID = packetData.readByte();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
