package com.sinodata.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.sf.json.JSONObject;

import com.sinodata.tools.HttpRequest;
import com.sinodata.tools.JsonJiaJieMi;
import com.sinodata.tools.MD5Security;
import com.sinodata.tools.PropertiesUtil;

public class AccessPanel extends JPanel {
	private JPanel jp1,jp2,jp3,jp4,jp5;
    JLabel jp1_label1,jp1_label2,jp1_label3,jp1_label4;
    JTextField jp1_tf1,jp1_tf2,jp1_tf3,jp1_tf4;
    JLabel jp2_label1,jp2_label2;
    JLabel jp3_label1,jp3_label2;
    JLabel jp4_label1,jp4_label2,jp4_label3,jp4_label4;
    JTextArea jp4_ta1,jp4_ta2,jp4_ta3,jp4_ta4;
    JScrollPane jp4_jsp1,jp4_jsp2,jp4_jsp3,jp4_jsp4;
    JButton jp5_b1,jp5_b2;
	List<JTextField> list_textField1,list_textField2,list_textField3,list_textField4;
    PropertiesUtil pu;
	public AccessPanel(){
		pu = new PropertiesUtil();
		lanchPanel1();
		lanchPanel2();
		lanchPanel3();
		lanchPanel4();
		lanchPanel5();
		add(jp1);
		add(jp2);
		add(jp3);
		add(jp4);
		add(jp5);
		addActionListener();//调用监听事件方法
		setLayout(null);
		jp1.setBounds(10, 10, 600, 75);
		jp2.setBounds(10, 95, 600, 160);
		jp3.setBounds(10, 250, 600, 160);
		jp4.setBounds(10, 420, 600, 320);
		jp5.setBounds(10, 750, 600, 30);
	}

