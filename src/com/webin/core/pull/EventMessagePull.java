package com.webin.core.pull;

public class EventMessagePull extends MessagePull {
	private String mEvent;
	private String mEventKey;

	public EventMessagePull(MessagePullObj msg) {
		super(msg);
		formatMessageWeb(msg);
	}
	
	private void formatMessageWeb(MessagePullObj msg){
		setEvent(msg.get("Event"));
		setEventKey(msg.get("EventKey"));
	}
	
	public String getEvent() {
		return mEvent;
	}

	public void setEvent(String event) {
		this.mEvent = event;
	}

	public String getEventKey() {
		return mEventKey;
	}

	public void setEventKey(String eventKey) {
		this.mEventKey = eventKey;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(super.toString());
		result.append("[Event=" + mEvent + "]\n");
		result.append("[EventKey=" + mEventKey + "]\n");
		return result.toString();
	}

}
