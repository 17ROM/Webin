package com.webin.core.wechat;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class EventMsg extends Msg {
	public static final String EVENT_SUBSCRIBE = "subscribe";
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
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
	
	public boolean isEvent(String event) {
		return event.equals(Event);
	}
	/**
	 * @see GET
	 */
	public static EventMsg toBean(String xml) {
		return MsgXml.toBean(xml, EventMsg.class);
	}
}
