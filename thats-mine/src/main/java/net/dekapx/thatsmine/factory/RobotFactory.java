package net.dekapx.thatsmine.factory;

import net.dekapx.thatsmine.component.Component;
import net.dekapx.thatsmine.supplier.Supplier;
import net.dekapx.thatsmine.worker.DryWorker;
import net.dekapx.thatsmine.worker.WetWorker;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static net.dekapx.thatsmine.common.CommonConstants.CAPACITY;

public class RobotFactory {

    public static void main(String[] args) {
        final BlockingQueue<Component> conveyerBelt = new ArrayBlockingQueue<>(CAPACITY);

        final Supplier supplier = new Supplier(conveyerBelt, "Supplier");
        final DryWorker dryWorker = new DryWorker(conveyerBelt, "DryWorker");
        final WetWorker wetWorker = new WetWorker(conveyerBelt, "WetWorker");

        supplier.start();
        dryWorker.start();
        wetWorker.start();
    }
}
