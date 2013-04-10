package com.webin.core.wechat;

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
	/**
	 * @see GET
	 */
	public static EventMsg toBean(String xml) {
		return MsgXml.toBean(xml, EventMsg.class);
	}
}
