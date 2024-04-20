package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CPlayerPacket5 extends PacketBase<IServerPacketHandler> {

    /*
    This packet is used to indicate whether the player is on ground (walking/swimming), or airborne (jumping/falling).

    When dropping from sufficient height, fall damage is applied when this state goes from False to True. The amount of damage applied is based on the point where it last changed from True to False. Note that there are several movement related packets containing this state.
     */

    boolean onGround; //True if the client is on the ground, False otherwise

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeBoolean(onGround);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        onGround = packetData.readBoolean();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {
        //todo handle
    }
}
