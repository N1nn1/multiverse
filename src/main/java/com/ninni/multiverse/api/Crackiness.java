package com.ninni.multiverse.api;

import com.ninni.multiverse.entities.CobblestoneGolemEntity;

import java.util.Arrays;
import java.util.Comparator;

public enum Crackiness {
    NONE(0, "none"),
    LOW(1, "low"),
    MEDIUM(2, "medium"),
    HIGH(3, "high");

    public static final Crackiness[] BY_ID;
    private final int id;
    private final String name;

    Crackiness(int j, String string2) {
        this.id = j;
        this.name = string2;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    static {
        BY_ID = Arrays.stream(Crackiness.values()).sorted(Comparator.comparingInt(Crackiness::getId)).toArray(Crackiness[]::new);
    }
}
