package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SEntityStatusPacket5 extends PacketBase<IClientPacketHandler> {

    //Changes the direction an entity's head is facing.

    int entityID;
    byte entityStatus;

    //Entity Status 	Meaning
    //0 	Something related to living entities?
    //1 	Something related to the player entity?
    //2 	Living Entity hurt
    //3 	Living Entity dead
    //4 	Iron Golem throwing up arms
    //6 	Wolf/Ocelot/Horse taming - Spawn "heart" particles
    //7 	Wolf/Ocelot/Horse tamed - Spawn "smoke" particles
    //8 	Wolf shaking water - Trigger the shaking animation
    //9 	(of self) Eating accepted by server
    //10 	Sheep eating grass
    //11 	Iron Golem handing over a rose
    //12 	Villager mating - Spawn "heart" particles
    //13 	Spawn particles indicating that a villager is angry and seeking revenge
    //14 	Spawn happy particles near a villager
    //15 	Witch animation - Spawn "magic" particles
    //16 	Zombie converting into a villager by shaking violently
    //17 	Firework exploding
    //18 	Animal in love (ready to mate) - Spawn "heart" particles

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeByte(entityStatus);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        entityStatus = packetData.readByte();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
