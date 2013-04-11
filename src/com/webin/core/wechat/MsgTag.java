package com.webin.core.wechat;

import java.io.InputStream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class MsgTag{
	// custom msg
	@XStreamAlias("ToUserName")
	public String ToUserName;
	@XStreamAlias("FromUserName")
	public String FromUserName;
	@XStreamAlias("CreateTime")
	public String CreateTime;
	@XStreamAlias("MsgType")
	public String MsgType;
	@XStreamAlias("MsgId")
	public String MsgId;
	
	// text msg
	@XStreamAlias("Content")
	public String Content;
	
	// pic msg
	@XStreamAlias("PicUrl")
	private String PicUrl;
	
	// location msg
	@XStreamAlias("Location_X")
	public String Location_X;
	@XStreamAlias("Location_Y")
	public String Location_Y;
	@XStreamAlias("Scale")
	public String Scale;
	@XStreamAlias("Label")
	public String Label;
	
	// link
	@XStreamAlias("Title")
	public String Title;
	@XStreamAlias("Description")
	public String Description;
	@XStreamAlias("Url")
	public String Url;
	
	// event
	@XStreamAlias("Event")
	public String Event;
	@XStreamAlias("EventKey")
	public String EventKey;

	public static MsgTag toBean(InputStream xml) {
		return MsgXml.toBean(xml, MsgTag.class);
	}
	
	public static MsgTag toBean(String xml) {
		return MsgXml.toBean(xml, MsgTag.class);
	}
	
	public String toXML(){
		return MsgXml.toXML(this);
	}
	
	private TextMsg makeTextMsg(){
		return TextMsg.toBean(toXML());
	}
	
	private LocationMsg makeLocationMsg() {
		return LocationMsg.toBean(toXML());
	}
	
	private EventMsg makeEventMsg() {
		return EventMsg.toBean(toXML());
	}

	private ImgMsg makeImagetMsg() {
		return ImgMsg.toBean(toXML());
	}
	
	private LinkMsg makeLinkMsg() {
		return LinkMsg.toBean(toXML());
	}

	public Msg getMsg() {
		Msg msg = null;
		if (Msg.MSG_GET_TEXT.equals(MsgType)) {
			msg = makeTextMsg();
		} else if (Msg.MSG_GET_LOCATION.equals(MsgType)) {
			msg = makeLocationMsg();
		} else if (Msg.MSG_GET_LINK.equals(MsgType)) {
			msg = makeLinkMsg();
		} else if (Msg.MSG_GET_IMAGE.equals(MsgType)) {
			msg = makeImagetMsg();
		} else if (Msg.MSG_GET_EVENT.equals(MsgType)) {
			msg = makeEventMsg();
		}
		return msg;
	}
	
	public <T> T toMsg() {
		return (T) getMsg();
	}
}
