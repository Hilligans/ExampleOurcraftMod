package mcop.entities;

import dev.hilligans.ourcraft.data.other.BoundingBox;
import dev.hilligans.ourcraft.data.other.IBoundingBox;
import dev.hilligans.ourcraft.entity.EntityType;
import dev.hilligans.ourcraft.entity.IEntity;

public class MCOPEntityType extends EntityType {

    float width;
    float height;
    int id;
    int maxHealth;
    int entityInventorySize;

    IBoundingBox boundingBox;

    public MCOPEntityType(String name, String modID, int id, float width, float height) {
        super(name, modID);
        this.id = id;
        this.width = width;
        this.height = height;
        boundingBox = new BoundingBox(-width/2, 0, -width/2, width/2, height, width/2);
    }

    public IEntity createEntity() {

        return null;
    }

    public IBoundingBox getBoundingBox() {
        return boundingBox;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getEntityInventorySize() {
        return entityInventorySize;
    }
}
