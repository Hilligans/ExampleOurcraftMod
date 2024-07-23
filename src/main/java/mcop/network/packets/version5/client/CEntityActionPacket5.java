package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;
import io.netty.buffer.ByteBuf;

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
    10  Blah
     */

    int entityID; //playerID
    byte actionID;
    int jumpBoost; //Horse jump boost. Ranged from 0 -> 100.

    public CEntityActionPacket5() {}

    public CEntityActionPacket5(int entityID, byte actionID, int jumpBoost) {
        this.entityID = entityID;
        this.actionID = actionID;
        this.jumpBoost = jumpBoost;
    }

    public CEntityActionPacket5(int entityID, byte actionID) {
        this(entityID, actionID, 0);
    }

    public static CEntityActionPacket5 crouch(int entityID) {
        return new CEntityActionPacket5(entityID, (byte) 1);
    }

    public static CEntityActionPacket5 uncrouch(int entityID) {
        return new CEntityActionPacket5(entityID, (byte) 2);
    }

    public static CEntityActionPacket5 leaveBed(int entityID) {
        return new CEntityActionPacket5(entityID, (byte) 3);
    }

    public static CEntityActionPacket5 startSprinting(int entityID) {
        return new CEntityActionPacket5(entityID, (byte) 4);
    }

    public static CEntityActionPacket5 stopSprinting(int entityID) {
        return new CEntityActionPacket5(entityID, (byte) 5);
    }

    public static CEntityActionPacket5 horseJump(int entityID, int jumpBoost) {
        return new CEntityActionPacket5(entityID, (byte) 6, jumpBoost);
    }


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
