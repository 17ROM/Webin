/**
 * import java class
 * 
 * GET MSG
 *   - LocationMsg
 *   - TextMsg
 *   - ImgMsg
 *   - LinkMsg
 *   - EventMsg
 *   
 * POST MSG
 * 	 - TextMsg
 *   - MusicMsg
 *   - ImgMsg
 */
importPackage(Packages.com.webin.core.wechat);
//include("textchat.js");

/**
 * return system error
 * @param MsgTag
 * @returns XML
 */
function systemError(tag) {
	var msg = tag.getMsg();
	var replay = new TextMsg(msg);
	replay.setContent("!!!");
	return replay.toXML();
}

/**
 * return handle error
 * @param MsgTag
 * @returns XML
 */
function handleError(tag){
	var msg = tag.getMsg();
	var replay = new TextMsg(msg);
	replay.setContent("...");
	return replay.toXML();
}

/***
 * main handle message
 * @param MsgTag
 */
function handleWeChat(msg) {
	var tag = MsgTag.toBean(msg);
	if (tag == null) {
		return systemError(tag);
	}
	if (tag.isMsgType(Msg.MSG_GET_EVENT)){
		return handleEventMsg(tag);
	} else if (tag.isMsgType(Msg.MSG_GET_TEXT)) {
		return handleTextMsg(tag);
	} else {
		return handleError(tag);
	}
}

function handleEventMsg(tag) {
	var event = tag.getMsg();
	var replay = new TextMsg(event);
	if (event.isEvent(EventMsg.EVENT_SUBSCRIBE)){
		replay.setContent("��ӭ��ע");
	} else if(event.isEvent(EventMsg.EVENT_UNSUBSCRIBE)) {
		replay.setContent("�ٴι�ע");
	} else {
		replay.setContent("������");
	}
	return replay.toXML();
}

function handleTextMsg(tag){
	var txt = tag.getMsg();
	var replay = new TextMsg(txt);
	replay.setContent("Ȼ����");
	return replay.toXML();
}
