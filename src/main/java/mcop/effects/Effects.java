package mcop.effects;

import dev.hilligans.ourcraft.util.registry.Registry;

public class Effects {

    public static final Effect none = new Effect("none");
    public static final Effect speed = new Effect("speed");
    public static final Effect slowness = new Effect("slowness");
    public static final Effect haste = new Effect("haste");
    public static final Effect mining_fatigue = new Effect("mining_fatigue");
    public static final Effect strength = new Effect("strength");
    public static final Effect instant_health = new Effect("instant_health");
    public static final Effect instant_damage = new Effect("instant_damage");
    public static final Effect jump_boost = new Effect("jump_boost");
    public static final Effect nausea = new Effect("nausea");
    public static final Effect regeneration = new Effect("regeneration");
    public static final Effect resistance = new Effect("resistance");
    public static final Effect fire_resistance = new Effect("fire_resistance");
    public static final Effect water_breathing = new Effect("water_breathing");
    public static final Effect invisibility = new Effect("invisibility");
    public static final Effect blindness = new Effect("blindness");
    public static final Effect night_vision = new Effect("night_vision");
    public static final Effect hunger = new Effect("hunger");
    public static final Effect weakness = new Effect("weakness");
    public static final Effect poison = new Effect("poison");
    public static final Effect wither = new Effect("wither");
    public static final Effect health_boost = new Effect("health_boost");
    public static final Effect absorption = new Effect("absorption");
    public static final Effect saturation = new Effect("saturation");
    public static final Effect glowing = new Effect("glowing");
    public static final Effect levitation = new Effect("levitation");
    public static final Effect luck = new Effect("luck");
    public static final Effect unluck = new Effect("unluck");
    public static final Effect slow_falling = new Effect("slow_falling");
    public static final Effect conduit_power = new Effect("conduit_power");
    public static final Effect dolphins_grace = new Effect("dolphins_grace");
    public static final Effect bad_omen = new Effect("bad_omen");
    public static final Effect hero_of_the_village = new Effect("hero_of_the_village");
    public static final Effect darkness = new Effect("darkness");

    public static final Effect[] effects = new Effect[]{none, speed, slowness, haste, mining_fatigue, strength, instant_health, instant_damage, jump_boost, nausea, regeneration, resistance, fire_resistance, water_breathing,
    invisibility, blindness, night_vision, hunger, weakness, poison, wither, health_boost, absorption, saturation, glowing, levitation, luck, unluck, slow_falling, conduit_power, dolphins_grace, bad_omen, hero_of_the_village, darkness};

    public static void registerEffects(Registry<Effect> effectRegistry) {
        effectRegistry.putAll(effects);
    }
}
