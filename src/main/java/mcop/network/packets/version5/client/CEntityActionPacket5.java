package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CEntityActionPacket5 extends PacketBase<IServerPacketHandler> {

    //Sent at least when crouching, leaving a bed, or sprinting.
    //To send action animation to client use 0x28.
    //The client will send this with Action ID = 3 when "Leave Bed" is clicked.

    /*
    Action ID can be one of the following values:
    ID 	Action
    1 	Crouch
    2 	Uncrouch
    3 	Leave bed
    4 	Start sprinting
    5 	Stop sprinting
    6 	Horse jump
    5 	Horse interaction ?
     */

    int entityID; //playerID
    byte actionID;
    int jumpBoost; //Horse jump boost. Ranged from 0 -> 100.

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeByte(actionID);
        packetData.writeInt(jumpBoost);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        actionID = packetData.readByte();
        jumpBoost = packetData.readInt();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
