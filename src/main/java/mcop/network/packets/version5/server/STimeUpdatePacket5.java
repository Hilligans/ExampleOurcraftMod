package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class STimeUpdatePacket5 extends PacketBase<IClientPacketHandler> {

    long ageOfWorld;
    long timeOfDay;

    /*
    Time is based on ticks, where 20 ticks happen every second. There are 24000 ticks in a day, making Minecraft days exactly 20 minutes long.

    The time of day is based on the timestamp modulo 24000. 0 is sunrise, 6000 is noon, 12000 is sunset, and 18000 is midnight.

    The default SMP server increments the time by 20 every second.
     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeLong(ageOfWorld);
        packetData.writeLong(timeOfDay);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        ageOfWorld = packetData.readLong();
        timeOfDay = packetData.readLong();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO implement
    }
}
