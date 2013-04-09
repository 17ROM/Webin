package com.webin.core.wechat;

import java.io.InputStream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class EventMsg extends Msg {
	@XStreamAlias("Event")
	private String Event;
	@XStreamAlias("EventKey")
	private String EventKey;

	public String getEvent() {
		return Event;
	}

	public String getEventKey() {
		return EventKey;
	}
	
	public static EventMsg toBean(InputStream xml) {
		return MsgXml.toBean(xml, EventMsg.class);
	}
}
