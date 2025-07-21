package mcop.entities;

import dev.hilligans.ourcraft.data.other.server.IInventoryChanged;
import dev.hilligans.ourcraft.entity.ILivingEntity;
import dev.hilligans.ourcraft.item.ItemStack;

public class LivingEntityBase extends EntityBase implements ILivingEntity {

    public int health;
    public int maxHealth;

    public ItemStack[] inventory;

    public LivingEntityBase(MCOPEntityType entityType) {
        super(entityType);
        this.health = entityType.getMaxHealth();
        this.maxHealth = entityType.getMaxHealth();
        inventory = new ItemStack[entityType.getInventorySize()];
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        health = Math.min(health, maxHealth);
    }

    @Override
    public void dealDamage(int dmg) {
        health -= dmg;
        if(health < 0) {
            health = 0;
        }
    }

    @Override
    public void heal(int amount) {
        health += amount;
        if(health < maxHealth) {
            health = maxHealth;
        }
    }

    @Override
    public boolean alive() {
        return health > 0;
    }

    @Override
    public int getInventorySize() {
        return entityType.getInventorySize();
    }

    @Override
    public ItemStack getItem(int slot) {
        return inventory[slot];
    }

    @Override
    public void setItem(int slot, ItemStack item) {
        inventory[slot] = item;
    }

    @Override
    public boolean addItem(ItemStack itemStack) {
        for(int i = 0; i < inventory.length && !itemStack.isEmpty(); i++) {
            if(inventory[i] == null) {
                inventory[i] = itemStack;
            } else if(inventory[i].isEmpty()) {
                inventory[i] = itemStack;
            } else {
                itemStack = inventory[i].mergeStack(itemStack);
            }
        }
        return itemStack.isEmpty();
    }

    @Override
    public void addListener(int slot, IInventoryChanged iInventoryChanged) {

    }

    @Override
    public void removeListener(int slot, IInventoryChanged iInventoryChanged) {

    }

    @Override
    public void notifyListeners(int slot) {

    }
}
