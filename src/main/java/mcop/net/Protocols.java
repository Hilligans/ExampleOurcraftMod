package mcop.net;

import dev.hilligans.ourcraft.mod.handler.content.ModContainer;
import mcop.net.handshake.CHandshake;
import mcop.net.login.CLoginAcknowledged;
import mcop.net.login.CLoginStart;
import mcop.net.login.SDisconnect;
import mcop.net.login.SLoginSuccess;
import mcop.net.status.CPingRequest;
import mcop.net.status.CStatusRequest;
import mcop.net.status.SPongResponse;
import mcop.net.status.SStatusResponse;

public class Protocols {

    public static void register(ModContainer container) {
        container.registerPacket("mcop:handshake", 0x0, CHandshake.instance);

        container.registerPacket("mcop:status-client-bound", 0x0, SStatusResponse.instance);
        container.registerPacket("mcop:status-client-bound", 0x1, SPongResponse.instance);

        container.registerPacket("mcop:status-server-bound", 0x0, CStatusRequest.instance);
        container.registerPacket("mcop:status-server-bound", 0x1, CPingRequest.instance);

        container.registerPacket("mcop:login-client-bound", 0x0, SDisconnect.instance);
        container.registerPacket("mcop:login-client-bound", 0x2, SLoginSuccess.instance);

        container.registerPacket("mcop:login-server-bound", 0x0, CLoginStart.instance);
        container.registerPacket("mcop:login-server-bound", 0x3, CLoginAcknowledged.instance);
    }
}
