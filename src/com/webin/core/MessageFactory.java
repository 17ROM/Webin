package com.webin.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

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
	//private Queue<MessagePull> mMsgPullList = new ConcurrentLinkedQueue<MessagePull>();
	
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
			MessagePull msgPull = classifyMessage(mPullObj, writer);
			if (msgPull != null) {
				//mMsgPullList.add(msgPull);
				HandleAutoReplay(msgPull);
			}
		}
	}
	
	private synchronized MessagePull classifyMessage(MessagePullObj msgobj, final PrintWriter writer){
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
		if (vMsgPull != null) {
			vMsgPull.setWriter(writer);
		}
		return vMsgPull;
	}
	
	private void HandleAutoReplay(MessagePull vMsgPull) throws IOException {
		Writer replay = vMsgPull.getWriter();
		TextMessagePush msg = (TextMessagePush)makeMessagePush(MessagePush.MSG_TEXT);
		msg.setContent("hello");
		replay.write(msg.toString());
		replay.flush();
		replay.close();
	}
	
	private MessagePush makeMessagePush(String vMsgType){
		MessagePush vMsgPush = null;
		if (MessagePush.MSG_TEXT.equals(vMsgType)){
			vMsgPush = new TextMessagePush();
		}else if (MessagePush.MSG_NEWS.equals(vMsgType)){
			vMsgPush = new ImgTextMessagePush();
		}else if (MessagePush.MSG_MUSIC.equals(vMsgType)){
			vMsgPush = new MusicMessagePush();
		}
		return vMsgPush;
	}
	/*
	public void run() {
		while (true) {
			if (!mMsgPullList.isEmpty()){
				MessagePull vMsgPull = mMsgPullList.element();
				HandleAutoReplay(vMsgPull);
				mMsgPullList.remove(vMsgPull);
			}
		}
	}*/
}
