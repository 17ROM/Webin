package com.webin.core.push;

import com.webin.core.ErrorException;

public class TextMessagePush extends MessagePush {
	private String mContent;
	
	public TextMessagePush(){
		super();
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
