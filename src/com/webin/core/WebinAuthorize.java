package com.webin.core;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;

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
		String string = null;
		try {
			string = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			string = byte2hexString(md.digest(string.getBytes()));
		} catch (Exception ex) {
		}
		return string;
	}

	public final String byte2hexString(byte[] bytes) {
		StringBuffer string = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				string.append("0");
			}
			string.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return string.toString().toUpperCase();
	}

	private boolean isAuthorize() {
		// 将token、timestamp、nonce三个参数进行字典序排序
		String[] arry = { TOKEN, timestamp, nonce };
		Arrays.sort(arry);
		// 将三个参数字符串拼接成一个字符串进行sha1加密
		String string = ArrayToString(arry);
		string = SHA1Encode(string);
		// 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		if (signature.toLowerCase().equals(string.toLowerCase())) {
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
		if (!checkParameters(req)) {
			writer.print("error=0");
		} else {
			if (isAuthorize()) {
				writer.print(echostr);
			} else {
				writer.print("error=1");
			}
		}
	}
}
