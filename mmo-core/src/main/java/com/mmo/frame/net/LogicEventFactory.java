package com.mmo.frame.net;

import com.lmax.disruptor.EventFactory;

public class LogicEventFactory implements EventFactory<LogicEvent>{

	@Override
	public LogicEvent newInstance() {
		return new LogicEvent();
	}

}
