package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SWindowPropertyPacket5 extends PacketBase<IClientPacketHandler> {

    int windowID; //The id of the window.
    short property; //Which property should be updated.
    short value; //The new value for the property.

    /*
    Furnace
    Properties:
        0: Progress arrow
        1: Fire icon (fuel)

    Values:
        0-200 for progress arrow
        0-200 for fuel indicator

    Ranges are presumably in in-game ticks

    Enchantment Table

    Properties: 0, 1 or 2 depending on the "enchantment slot" being given.

    Values: The enchantment's level.

    Beacon
        0: Power level
        1: Potion effect one
        2: Potion effect two

    Anvil
        0: Maximum cost

    Brewing Stand
        0: Brew time

    Brew time is a value between 0 and 400, with 400 making the arrow empty, and 0 making the arrow full.
     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUByte(windowID);
        packetData.writeShort(property);
        packetData.writeShort(value);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        windowID = packetData.readUByte();
        property = packetData.readShort();
        value = packetData.readShort();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
