package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SAnimationPacket5 extends PacketBase<IClientPacketHandler> {

    /*
    Sent whenever an entity should change animation.

    Animation can be one of the following values:
    ID 	Animation
    0 	Swing arm
    1 	Damage animation
    2 	Leave bed
    3 	Eat food
    4 	Critical effect
    5 	Magic critical effect
    102 	(unknown)
    104 	Crouch
    105 	Uncrouch
     */

    int entityID;
    int animation;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeUByte(animation);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        animation = packetData.readUByte();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO implement
    }
}
