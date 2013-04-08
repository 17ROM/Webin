package com.webin.core.pull;

import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.webin.core.ErrorException;

public class MessagePullObj extends DefaultHandler {
	private HashMap<String, String> mHashMap = new HashMap<String, String>();
	private SAXParserFactory mFactory = SAXParserFactory.newInstance();
	private InputStream mContext;
	private SAXParser mParser;
	private String mXmlKey;

	public MessagePullObj(){
	}
	
	public void handleInputStream(InputStream inputStream){
		mContext = inputStream;
		try {
			mParser = mFactory.newSAXParser();
			mParser.parse(mContext, this);
		} catch (Exception e) {
			ErrorException.SAXParser(e);
		}
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (!"xml".equals(qName)) {
			mXmlKey = qName;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (mXmlKey != null) {
			String mXmlValue = new String(ch, start, length);
			if (!mHashMap.containsKey(mXmlKey)) {
				mHashMap.put(mXmlKey, mXmlValue);
			}
		}
	}
	
	public String get(String key){
		return mHashMap.get(key);
	}
}
