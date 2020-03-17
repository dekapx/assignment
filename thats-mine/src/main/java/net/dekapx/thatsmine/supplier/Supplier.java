package net.dekapx.thatsmine.supplier;

import net.dekapx.thatsmine.component.Component;
import net.dekapx.thatsmine.component.ComponentFactory;
import net.dekapx.thatsmine.util.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static net.dekapx.thatsmine.common.CommonConstants.CAPACITY;
import static net.dekapx.thatsmine.common.CommonConstants.ONE_SECOND_DELAY;

public class Supplier extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(Supplier.class);

    private static final int INITIAL_VALUE = 1;
    private AtomicInteger counter = new AtomicInteger(INITIAL_VALUE);
    private BlockingQueue<Component> conveyerBelt;


    public Supplier(final BlockingQueue<Component> conveyerBelt, final String threadName) {
        this.conveyerBelt = conveyerBelt;
        setName(threadName);
    }

    @Override
    public void run() {
        while (true) {
            deliverComponents();
        }
    }

    private void deliverComponents() {
        delayBySeconds(ONE_SECOND_DELAY);

        final int key = KeyGenerator.getRandomKey();
        try {
            final Component component = ComponentFactory.getComponentByKey(key);
            int remainingCapacity = conveyerBelt.remainingCapacity();
            if (isConveyerBeltWithinLimit(remainingCapacity)) {
                deliverComponent(component);
            } else if(isConveyerBeltExceedLimit()) {
                removeComponent();
            }
        } catch (InterruptedException e) {
            LOGGER.error("Exception " + e);
        }
    }

    private void delayBySeconds(final int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
            counter.addAndGet(1);
        } catch (InterruptedException e) {
            LOGGER.error("Exception " + e);
        }
    }

    private void removeComponent() throws InterruptedException {
        final Component removedComponent = conveyerBelt.take();
        LOGGER.info("{} removed due to capacity reached", removedComponent.getType());
    }

    private void deliverComponent(Component component) throws InterruptedException {
        conveyerBelt.put(component);
        LOGGER.info("{} put : {}", Thread.currentThread().getName(), component.getType());
        resetCounter();
    }

    private void resetCounter() {
        counter.set(1);
    }

    private boolean isConveyerBeltWithinLimit(int remainingCapacity) {
        return remainingCapacity > 0;
    }

    private boolean isConveyerBeltExceedLimit() {
        return counter.get() > CAPACITY;
    }
}
