package com.sinodata.gui;

import java.awt.GridLayout;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.sinodata.tools.PropertiesUtil;
import com.sinodata.tools.UtilsList;

import net.sf.json.JSONObject;

public class UnityPanelTwo extends JPanel {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	JLabel label1, label2;
	JTextField tf1, tf2;
	JRadioButton rb1, rb2;
	ButtonGroup group;
	PropertiesUtil pu;
	int count;
	ArrayList<TextField> al1;
	ArrayList<TextField> al2;

	public UnityPanelTwo() {
		pu = new PropertiesUtil();
		label1 = new JLabel("接口请求URL地址");
		label2 = new JLabel("agent_id");
		tf1 = new JTextField("请按接口实际填写，例如：/access/api/agent/login.action");
		tf2 = new JTextField(pu.read("config.properties", "agentId"));
		rb1 = new JRadioButton("使用公共密钥");
		rb2 = new JRadioButton("使用商家密钥");
		group = new ButtonGroup();
		group.add(rb1);
		group.add(rb2);
		rb1.setSelected(true);

		al1 = new ArrayList<TextField>();
		al2 = new ArrayList<TextField>();
		
		count = Integer.parseInt(pu.read("config.properties", "paramNumber"));
		for (int i = 0; i < count; i++) {
			al1.add(new TextField());
			al2.add(new TextField());
		}
		lanchPanel();
	}

	private void lanchPanel() {
		add(rb1);
		add(rb2);
		add(label1);
		add(tf1);
		add(label2);
		add(tf2);

		for (int i = 0; i < count; i++) {
			add(al1.get(i));
			add(al2.get(i));
		}
		setLayout(new GridLayout(count + 3, 2));
		// setBounds(0,100,200,400);
		validate();

	}

	String getArrayListTextFieldToString() {
		JSONObject jo = new JSONObject();
		for (int i = 0; i < count; i++) {
			String key = al1.get(i).getText();
			String value = al2.get(i).getText();
			if (!"".equals(key)) {
				jo.put(key, value);
			}
		}
		//统一账户平台的接入使用
//		if (jo.has("checkcode") && "".equals(jo.get("checkcode"))) {
//			String stmp = UtilsList.getCheckCode(jo.getString("runcode")
//					+ tf2.getText() + jo.getString("userid")
//					+ jo.getString("codeinfo") + jo.getString("totalmoney")
//					+ jo.getString("loginsession"));
//			jo.put("checkcode", stmp);
//		}
		
		//即开票的接入使用
		if (jo.has("check_code") && "".equals(jo.get("check_code"))) {
			String stmp = UtilsList.getCheckCode(jo.getString("run_code") + "|"
					+ jo.getString("station_id") + "|"
					+ jo.getString("payment_amount") + "|" + jo.getString("payment_date") + "|"
					+ jo.getString("payment_time") + "|" );
			jo.put("check_code", stmp);
		}
		return jo.toString();
	}

	void setArrayListTextFieldToNull() {
		for (int i = 0; i < count; i++) {
			al1.get(i).setText(null);
			al2.get(i).setText(null);
		}
	}

}
