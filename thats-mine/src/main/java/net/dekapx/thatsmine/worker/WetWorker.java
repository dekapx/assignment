package net.dekapx.thatsmine.worker;

import net.dekapx.thatsmine.component.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static net.dekapx.thatsmine.common.CommonConstants.ONE_SECOND_DELAY;

public class WetWorker extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(WetWorker.class);

    private BlockingQueue<Component> conveyerBelt;

    public WetWorker(final BlockingQueue<Component> conveyerBelt, final String threadName){
        this.conveyerBelt = conveyerBelt;
        setName(threadName);
    }

    @Override
    public void run() {
        while(true){
            delayBySeconds(ONE_SECOND_DELAY);

            try {
                final Component component = conveyerBelt.take();
                // TODO: logic to build robot...
                buildRobot(component);

                LOGGER.info("{} consume : {}", Thread.currentThread().getName(), component.getType());
            } catch (InterruptedException e) {
                LOGGER.error("Exception " + e);
            }
        }
    }

    private void buildRobot(Component component) {
        switch (component.getType()) {
            case MAIN_UNIT:
                break;
            case MOP:
                break;
            default:
                break;
        }
    }

    private void delayBySeconds(final int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            LOGGER.error("Exception " + e);
        }
    }
}
