package net.dekapx.thatsmine.component;

import net.dekapx.thatsmine.common.ComponentTypes;
import static net.dekapx.thatsmine.common.ComponentTypes.MAIN_UNIT;

public class MainUnit implements Component {
    @Override
    public ComponentTypes getType() {
        return MAIN_UNIT;
    }
}
