package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CPluginMessagePacket5 extends PacketBase<IServerPacketHandler> {


    /*
    Mods and plugins can use this to send their data.
    Minecraft itself uses a number of plugin channels. These internal channels are prefixed with MC|.
    https://wiki.vg/Plugin_channel

    More documentation on this:
    http://dinnerbone.com/blog/2012/01/13/minecraft-plugin-channels-messaging/
     */

    String channel;
    short length;
    byte[] data;


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(channel);
        packetData.writeShort(length);
        packetData.writeBytesN(data);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        channel = packetData.readUTF8();
        length = packetData.readShort();
        data = packetData.readBytes(length);
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
