package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;
import mcop.network.packets.version5.server.SChatMessagePacket5;
import org.json.JSONObject;

public class CChatMessagePacket5 extends PacketBase<IServerPacketHandler> {

    /*
    The default server will check the message to see if it begins with a '/'.
    If it doesn't, the username of the sender is prepended and sent to all other clients (including the original sender).
    If it does, the server assumes it to be a command and attempts to process it.
    A message longer than 100 characters will cause the server to kick the client.
    This change was initially done by allowing the client to not slice the message up to 119 (the previous limit),
    without changes to the server. For this reason, the vanilla server kept the code to cut messages at 119,
    but this isn't a protocol limitation and can be ignored.

    For more information, see Chat.
     */

    String message;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(message);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        message = packetData.readUTF8();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {
        JSONObject jsonObject = new JSONObject();
       // jsonObject.put("text", STR."<\{iServerPacketHandler.getServerPlayerData().getPlayerName()}>: \{message}");
        iServerPacketHandler.getServer().getServerNetworkHandler().sendPacketInternal(new SChatMessagePacket5(jsonObject.toString()));
    }
}
