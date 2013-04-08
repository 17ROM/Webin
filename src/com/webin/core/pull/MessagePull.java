package com.webin.core.pull;

import java.io.OutputStream;
import java.io.Writer;

public abstract class MessagePull {
	public static final String MSG_TEXT = "text";
	public static final String MSG_UNKNOW = "null";
	public static final String MSG_IMAGE = "image";
	public static final String MSG_LOCATION = "location";
	public static final String MSG_EVENT = "event";
	public static final String MSG_LINK = "link";
	
	private String mMsgType = MSG_UNKNOW;

	private String mCreateTime;
	private String mFromUserName;
	private String mToUserName;
	private String mMsgId;
	
	private MessagePullObj mMsgWeb;
	private Writer mWriter;
	
	public MessagePull(MessagePullObj msg){
		mMsgWeb = msg;
		formatMessageWeb(mMsgWeb);
	}
	
	private void formatMessageWeb(MessagePullObj msg){
		setMsgId(msg.get("MsgId"));
		setCreateTime(msg.get("CreateTime"));
		setFromUserName(msg.get("FromUserName"));
		setToUserName(msg.get("ToUserName"));
		setMsgType(msg.get("MsgType"));
	}
	
	public void setWriter(final Writer writer) {
		this.mWriter = writer;
	}
	
	private void setCreateTime(String createTime) {
		this.mCreateTime = createTime;
	}

	private void setFromUserName(String fromUserName) {
		this.mFromUserName = fromUserName;
	}
	
	private void setToUserName(String toUserName) {
		this.mToUserName = toUserName;
	}
	
	private void setMsgId(String msgId){
		this.mMsgId = msgId;
	}
	
	private void setMsgType(String type){
		this.mMsgType = type;
	}

	public String getToUserName(){
		return mToUserName;
	}
	
	public String getFromUserName(){
		return mFromUserName;
	}
	
	public String getCreateTime(){
		return mCreateTime;
	}
	
	public String getMsgId(){
		return mMsgId;
	}
	
	public String getMsgType(){
		return mMsgType;
	}
	
	public Writer getWriter() {
		return mWriter;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[MsgId=" + mMsgId + "]\n");
		result.append("[ToUserName=" + mToUserName + "]\n");
		result.append("[FromUserName=" + mFromUserName + "]\n");
		result.append("[CreateTime=" + mCreateTime + "]\n");
		result.append("[MsgType=" + mMsgType + "]\n");
		return result.toString();
	}
}
