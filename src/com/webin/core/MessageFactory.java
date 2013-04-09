package com.webin.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import com.webin.core.pull.EventMessagePull;
import com.webin.core.pull.ImageMessagePull;
import com.webin.core.pull.LinkMessagePull;
import com.webin.core.pull.LocationMessagePull;
import com.webin.core.pull.MessagePull;
import com.webin.core.pull.MessagePullObj;
import com.webin.core.pull.TextMessagePull;
import com.webin.core.push.ImgTextMessagePush;
import com.webin.core.push.MessagePush;
import com.webin.core.push.MusicMessagePush;
import com.webin.core.push.TextMessagePush;

public class MessageFactory extends Thread{
	private static MessageFactory _inst = null;
	private MessagePullObj mPullObj;
	
	private MessageFactory(){
		mPullObj = new MessagePullObj();
	}

	public static MessageFactory getInstance() {
		if (_inst==null){
			_inst = new MessageFactory();
		}
		return _inst;
	}
	
	public synchronized void handleMessage(InputStream inputStream, final PrintWriter writer) throws IOException {
		if (mPullObj != null) {
			mPullObj.handleInputStream(inputStream);
			MessagePull msgPull = classifyMessage(mPullObj);
			if (msgPull != null) {
				WebinLog.D(msgPull.toString());
				HandleAutoReplay(msgPull, writer);
			}
		}
	}
	
	private synchronized MessagePull classifyMessage(MessagePullObj msgobj){
		MessagePull vMsgPull = null;
		String vMsgType = mPullObj.get("MsgType");
		if (MessagePull.MSG_TEXT.equals(vMsgType)) {
			vMsgPull = new TextMessagePull(mPullObj);
		} else if (MessagePull.MSG_LOCATION.equals(vMsgType)) {
			vMsgPull = new LocationMessagePull(mPullObj);
		} else if (MessagePull.MSG_LINK.equals(vMsgType)) {
			vMsgPull = new LinkMessagePull(mPullObj);
		} else if (MessagePull.MSG_IMAGE.equals(vMsgType)) {
			vMsgPull = new ImageMessagePull(mPullObj);
		} else if (MessagePull.MSG_EVENT.equals(vMsgType)) {
			vMsgPull = new EventMessagePull(mPullObj);
		}
		return vMsgPull;
	}
	
	private void HandleAutoReplay(MessagePull vMsgPull, PrintWriter writer) throws IOException {
		TextMessagePush msg = (TextMessagePush) makeMessagePush(vMsgPull, MessagePush.MSG_TEXT);
		msg.setContent("»¶Ó­¹Ø×¢");
		msg.setFuncFlag("1");
		writer.print(msg);
		WebinLog.D(msg.toString());
	}
	
	private MessagePush makeMessagePush(MessagePull vMsgPull, String vMsgType){
		MessagePush vMsgPush = null;
		if (MessagePush.MSG_TEXT.equals(vMsgType)){
			vMsgPush = new TextMessagePush(vMsgPull);
		}else if (MessagePush.MSG_NEWS.equals(vMsgType)){
			vMsgPush = new ImgTextMessagePush(vMsgPull);
		}else if (MessagePush.MSG_MUSIC.equals(vMsgType)){
			vMsgPush = new MusicMessagePush(vMsgPull);
		}
		return vMsgPush;
	}
}
