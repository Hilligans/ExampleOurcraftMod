package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SPluginMessagePacket5 extends PacketBase<IClientPacketHandler> {


    /*
    Mods and plugins can use this to send their data. Minecraft itself uses a number of plugin channels. These internal channels are prefixed with MC|.
    https://wiki.vg/Plugin_channel

    More documentation on this: http://dinnerbone.com/blog/2012/01/13/minecraft-plugin-channels-messaging/
     */

    String channelName;
    short length;
    byte[] data;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(channelName);
        packetData.writeShort(length);
        packetData.writeBytesN(data);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        channelName = packetData.readUTF8();
        length = packetData.readShort();
        data = packetData.readBytes(length);
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
