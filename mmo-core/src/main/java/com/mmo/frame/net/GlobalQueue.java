package com.mmo.frame.net;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.LifecycleAware;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *  公共的环形队列, 放到core里可以被sm, scene等调用
 *  Created by miaopeng on 2018-04-17
 */
public class GlobalQueue {

    /**
     * 大小为2的指数幂
     */
    private static final int INIT_LOGIC_EVENT_CAPACITY = 1024 * 8 * 16;

    private static final Disruptor<LogicEvent> DISRUPTOR;

    public static final RingBuffer<LogicEvent> logicQueue;


    /**
     * 静态方法里初始化了Disruptor
     */
    static {

        ExecutorService logicExecutor = Executors.newSingleThreadExecutor((
                Runnable r) -> new Thread(r, "LOGIC_THREAD"));

        EventConsumer<LogicEvent> logicEventConsumer = GlobalFactory.getLogicConsumerFactory().newConsumer();

        DISRUPTOR = new Disruptor<>(
                new LogicEventFactory(), INIT_LOGIC_EVENT_CAPACITY,
                logicExecutor, ProducerType.MULTI,
                new SleepingWaitExtendStrategy(logicEventConsumer)
                //new TimeoutBlockingExtendWaitStrategy(1000, TimeUnit.MILLISECONDS, logicEventConsumer)
        );

        LogicEventHandler logicEventHandler = new LogicEventHandler(logicEventConsumer);
        DISRUPTOR.setDefaultExceptionHandler(logicEventHandler); // disruptor 3.3.6
        //DISRUPTOR.handleExceptionsWith(logicEventHandler);// disruptor 3.3.2
        DISRUPTOR.handleEventsWith(logicEventHandler);

        logicQueue = DISRUPTOR.getRingBuffer();

        DISRUPTOR.start();
    }
}

class LogicEventHandler implements EventHandler<LogicEvent>, LifecycleAware, ExceptionHandler<LogicEvent> {
    private static final Logger logger = LoggerFactory.getLogger(LogicEventHandler.class);

    private EventConsumer<LogicEvent> logicEventConsumer;

    public LogicEventHandler(EventConsumer<LogicEvent> logicEventConsumer) {
        this.logicEventConsumer = logicEventConsumer;
    }

    @Override
    public void onEvent(LogicEvent logicEvent, long l, boolean b) throws Exception {
        logicEventConsumer.onEventTemplate(logicEvent, l, b);
    }

    @Override
    public void onStart() {
        logger.warn("disruptor onStart");
    }

    @Override
    public void onShutdown() {
        logger.warn("disruptor onShutdown");
    }

    @Override
    public void handleEventException(Throwable throwable, long l, LogicEvent logicEvent) {
        logger.error("disruptor handleEventException", throwable);
    }

    @Override
    public void handleOnStartException(Throwable throwable) {
        logger.error("disruptor handleOnStartException", throwable);
    }

    @Override
    public void handleOnShutdownException(Throwable throwable) {
        logger.error("disruptor handleOnShutdownException", throwable);
    }
}