	private void lanchPanel1(){
		jp1 = new JPanel();
		jp1_label1 = new JLabel("商家密钥：");
		jp1_label2 = new JLabel("商家ID：");
		jp1_label3 = new JLabel("接口请求地址中cmd:（例如queryCode）");
		jp1_label4 = new JLabel("服务器IP:PORT");
//		jp1_tf1 = new JTextField();
//		jp1_tf2 = new JTextField();
		jp1_tf1 = new JTextField(pu.read("config.properties", "pubSecretKey"));
		jp1_tf2 = new JTextField(pu.read("config.properties", "agentId"));
		jp1_tf3 = new JTextField("queryPrize");
		jp1_tf4 = new JTextField(pu.read("config.properties", "accessIpPort"));
		
		jp1.add(jp1_label4);
		jp1.add(jp1_tf4);
		jp1.add(jp1_label1);
		jp1.add(jp1_tf1);
		jp1.add(jp1_label2);
		jp1.add(jp1_tf2);
		jp1.add(jp1_label3);
		jp1.add(jp1_tf3);
		jp1.setBackground(Color.lightGray);
		jp1.setLayout(new GridLayout(4,2));
		
	}
	private void lanchPanel2(){
		jp2 = new JPanel();
		jp2_label1 = new JLabel("基本参数：");
		jp2_label2 = new JLabel();
		jp2.add(jp2_label1);
		jp2.add(jp2_label2);
		
		list_textField1 = new ArrayList<JTextField>();
		list_textField2 = new ArrayList<JTextField>();
		for (int i = 0; i < 6; i++) {
			list_textField1.add(new JTextField());
			jp2.add(list_textField1.get(i));
			list_textField2.add(new JTextField());
			jp2.add(list_textField2.get(i));
		}
		jp2.setBackground(Color.lightGray);
		jp2.setLayout(new GridLayout(7,2));
	}
	private void lanchPanel3(){
		jp3= new JPanel();
		jp3_label1 = new JLabel("业务参数：");
		jp3_label2 = new JLabel("ReqContent");
		jp3.add(jp3_label1);
		jp3.add(jp3_label2);
		list_textField3 = new ArrayList<JTextField>();
		list_textField4 = new ArrayList<JTextField>();
		for (int i = 0; i < 6; i++) {
			list_textField3.add(new JTextField());
			jp3.add(list_textField3.get(i));
			list_textField4.add(new JTextField());
			jp3.add(list_textField4.get(i));
		}
		jp3.setBackground(Color.lightGray);
		jp3.setLayout(new GridLayout(7,2));
	}
	private void lanchPanel4(){
		jp4 = new JPanel();
		jp4_label1 = new JLabel("请求加密前，paramcontent=");
		jp4_label2 = new JLabel("请求加密后，paramcontent=");
		jp4_label3 = new JLabel("响应解密前：");
		jp4_label4 = new JLabel("响应解密后：");
		jp4_ta1 = new JTextArea();
		jp4_ta2 = new JTextArea();
		jp4_ta3 = new JTextArea();
		jp4_ta4 = new JTextArea();
		jp4_ta1.setLineWrap(true); //设置自动换行
		jp4_ta2.setLineWrap(true); 
		jp4_ta3.setLineWrap(true); 
		jp4_ta4.setLineWrap(true); 
		jp4_jsp1 = new JScrollPane(jp4_ta1);//添加滚动条
		jp4_jsp2 = new JScrollPane(jp4_ta2);
		jp4_jsp3 = new JScrollPane(jp4_ta3);
		jp4_jsp4 = new JScrollPane(jp4_ta4);
		jp4_jsp1.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); //设置水平滚动条自动出现 
		jp4_jsp1.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); //设置垂直滚动条自动出现 
		jp4_jsp2.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		jp4_jsp2.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		jp4_jsp3.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		jp4_jsp3.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		jp4_jsp4.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		jp4_jsp4.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		jp4.add(jp4_label1);
		jp4.add(jp4_jsp1);
		jp4.add(jp4_label2);
		jp4.add(jp4_jsp2);
		jp4.add(jp4_label3);
		jp4.add(jp4_jsp3);
		jp4.add(jp4_label4);
		jp4.add(jp4_jsp4);
		jp4.setBackground(Color.lightGray);
		jp4.setLayout(new GridLayout(8,1));
	}
	private void lanchPanel5(){
		jp5 = new JPanel();
		jp5_b1 = new JButton("发送请求");
		jp5_b2 = new JButton("重置参数");
		jp5.add(jp5_b1);
		jp5.add(jp5_b2);
	}
	
	/**
	 * 组装好请求参数并返回
	 * @return 组装好的请求参数字符串
	 */
	private String getRequestJsonData(){
		JSONObject json_respContent = new JSONObject();//业务参数JSON
		JSONObject json_requestData = new JSONObject();//请求参数JSON，包含基本参数和业务参数
		
		for(int i=0;i<list_textField3.size();i++){
			String strTmp1 = list_textField3.get(i).getText();
			if(!strTmp1.equals("") && strTmp1 != null){
				json_respContent.put(strTmp1, list_textField4.get(i).getText());
			}
		}
		for(int j=0;j<list_textField1.size();j++){
			String strTmp2 = list_textField1.get(j).getText();
			if(!strTmp2.equals("") && strTmp2 != null){
				json_requestData.put(strTmp2, list_textField2.get(j).getText());
			}
		}
		json_requestData.put("ReqContent", json_respContent.toString());
		return json_requestData.toString();
	}
	/**
	 * 将请求数据进行3DES加密后返回byte[]
	 * @param str 未加密的原始请求数据
	 * @return 3DES加密后的byte[]
	 */
	public byte[] requestDataEncrypt(String str){
		try {
			return new JsonJiaJieMi(jp1_tf1.getText()).jiaMiNoBASE64(str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	/**
	 * 将响应数据进行3DES解密后返回字符串
	 * @param data 响应数据byte[]
	 * @return
	 */
	public String reponseDataDecrypt(byte[] data){
		try {
			return new JsonJiaJieMi(jp1_tf1.getText()).jieMiNoBASE64(data);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 获取请求URL地址
	 * @param str 已加密的请求数据字符串
	 * @return 返回请求URL地址，例如：http://remoteip:port/api/access/do?cmd=queryCode&partnerId={0}&hashType=md5&hash={1}
	 * 客户端需要在url中使用自己的partnerid替换”{0}”， 根据报文哈希算法计算出哈希结果替换”{1}”
	 */
	private String getRequestUrl(byte[] data ){
		String strTmp = jp1_tf4.getText() + "/api/access/do?cmd=" + jp1_tf3.getText() 
				+ "&partnerId=" + jp1_tf2.getText() + "&hashType=md5&hash=" + getHash(data);
		return strTmp;
	}
	
	/**
	 * 报文哈希算法
	 * @param data 已3DES加密的请求数据byte[]
	 * @return MD5加密后的hash值
	 */
	private String getHash(byte[] data) {
		try {
			return MD5Security.getMD5String(data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 发起HTTP请求，返回请求响应数据
	 * @param url
	 * @param data 
	 * @return 返回请求响应数据
	 */
	private byte[] sentHttpRequest(String url, byte[] data){
		return HttpRequest.sendPost(url, data);
	}
//	
//	/**
//	 * 调用发起HTTP请求方法，获取响应数据
//	 * @param url
//	 * @param param
//	 * @return 响应数据
//	 */
//	private String getHttpReponse(String url, String param){
//		return setHttpRequest(url, param);
//	}
	
	private void setArrayListTextFieldToNull() {
		for (int i = 0; i < 6; i++) {
			list_textField1.get(i).setText(null);
			list_textField2.get(i).setText(null);
			list_textField3.get(i).setText(null);
			list_textField4.get(i).setText(null);
		}
	}
	private void addActionListener(){
		jp5_b1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				jp4_ta1.setText(getRequestJsonData());
				byte[] requestByteData = requestDataEncrypt(jp4_ta1.getText());
				byte[] reponseByteData = sentHttpRequest(getRequestUrl(requestByteData),requestByteData );
				try {
					jp4_ta2.setText("只有3DES加密，没有BASE64转码" );
					jp4_ta3.setText("响应数据为byte[]");
					jp4_ta4.setText(reponseDataDecrypt(reponseByteData));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		jp5_b2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setArrayListTextFieldToNull();
			}
			
		});
	}
}
