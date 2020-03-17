package net.dekapx.thatsmine.component;

import java.util.HashMap;
import java.util.Map;

import static net.dekapx.thatsmine.common.CommonConstants.NUMBER_OF_COMPONENTS;

public class ComponentFactory {
    private static Map<Integer, Component> components = new HashMap<>(NUMBER_OF_COMPONENTS);

    static {
        populateComponents();
    }

    public static Component getComponentByKey(final int key) {
        return components.get(key);
    }

    private static void populateComponents() {
        components.put(1, new MainUnit());
        components.put(2, new Broom());
        components.put(3, new Mop());
    }
}
