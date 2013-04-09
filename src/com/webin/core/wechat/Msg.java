package com.webin.core.wechat;

import java.io.InputStream;
import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class Msg {
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
	private String MsgType = MSG_POST_UNKNOW;
	@XStreamAlias("CreateTime")
	private String CreateTime;
	@XStreamAlias("FromUserName")
	private String FromUserName;
	@XStreamAlias("ToUserName")
	private String ToUserName;
	@XStreamAlias("FuncFlag")
	private String FuncFlag;
	@XStreamAlias("MsgId")
	private String MsgId;
	
	protected void setCreateTime(){
		this.CreateTime = String.valueOf(new Long(new Date().getTime()));
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
	
	public static Msg toBean(InputStream xml) {
		return MsgXml.toBean(xml, Msg.class);
	}
}
