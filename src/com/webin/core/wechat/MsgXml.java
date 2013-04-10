package com.webin.core.wechat;

import java.io.InputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.webin.core.ErrorException;

public class MsgXml {
	public static String toXML(Object obj) {
		XStream xstream = new XStream(new DomDriver("utf-8"));
		xstream.processAnnotations(obj.getClass());
		return xstream.toXML(obj);
	}

	public static <T> T toBean(InputStream xml, Class<T> cls) {
		XStream xstream = new XStream(new DomDriver("utf-8"));
		xstream.processAnnotations(cls);
		T obj = null;
		try{
			obj = (T) xstream.fromXML(xml);
		}catch(Exception e){
			ErrorException.SAXParser(e);
		}
		return obj;
	}
	
	public static <T> T toBean(String xml, Class<T> cls) {
		XStream xstream = new XStream(new DomDriver("utf-8"));
		xstream.processAnnotations(cls);
		T obj = null;
		try{
			obj = (T) xstream.fromXML(xml);
		}catch(Exception e){
			ErrorException.SAXParser(e);
		}
		return obj;
	}
}
