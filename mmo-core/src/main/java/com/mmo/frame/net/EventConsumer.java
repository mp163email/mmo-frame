package com.mmo.frame.net;

import com.mmo.frame.interfaces.Cleanable;
import com.mmo.frame.utils.CloseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * miaopeng 2018-04-19 17:29
 */
public abstract class EventConsumer<T extends Cleanable> {

    private static final Logger logger = LoggerFactory
            .getLogger(EventConsumer.class);

    private static final int LOOP_PER_EVENT_TIMES = 10000;

    private int eventTimeIndex = 0;

    private boolean isFirstLoop = true;

    public final void loopTemplate() {

        try {
            ensureThreadStart();

            loop();

            eventTimeIndex = 0;
        } catch (Exception e) {
            logger.error("", e);
        } catch (OutOfMemoryError e) {
            logger.error("", e);
            CloseUtils.exit("Core", "", -2);
        }
    }

    private void ensureThreadStart() {

        if (isFirstLoop) {

            logger.info("logic thread starting");

            isFirstLoop = false;

            try {
            	
                initWhenThreadStart();

            } catch (Exception e) {

                logger.error("", e);

                CloseUtils.exit("Core", "", -1);

            }
            logger.info("logic thread started");
        }

    }

    public abstract void loop();

    protected abstract void onEvent(T event, long sequence, boolean endOfBatch);

    public final void onEventTemplate(T event, long sequence, boolean endOfBatch) {
        long start = System.nanoTime();

        try {
            ensureThreadStart();

            try {
                onEvent(event, sequence, endOfBatch);
            } finally {
                event.clean();

                ++eventTimeIndex;

                if (eventTimeIndex == LOOP_PER_EVENT_TIMES) {
                    eventTimeIndex = 0;
                    loopTemplate();
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }

        long end = System.nanoTime();

        if (event != null) {
            if ((end - start) > 1000000L * 1000) {
                if (event instanceof LogicEvent) {
                    logger.warn("TICK耗时太长 {}ms, protoEnum:{}", (end - start) / 1000000L, ((LogicEvent) event).getProtoEnum());
                } else {
                    logger.warn("TICK耗时太长 {}ms", (end - start) / 1000000L);
                }
            } else if (event instanceof LogicEvent && ((LogicEvent) event).getTickRecv() != 0) {
                if ((end - ((LogicEvent) event).getTickRecv()) > 1000000L * 1000) {
                    logger.warn("network包耗时太长 {}ms", (end - ((LogicEvent) event).getTickRecv()) / 1000000L);
                }
                ((LogicEvent) event).setTickRecv(0);
            }
        }
    }

    protected abstract void initWhenThreadStart();

}
