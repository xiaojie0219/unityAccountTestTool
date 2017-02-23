package com.sinodata.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sinodata.tools.HttpRequest;
import com.sinodata.tools.JsonJiaJieMi;

public class UnityPanel extends JPanel{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    UnityPanelOne po;
    UnityPanelTwo pt;
    UnityPanelThree pte;
    UnityPanelFour pf;

    public UnityPanel(){
        po = new UnityPanelOne();
        pt = new UnityPanelTwo();
        pte = new UnityPanelThree();
        pf = new UnityPanelFour();

        lanchPanel();
    }
    public void lanchPanel(){
        add(po);
        add(pt);
        add(pte);
        add(pf);
        po.setBackground(Color.lightGray);
        pt.setBackground(Color.lightGray);
        pte.setBackground(Color.lightGray);
//        setLayout(new GridLayout(5,1));
        setLayout(null);
//        pack();
        po.setBounds(10,10,600,60);
        pt.setBounds(10, 70, 600, 370);
        pte.setBounds(10, 430, 600, 320);
        pf.setBounds(10, 760, 600, 30);
        
        pf.bt1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = pt.getArrayListTextFieldToString();
				pte.tf1.setText(str);//将组装后的参数内容显示到文本框
				
				String secretKey = getSecretKey();
				String paramcontent = getEncryptString(secretKey,str);
				pte.tf2.setText(paramcontent);//将参数加密后显示到文本框
				
				//发起HTTP请求，并获取响应内容
				String url = po.tf3.getText() + pt.tf1.getText();
				String param = pt.label2.getText() + "=" + pt.tf2.getText() + "&param_content=" + paramcontent;
				String response = HttpRequest.sendPost(url, param);
				pte.tf3.setText(response);//将未解密的响应内容显示到文本框
				pte.tf4.setText(getDecryptString(secretKey,response));//将解密后的响应内容显示到文本框
			}
        	
        });
        
        pf.bt2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pt.setArrayListTextFieldToNull();
			}
        });
    }
    public String getSecretKey(){
    	if(pt.rb1.isSelected()){
    		return po.tf1.getText();
    	}else if (pt.rb2.isSelected()){
    		return po.tf2.getText();
    	}else{
    		System.out.println("请填写正确的公钥或私钥。。。");
    		return null;
    	}
    }
    public String getEncryptString(String secretKey,String str){
    	JsonJiaJieMi jjj = new JsonJiaJieMi(secretKey);
		try {
			return jjj.jiaMi(str);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			return null;
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
    }
    public String getDecryptString(String secretKey,String str){
    	JsonJiaJieMi jjj = new JsonJiaJieMi(secretKey);
    	try {
			return jjj.jieMi(str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    	
    public static void main(String[] args){
        JFrame jf = new JFrame("test");
        jf.add(new UnityPanel());
        jf.setBounds(100,50,640,820);
        jf.setVisible(true);
    }
}

