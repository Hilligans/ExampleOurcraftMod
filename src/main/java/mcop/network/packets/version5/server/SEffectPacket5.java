package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SEffectPacket5 extends PacketBase<IClientPacketHandler> {

    int effectID;
    int x;
    byte y;
    int z;
    int data;
    boolean disableRelativeVolume;

    /*
    Sent when a client is to play a sound or particle effect.

    By default, the minecraft client adjusts the volume of sound effects based on distance.
    The final boolean field is used to disable this,
    and instead the effect is played from 2 blocks away in the correct direction.
    Currently this is only used for effects 1013 and 1018 (mob.wither.spawn and mob.enderdragon.end, respectively),
    and is ignored for any other value by the client.
     */


    /*
    Effects
ID 	Name
Sound
1000 	random.click
1001 	random.click
1002 	random.bow
1003 	random.door_open or random.door_close (50/50 chance)
1004 	random.fizz
1005 	Play a music disc. Data Record ID
(1006 not assigned)
1007 	mob.ghast.charge
1008 	mob.ghast.fireball
1009 	mob.ghast.fireball, but with a lower volume.
1010 	mob.zombie.wood
1011 	mob.zombie.metal
1012 	mob.zombie.woodbreak
1013 	mob.wither.spawn
1014 	mob.wither.shoot
1015 	mob.bat.takeoff
1016 	mob.zombie.infect
1017 	mob.zombie.unfect
1018 	mob.enderdragon.end
1020 	random.anvil_break
1021 	random.anvil_use
1022 	random.anvil_land
Particle
2000 	Spawns 10 smoke particles, e.g. from a fire. Data direction, see below
2001 	Block break. Data Block ID
2002 	Splash potion. Particle effect + glass break sound. Data Potion ID
2003 	Eye of ender entity break animation - particles and sound
2004 	Mob spawn particle effect: smoke + flames
2005 	Spawn "happy villager" effect (green crosses), used for bonemealing vegetation.
2006 	Spawn fall particles (when player hits ground). Data fall damage taken for particle speed

Smoke directions:
ID 	Direction
0 	South - East
1 	South
2 	South - West
3 	East
4 	(Up or middle ?)
5 	West
6 	North - East
7 	North
8 	North - West
     */

    //https://github.com/ddevault/Craft.Net/blob/master/source/Craft.Net.Common/SoundEffect.cs
    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(effectID);
        packetData.writeInt(x);
        packetData.writeByte(y);
        packetData.writeInt(z);
        packetData.writeInt(data);
        packetData.writeBoolean(disableRelativeVolume);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        effectID = packetData.readInt();
        x = packetData.readInt();
        y = packetData.readByte();
        z = packetData.readInt();
        data = packetData.readInt();
        disableRelativeVolume = packetData.readBoolean();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo implement
    }
}
