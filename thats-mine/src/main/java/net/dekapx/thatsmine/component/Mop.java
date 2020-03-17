package net.dekapx.thatsmine.component;

import net.dekapx.thatsmine.common.ComponentTypes;
import static net.dekapx.thatsmine.common.ComponentTypes.MOP;

public class Mop implements Component {
    @Override
    public ComponentTypes getType() {
        return ComponentTypes.MOP;
    }
}
