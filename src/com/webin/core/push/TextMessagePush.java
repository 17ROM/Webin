package com.webin.core.push;

import com.webin.core.ErrorException;
import com.webin.core.pull.MessagePull;

public class TextMessagePush extends MessagePush {
	private String mContent;
	
	public TextMessagePush(MessagePull vMsgPull){
		super();
		setToUserName(vMsgPull.getToUserName());
		setFromUserName(vMsgPull.getFromUserName());
		setCreateTime();
		setMsgType(MSG_TEXT);
	}
	
	public void setContent(String context){
		this.mContent = context;
		set("Content", mContent);
	}
	
	@Override
	public String toString() {
		if (mContent == null) {
			ErrorException.NullPointer(mContent);
		}
		return super.toString();
	}
}
