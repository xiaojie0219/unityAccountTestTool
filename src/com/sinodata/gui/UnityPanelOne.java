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
    JLabel label1,label2;
    JTextField tf1,tf2;
    PropertiesUtil pu;

    public UnityPanelOne(){
        label1 = new JLabel("公共密钥");
        label2 = new JLabel("商家密钥");
        
        pu = new PropertiesUtil();
        tf1 = new JTextField(pu.read("config.properties", "pubSecretKey"));
        tf2 = new JTextField(pu.read("config.properties", "agentSecretKey"));

        lanchPanel();
    }

    private void lanchPanel() {
        add(label1);
        add(tf1);
        add(label2);
        add(tf2);
        setLayout(new GridLayout(2,2));
        validate();
        setBounds(0,0,100,10);
    }

}
