package com.sinodata.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
	public static PropertiesUtil pu = new PropertiesUtil();
	
	/**
	 * 发送HTTP POST请求并返回响应内容
	 * @param url 接口后半段URL地址，例如：/access/api/login.action?
	 * @param param 经过3DES加密并BASE64编码转化后的字符串
	 * @return 请求响应内容
	 */
	public static String sendPost(String url,String param){
		try{
			String accessIpPort = pu.read("config.properties", "accessIpPort");
			URL urls = new URL("http://" + accessIpPort + url);
			HttpURLConnection uc = (HttpURLConnection) urls.openConnection();
			uc.setRequestMethod("POST");
			uc.setRequestProperty("content-type", "multipart/form-data");
			uc.setRequestProperty("charset", "UTF-8");
			uc.setDoOutput(true);
			uc.setDoInput(true);
			
			uc.setUseCaches(false);
			uc.setReadTimeout(10000);
			uc.setConnectTimeout(10000);
			OutputStream os = uc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.write((param).getBytes("utf-8"));
			dos.flush();
			os.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
			String readLine = "";
			StringBuffer sb = new StringBuffer();
			while ((readLine = in.readLine()) != null)
			{
				sb.append(readLine);
			}
			in.close();
			return sb.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 发送HTTP POST请求并返回响应内容
	 * @param url 接口后半段URL地址，例如：/api/access/do?cmd=queryPrize&partnerId={0}&hashType=md5&hash={1}
	 * @param data 经过3DES加密后的byte[]
	 * @return 响应内容byte[]
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] sendPost(String url,byte[] data){
		try{
			String accessIpPort = pu.read("config.properties", "accessIpPort");
			URL urls = new URL("http://" + accessIpPort + url);
			HttpURLConnection uc = (HttpURLConnection) urls.openConnection();
			uc.setRequestMethod("POST");
			uc.setRequestProperty("content-type", "multipart/form-data");
			uc.setRequestProperty("charset", "UTF-8");
			uc.setDoOutput(true);
			uc.setDoInput(true);
			
			uc.setUseCaches(false);
			uc.setReadTimeout(10000);
			uc.setConnectTimeout(10000);
			OutputStream os = uc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.write(data);
			dos.flush();
			os.close();
			
			InputStream inStrm = uc.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(inStrm);
			ByteArrayOutputStream bAOutputStream = new ByteArrayOutputStream();
			int ch;
			while ((ch = bis.read()) != -1) {
				bAOutputStream.write(ch);
			}
			 return bAOutputStream.toByteArray();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}