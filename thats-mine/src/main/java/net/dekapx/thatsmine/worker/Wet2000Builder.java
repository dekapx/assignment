package net.dekapx.thatsmine.worker;

import net.dekapx.thatsmine.component.Broom;
import net.dekapx.thatsmine.component.Component;
import net.dekapx.thatsmine.component.MainUnit;
import net.dekapx.thatsmine.component.Mop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wet2000Builder {
    private static final int MAX_SIZE = 3;

    private static final String MAIN_UNIT_KEY = "MainUnit";
    private static final String MOP_1_KEY = "Mop1";
    private static final String MOP_2_KEY = "Mop2";

    private Map<String, Component> components = new HashMap<>(3);

    public void put(String key, Component value) {
        if (!components.containsKey(key)) {
            components.put(key, value);
        }
    }

    public boolean isCompleted() {
        return components.size() == MAX_SIZE;
    }

    public Wet2000 getRobot() {
        final MainUnit mainUnit = (MainUnit)components.get(MAIN_UNIT_KEY);
        final List<Mop> mops = Arrays.asList((Mop)components.get(MOP_1_KEY),
                (Mop)components.get(MOP_2_KEY));
        return new Wet2000(mainUnit, mops);
    }

}
