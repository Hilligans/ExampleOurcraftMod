package mcop.network.packets.version5;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.tag.CompoundNBTTag;

public class ItemStack {

    public int itemID;
    public byte itemCount;
    public CompoundNBTTag nbt;
    public boolean present = true;
    short dmg = 0;

    public ItemStack(IPacketByteArray byteArray) {
        short val = byteArray.readShort();
        if(val != -1) {
            itemID = val;
            itemCount = byteArray.readByte();
            dmg = byteArray.readShort();
            nbt = new CompoundNBTTag();
            //nbt.read(byteArray);
        } else {
            present = false;
        }
        /*
        if(byteArray.readBoolean()) {
            itemID = byteArray.readVarInt();
            itemCount = byteArray.readByte();
            nbt = new CompoundNBTTag();
            nbt.read(byteArray);
        } else {
            present = false;
        }

         */
    }

    public void write(IPacketByteArray byteArray) {
        byteArray.writeBoolean(present);
        if(present) {
            byteArray.writeVarInt(itemID);
            byteArray.writeByte(itemCount);
            nbt.write(byteArray);
        }
    }

    public dev.hilligans.ourcraft.item.ItemStack getInternal() {
      //  dev.hilligans.ourcraft.item.ItemStack itemStack = new dev.hilligans.ourcraft.item.ItemStack();
        return null;
    }
}
