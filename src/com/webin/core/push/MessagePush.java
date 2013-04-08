package com.webin.core.push;

public abstract class MessagePush {
	public static final String MSG_TEXT = "text";
	public static final String MSG_UNKNOW = "null";
	public static final String MSG_NEWS = "news";
	public static final String MSG_MUSIC = "music";
	private String mMsgType = MSG_UNKNOW;

	private String mFromUserName;
	private String mToUserName;
	
	private MessagePushObj mMsgWeb;
	
	public MessagePush(){
		mMsgWeb = new MessagePushObj();
	}

	public void setFromUserName(String fromUserName) {
		this.mFromUserName = fromUserName;
		set("FromUserName", mFromUserName);
	}

	public void setToUserName(String toUserName) {
		this.mToUserName = toUserName;
		set("ToUserName", mToUserName);
	}
	
	protected void setMsgType(String type){
		this.mMsgType = type;
		set("MsgType", mMsgType);
	}
	
	protected void set(boolean format, String key, String value) {
		mMsgWeb.set(format, key, value);
	}
	
	protected void set(String key, String value) {
		set(true, key, value);
	}
	
	@Override
	public String toString() {
		return mMsgWeb.toString();
	}
}
