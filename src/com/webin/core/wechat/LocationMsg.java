package com.webin.core.wechat;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class LocationMsg extends Msg {
	@XStreamAlias("Location_X")
	private String Location_X;
	@XStreamAlias("Location_Y")
	private String Location_Y;
	@XStreamAlias("Scale")
	private String Scale;
	@XStreamAlias("Label")
	private String Label;
	
	public String getLocation_X() {
		return Location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public String getScale() {
		return Scale;
	}

	public String getLabel() {
		return Label;
	}
	/**
	 * @see GET
	 */
	public static LocationMsg toBean(String xml) {
		return MsgXml.toBean(xml, LocationMsg.class);
	}
}
