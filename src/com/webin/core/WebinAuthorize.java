package com.webin.core;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class WebinAuthorize {
	private static WebinAuthorize _inst = null;
	private final String TOKEN = "Webin";
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;

	private WebinAuthorize() {
	}

	public static WebinAuthorize getInstance() {
		if (_inst == null) {
			_inst = new WebinAuthorize();
		}
		return _inst;
	}

	private String ArrayToString(String[] arr) {
		StringBuffer string = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			string.append(arr[i]);
		}
		return string.toString();
	}

	public String SHA1Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}

	private boolean isAuthorize(String signature, String timestamp, String nonce) {
		// ��token��timestamp��nonce�������������ֵ�������
		String[] arry = { TOKEN, timestamp, nonce };
		Arrays.sort(arry);
		// �����������ַ���ƴ�ӳ�һ���ַ�������sha1����
		String string = ArrayToString(arry);
		string = SHA1Encode(string);
		// �����߻�ü��ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��
		if (signature.equals(string)) {
			return true;
		}
		return false;
	}
	
	private boolean checkParameters(HttpServletRequest req) {
		signature = req.getParameter("signature");
		timestamp = req.getParameter("timestamp");
		nonce = req.getParameter("nonce");
		echostr = req.getParameter("echostr");
		if (signature != null && timestamp != null && nonce != null && echostr != null) {
			return true;
		}
		return false;
	}

	public void doAuthorize(HttpServletRequest req, PrintWriter writer) {
		if (checkParameters(req)){
			if (isAuthorize(signature, timestamp, nonce)) {
				writer.write(echostr);
				writer.flush();
				writer.close();
			}
		}
	}

}
