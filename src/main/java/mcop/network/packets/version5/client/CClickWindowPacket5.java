package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;
import mcop.network.packets.version5.ItemStack;

public class CClickWindowPacket5 extends PacketBase<IServerPacketHandler> {

    //This packet is sent by the player when it clicks on a slot in a window.

    byte windowID; //The id of the window which was clicked. 0 for player inventory.
    short slot;//The clicked slot. See below.
    byte button;// 	The button used in the click. See below.
    short action;//A unique number for the action, used for transaction handling (See the Transaction packet).
    byte mode;//Inventory operation mode. See below.
    ItemStack clickedItem;

    /*
    See inventory windows for further information about how slots are indexed.
    https://wiki.vg/Inventory#Windows

When right-clicking on a stack of items, half the stack will be picked up and half left in the slot. If the stack is an odd number, the half left in the slot will be smaller of the amounts.

The Action number is actually a counter, starting at 1. This number is used by the server as a transaction ID to send back a Transaction packet.
https://wiki.vg/index.php?title=Protocol&oldid=6003#0x6A

The distinct type of click performed by the client is determined by the combination of the "Mode" and "Button" fields.
Mode 	Button 	Slot 	Trigger
0 	0 	Normal 	Left mouse click
1 	Normal 	Right mouse click
1 	0 	Normal 	Shift + left mouse click
1 	Normal 	Shift + right mouse click (Identical behavior)
2 	0 	Normal 	Number key 1
1 	Normal 	Number key 2
2 	Normal 	Number key 3
... 	... 	...
8 	Normal 	Number key 9
3 	2 	Normal 	Middle click
4 	0 	Normal 	Drop key (Q)
1 	Normal 	Ctrl + Drop key (Ctrl-Q)
0 	-999 	Left click outside inventory holding nothing (No-op)
1 	-999 	Right click outside inventory holding nothing (No-op)
5 	0 	-999 	Starting left mouse drag (Or middle mouse)
4 	-999 	Starting right mouse drag
1 	Normal 	Add slot for left-mouse drag
5 	Normal 	Add slot for right-mouse drag
2 	-999 	Ending left mouse drag
6 	-999 	Ending right mouse drag
6 	0 	Normal 	Double click

Starting from version 1.5, "painting mode" is available for use in inventory windows. It is done by picking up stack of something (more than 1 items), then holding mouse button (left, right or middle) and dragging holded stack over empty (or same type in case of right button ) slots. In that case client sends the following to server after mouse button release (omitting first pickup packet which is sent as usual):

    packet with mode 5, slot -999 , button (0 for left | 4 for right);
    packet for every slot painted on, mode is still 5, button (1 | 5);
    packet with mode 5, slot -999, button (2 | 6);

If any of the painting packets other than the "progress" ones are sent out of order (for example, a start, some slots, then another start; or a left-click in the middle) the painting status will be reset.
     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeByte(windowID);
        packetData.writeShort(slot);
        packetData.writeByte(button);
        packetData.writeShort(action);
        packetData.writeByte(mode);
        clickedItem.write(packetData);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        windowID = packetData.readByte();
        slot = packetData.readShort();
        button = packetData.readByte();
        action = packetData.readShort();
        mode = packetData.readByte();
        clickedItem = new ItemStack(packetData);
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {
        System.out.println("Window: " + windowID);
    }
}
