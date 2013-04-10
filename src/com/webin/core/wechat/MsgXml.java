package com.webin.core.wechat;

import java.io.InputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class MsgXml {
	public static String toXML(Object obj) {
		XStream xstream = new XStream(new DomDriver("utf-8"));
		xstream.processAnnotations(obj.getClass());
		return xstream.toXML(obj);
	}

	public static <T> T toBean(InputStream xml, Class<T> cls) {
		XStream xstream = new XStream(new DomDriver("utf-8"));
		xstream.processAnnotations(cls);
		T obj = (T) xstream.fromXML(xml);
		return obj;
	}
	
	public static <T> T toBean(String xml, Class<T> cls) {
		XStream xstream = new XStream(new DomDriver("utf-8"));
		xstream.processAnnotations(cls);
		T obj = (T) xstream.fromXML(xml);
		return obj;
	}
}
