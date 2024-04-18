package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SEntityPropertiesPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;

    //Key 	        String
    //Value 	    Double
    //List Length 	Short 	Number of list elements that follow.
    //Modifiers 	Array of Modifier Data 	http://www.minecraftwiki.net/wiki/Attribute#Modifiers

    @Override
    public void encode(IPacketByteArray packetData) {

        //todo implement
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        int length = packetData.readShort();
        /*for(int x = 0; x < length; x++) {
            packetData.readUTF8();
            packetData.readDouble();
            int length1 = packetData.readShort();
            for(int y = 0; y < length1; y++) {

            }
        }

         */
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
