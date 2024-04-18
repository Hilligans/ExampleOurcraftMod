package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SUpdateHealthPacket5 extends PacketBase<IClientPacketHandler> {

    float health;
    short food;
    float saturation;



    /*
    Sent by the server to update/set the health of the player it is sent to.

    Food saturation acts as a food "overcharge". Food values will not decrease while the saturation is over zero.
    Players logging in automatically get a saturation of 5.0. Eating food increases the saturation as well as the food bar.
     */

    public SUpdateHealthPacket5(float health, short food, float saturation) {
        this.health = health;
        this.food = food;
        this.saturation = saturation;
    }

    public SUpdateHealthPacket5() {}

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeFloat(health);
        packetData.writeShort(food);
        packetData.writeFloat(saturation);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        health = packetData.readFloat();
        food = packetData.readShort();
        saturation = packetData.readFloat();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO handle
    }
}
