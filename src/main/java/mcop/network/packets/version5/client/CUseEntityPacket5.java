package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CUseEntityPacket5 extends PacketBase<IServerPacketHandler> {

    /*
    This packet is sent from the client to the server when the client attacks or right-clicks another entity (a player, minecart, etc).

    A Notchian server only accepts this packet if the entity being attacked/used is visible without obstruction and within a 4-unit radius of the player's position.

    Note that middle-click in creative mode is interpreted by the client and sent as a Creative Inventory Action packet instead.
     */

    int target;
    byte mouse;


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(target);
        packetData.writeByte(mouse);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        target = packetData.readInt();
        mouse = packetData.readByte();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {
        //todo handle
    }
}
