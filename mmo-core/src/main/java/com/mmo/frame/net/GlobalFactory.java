package com.mmo.frame.net;

/**
 * 
 * miaopeng 2018-04-19 17:26
 */
public class GlobalFactory {

	private static volatile EventConsumerFactory logicConsumerFactory;

	public static EventConsumerFactory getLogicConsumerFactory() {
		return logicConsumerFactory;
	}

	public static void setLogicConsumerFactory(EventConsumerFactory logicConsumerFactory) {
		GlobalFactory.logicConsumerFactory = logicConsumerFactory;
	}
}
