package com.sinodata.gui;


import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sinodata.tools.PropertiesUtil;

public class UnityPanelOne extends JPanel{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JLabel label1,label2,label3;
    JTextField tf1,tf2,tf3;
    PropertiesUtil pu;

    public UnityPanelOne(){
        label1 = new JLabel("公共密钥");
        label2 = new JLabel("商家密钥");
        label3 = new JLabel("服务器IP:PORT");
        
        pu = new PropertiesUtil();
        tf1 = new JTextField(pu.read("config.properties", "pubSecretKey"));
        tf2 = new JTextField(pu.read("config.properties", "agentSecretKey"));
        tf3 = new JTextField(pu.read("config.properties", "accessIpPort"));

        lanchPanel();
    }

    private void lanchPanel() {
        add(label3);
        add(tf3);
        add(label1);
        add(tf1);
        add(label2);
        add(tf2);
        setLayout(new GridLayout(3,2));
        validate();
        setBounds(0,0,100,10);
    }

}
