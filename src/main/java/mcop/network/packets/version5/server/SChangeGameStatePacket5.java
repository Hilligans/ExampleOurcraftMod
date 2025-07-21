package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SChangeGameStatePacket5 extends PacketBase<IClientPacketHandler> {

    // It appears when a bed can't be used as a spawn point and when the rain state changes.
    //
    // The class has an array of strings linked to reason codes
    // 0, 1, 2, and 3 but only the codes for 1 and 2 are null.

    int reason;
    float value; //Depends on reason

    /*
    Reason codes
    Code 	Effect 	Notes
    0 	    Invalid Bed 	        "tile.bed.notValid"
    1 	    End raining
    2 	    Begin raining
    3 	    Change game mode 	    "gameMode.changed" 0 - Survival, 1 - Creative, 2 - Adventure
    4 	    Enter credits
    5 	    Demo messages 	        0 - Show welcome to demo screen, 101 - Tell movement controls, 102 - Tell jump control, 103 - Tell inventory control
    6 	    Arrow hitting player 	Appears to be played when an arrow strikes another player in Multiplayer
    7 	    Fade value 	            The current darkness value. 1 = Dark, 0 = Bright, Setting the value higher causes the game to change color and freeze
    8 	    Fade time 	            Time in ticks for the sky to fade
     */

    public SChangeGameStatePacket5() {}

    public SChangeGameStatePacket5(int reason, float value) {
        this.reason = reason;
        this.value = value;
    }

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUByte(reason);
        packetData.writeFloat(value);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        reason = packetData.readUByte();
        value = packetData.readFloat();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO handle
    }
}
