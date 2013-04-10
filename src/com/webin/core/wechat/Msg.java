package com.webin.core.wechat;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.webin.core.WebinUtil;

public abstract class Msg {
	// POST
	public static final String MSG_POST_TEXT = "text";
	public static final String MSG_POST_UNKNOW = "null";
	public static final String MSG_POST_NEWS = "news";
	public static final String MSG_POST_MUSIC = "music";
	// GET
	public static final String MSG_GET_TEXT = "text";
	public static final String MSG_GET_UNKNOW = "null";
	public static final String MSG_GET_IMAGE = "image";
	public static final String MSG_GET_LOCATION = "location";
	public static final String MSG_GET_EVENT = "event";
	public static final String MSG_GET_LINK = "link";
	
	@XStreamAlias("MsgType")
	protected String MsgType = MSG_POST_UNKNOW;
	@XStreamAlias("CreateTime")
	protected String CreateTime;
	@XStreamAlias("FromUserName")
	protected String FromUserName;
	@XStreamAlias("ToUserName")
	protected String ToUserName;
	@XStreamAlias("FuncFlag")
	protected String FuncFlag;
	@XStreamAlias("MsgId")
	protected String MsgId;
	
	protected void setCreateTime(){
		this.CreateTime = WebinUtil.getTime();
	}

	protected void setFromUserName(String fromUserName) {
		this.FromUserName = fromUserName;
	}

	protected void setToUserName(String toUserName) {
		this.ToUserName = toUserName;
	}
	
	protected void setMsgType(String type){
		this.MsgType = type;
	}
	
	public void setFuncFlag(String flag) {
		this.FuncFlag = flag;
	}
	
	public String getToUserName(){
		return ToUserName;
	}
	
	public String getFromUserName(){
		return FromUserName;
	}
	
	public String getCreateTime(){
		return CreateTime;
	}
	
	public String getMsgId(){
		return MsgId;
	}
	
	public String getMsgType(){
		return MsgType;
	}
}
