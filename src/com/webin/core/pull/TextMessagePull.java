package com.webin.core.pull;

public class TextMessagePull extends MessagePull {
	
	private String mContent;
	
	public TextMessagePull(MessagePullObj msg){
		super(msg);
		formatMessageWeb(msg);
	}
	
	private void formatMessageWeb(MessagePullObj msg){
		setContent(msg.get("Content"));
	}
	
	private void setContent(String mContext){
		this.mContent = mContext;
	}
	
	public String getContent(){
		return mContent;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(super.toString());
		result.append("[Content=" + mContent + "]\n");
		return result.toString();
	}
}
