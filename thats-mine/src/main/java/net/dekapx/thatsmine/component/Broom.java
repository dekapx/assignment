package net.dekapx.thatsmine.component;

import net.dekapx.thatsmine.common.ComponentTypes;
import static net.dekapx.thatsmine.common.ComponentTypes.BROOM;


public class Broom implements Component {
    @Override
    public ComponentTypes getType() {
        return BROOM;
    }
}
