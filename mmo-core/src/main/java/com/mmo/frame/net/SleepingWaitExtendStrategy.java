package com.mmo.frame.net;

import com.lmax.disruptor.AlertException;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WaitStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

public class SleepingWaitExtendStrategy implements WaitStrategy {

	private static final Logger logger = LoggerFactory.getLogger(SleepingWaitExtendStrategy.class);
	
	private final EventConsumer<?> eventConsumer;

	private static final int DEFAULT_RETRIES = 200;

	private final int retries;

	public SleepingWaitExtendStrategy(EventConsumer<?> eventConsumer) {

		this.retries = DEFAULT_RETRIES;

		this.eventConsumer = eventConsumer;

	}

	@Override
	public long waitFor(final long sequence, Sequence cursor,
			final Sequence dependentSequence, final SequenceBarrier barrier)
			throws AlertException, InterruptedException {
		long availableSequence;
		int counter = retries;

		while ((availableSequence = dependentSequence.get()) < sequence) {


				eventConsumer.loopTemplate();


			counter = applyWaitMethod(barrier, counter);

		}

		return availableSequence;
	}

	@Override
	public void signalAllWhenBlocking() {
	}

	private int applyWaitMethod(final SequenceBarrier barrier, int counterParam)
			throws AlertException {
		barrier.checkAlert();
		int counter = counterParam;

		if (counter > 100) {
			--counter;
		} else if (counter > 0) {
			--counter;
			Thread.yield();
		} else {
			LockSupport.parkNanos(1L);
		}

		return counter;
	}
}
