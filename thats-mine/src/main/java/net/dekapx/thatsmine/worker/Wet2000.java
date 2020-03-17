package net.dekapx.thatsmine.worker;

import net.dekapx.thatsmine.component.Broom;
import net.dekapx.thatsmine.component.MainUnit;
import net.dekapx.thatsmine.component.Mop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wet2000 {
    private MainUnit mainUnit;
    private List<Mop> mops;

    Wet2000(final MainUnit mainUnit, final List<Mop> mops) {
        this.mainUnit = mainUnit;
        this.mops = new ArrayList<>(mops);
    }

    public MainUnit getMainUnit() {
        return mainUnit;
    }

    public List<Mop> getMops() {
        return Collections.unmodifiableList(mops);
    }
}
