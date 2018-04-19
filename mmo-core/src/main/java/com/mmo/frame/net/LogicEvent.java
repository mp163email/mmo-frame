package com.mmo.frame.net;

import com.mmo.frame.interfaces.Cleanable;
import io.netty.channel.Channel;


/**
 *  逻辑事件-环形队列里的数据单元
 *  Created by miaopeng on 4-17
 */
public class LogicEvent implements Cleanable {

	private LogicEventType logicEventType;

	private ProtoEnum protoEnum;

	private Object proto;
	
	private Channel channel;

	private int protoLen;

	private long tickRecv;

	public LogicEventType getLogicEventType()
	{
		return logicEventType;
	}

	public void setLogicEventType(LogicEventType logicEventType)
	{
		this.logicEventType = logicEventType;
	}

	@Override
	public void clean() {
		logicEventType = null;
		proto = null;
	}

	public ProtoEnum getProtoEnum()
	{
		return protoEnum;
	}

	public void setProtoEnum(ProtoEnum protoEnum)
	{
		this.protoEnum = protoEnum;
	}

	public Object getProto()
	{
		return proto;
	}

	public void setProto(Object proto)
	{
		this.proto = proto;
	}

	public Channel getChannel()
	{
		return channel;
	}

	public void setChannel(Channel channel)
	{
		this.channel = channel;
	}

	public int getProtoLen() {
		return protoLen;
	}

	public void setProtoLen(int protoLen) {
		this.protoLen = protoLen;
	}

	public long getTickRecv() {
		return tickRecv;
	}

	public void setTickRecv(long tickRecv) {
		this.tickRecv = tickRecv;
	}

}
