package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CPlayerDiggingPacket5 extends PacketBase<IServerPacketHandler> {

    //Sent when the player mines a block. A Notchian server only accepts digging packets with coordinates within a 6-unit radius of the player's position.

    byte status;
    int x;
    int y;
    int z;
    byte face;

    /*
    Status can (currently) be one of six values:
    Meaning 	Value
    Started digging 	0
    Cancelled digging 	1
    Finished digging 	2
    Drop item stack 	3
    Drop item 	        4
    Shoot arrow / finish eating 	5


    Notchian clients send a 0 (started digging) when they start digging and a 2 (finished digging) once they think they are finished.
    If digging is aborted, the client simply send a 1 (Cancel digging).

    Status code 4 (drop item) is a special case. In-game, when you use the Drop Item command (keypress 'q'),
    a dig packet with a status of 4, and all other values set to 0, is sent from client to server. Status code 3 is similar, but drops the entire stack.

    Status code 5 (shoot arrow / finish eating) is also a special case.
    The x, y and z fields are all set to 0 like above, with the exception of the face field, which is set to 255.

    The face can be one of six values, representing the face being hit:
    Value 	0 	1 	2 	3 	4 	5
    Offset 	-Y 	+Y 	-Z 	+Z 	-X 	+X

    In 1.7.3, when a player opens a door with left click the server receives Packet 0xE+start digging and opens the door.
     */
    public CPlayerDiggingPacket5() {}

    public CPlayerDiggingPacket5(byte status, int x, int y, int z, byte face) {
        this.status = status;
        this.x = x;
        this.y = y;
        this.z = z;
        this.face = face;
    }

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeByte(status);
        packetData.writeInt(x);
        packetData.writeUByte(y);
        packetData.writeInt(z);
        packetData.writeByte(face);

    }

    @Override
    public void decode(IPacketByteArray packetData) {
        status = packetData.readByte();
        x = packetData.readInt();
        y = packetData.readUByte();
        z = packetData.readInt();
        face = packetData.readByte();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
