package mcop.entities;

import dev.hilligans.ourcraft.data.other.IBoundingBox;
import dev.hilligans.ourcraft.entity.EntityType;
import dev.hilligans.ourcraft.entity.IEntity;
import dev.hilligans.ourcraft.world.newworldsystem.IWorld;

public class EntityBase implements IEntity {

    IWorld world;

    public float pitch;
    public float yaw;

    public float velX;
    public float velY;
    public float velZ;

    public double x;
    public double y;
    public double z;


    public IBoundingBox entityBoundingBox;
    public MCOPEntityType entityType;

    public EntityBase(MCOPEntityType entityType) {
        this.entityBoundingBox = entityType.getBoundingBox();
        this.entityType = entityType;
    }

    int id = 0;

    @Override
    public IWorld getWorld() {
        return world;
    }

    @Override
    public void setWorld(IWorld world) {
        this.world = world;
    }

    @Override
    public void setRot(float pitch, float yaw, float roll) {
        this.pitch = pitch;
        this.yaw = yaw;
    }

    @Override
    public long getID() {
        return id;
    }

    @Override
    public void setID(long id) {
        this.id = (int) id;
    }

    @Override
    public EntityType getEntityType() {
        return entityType;
    }

    @Override
    public IBoundingBox getEntityBoundingBox() {
        return entityBoundingBox;
    }

    @Override
    public float getPitch() {
        return pitch;
    }

    @Override
    public float getYaw() {
        return yaw;
    }

    @Override
    public float getRoll() {
        return 0;
    }

    @Override
    public void setVel(float velX, float velY, float velZ) {
        this.velX = velX;
        this.velY = velY;
        this.velZ = velZ;
    }

    @Override
    public float getVelX() {
        return velX;
    }

    @Override
    public float getVelY() {
        return velY;
    }

    @Override
    public float getVelZ() {
        return velZ;
    }

    @Override
    public void setPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public void tick() {

    }
}
