package net.dekapx.thatsmine.worker;

import net.dekapx.thatsmine.component.Broom;
import net.dekapx.thatsmine.component.Component;
import net.dekapx.thatsmine.component.MainUnit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dry2000Builder {
    private static final int MAX_SIZE = 3;

    private static final String MAIN_UNIT_KEY = "MainUnit";
    private static final String BROOM_1_KEY = "Broom1";
    private static final String BROOM_2_KEY = "Broom2";

    private Map<String, Component> components = new HashMap<>(3);

    public void put(String key, Component value) {
        if (!components.containsKey(key)) {
            components.put(key, value);
        }
    }

    public boolean isCompleted() {
        return components.size() == MAX_SIZE;
    }

    public Dry2000 getRobot() {
        final MainUnit mainUnit = (MainUnit)components.get(MAIN_UNIT_KEY);
        final List<Broom> brooms = Arrays.asList((Broom)components.get(BROOM_1_KEY),
                (Broom)components.get(BROOM_2_KEY));
        return new Dry2000(mainUnit, brooms);
    }

}
