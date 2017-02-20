package com.sinodata.tools;

import java.io.UnsupportedEncodingException;

public class JsonJiaJieMi {
	private String secretKey;
	
	public JsonJiaJieMi(String secretKey){
		this.secretKey = secretKey;
	}
	public String jiaMi(String str)
			throws UnsupportedEncodingException, Exception {
		String sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(
				str.getBytes("UTF-8"), secretKey));
		return sendTmp;
	}

	public String jieMi(String str)
			throws UnsupportedEncodingException, Exception {
		String data = new String(ThreeDES.decryptMode(
				ThreeDES.decryptBASE64(str), secretKey), "UTF-8");
		return data;
	}
	
	public byte[] jiaMiNoBASE64(String str) throws UnsupportedEncodingException,Exception {
		byte[] b = ThreeDES.encryptMode(str.getBytes("UTF-8"),secretKey);
		return b;
	}
	
	public String jieMiNoBASE64(byte[] repData) throws UnsupportedEncodingException,Exception {
		String data = new String(ThreeDES.decryptMode(repData,secretKey),"UTF-8");
		return data;
	}

	public static void main(String[] args) {
		JsonJiaJieMi jjj = new JsonJiaJieMi("C3F1E6B25312648DDF122DB8");
		String str1 = "\"userid\":\"3760100874469\",\"runcode\":\"UZT_20150720094605_00003036\",\"beginorder\":\"1\",\"endorder\":\"1\",\"currentrow\":\"0\",\"rowcount\":\"100\"";
		String str2 = "EHbQZPLS8RybKnTYonWDAPvMWr+urmWD6XMT3QooFFWzLPEJn0eQgoTvT8r47ybRyfzxS/Xb6r3dJEgU18fpt7ZqoLQf1lEfK+mi7szOFkNEvGQG2OruCgXsWwT5dHg3YzL8nz7YpMh1TwYOid0v++QU5pKaoAA4HorvFHocaGo=";
		try {
			System.out.println("发送加密前 = " + str1);
			System.out.println("发送加密后 = " + jjj.jiaMi(str1));
			System.out.println("响应解密前 = " + str2);
			System.out.println("响应解密后 = " + jjj.jieMi(str2));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
