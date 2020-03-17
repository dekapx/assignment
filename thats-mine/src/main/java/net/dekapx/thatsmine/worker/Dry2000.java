package net.dekapx.thatsmine.worker;

import net.dekapx.thatsmine.component.Broom;
import net.dekapx.thatsmine.component.MainUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dry2000 {
    private MainUnit mainUnit;
    private List<Broom> brooms;

    Dry2000(final MainUnit mainUnit, final List<Broom> brooms) {
        this.mainUnit = mainUnit;
        this.brooms = new ArrayList<>(brooms);
    }

    public MainUnit getMainUnit() {
        return mainUnit;
    }

    public List<Broom> getBrooms() {
        return Collections.unmodifiableList(brooms);
    }
}
