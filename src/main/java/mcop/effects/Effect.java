package mcop.effects;

import dev.hilligans.ourcraft.util.registry.IRegistryElement;

public class Effect implements IRegistryElement {

    String name;

    public Effect(String name) {
        this.name = name;
    }

    @Override
    public String getResourceName() {
        return name;
    }

    @Override
    public String getResourceOwner() {
        return "mcop";
    }

    @Override
    public String getResourceType() {
        return "effects";
    }
}
