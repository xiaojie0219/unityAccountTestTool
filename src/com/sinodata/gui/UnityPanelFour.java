package com.sinodata.gui;


import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UnityPanelFour extends JPanel{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JButton bt1,bt2,bt3;
    public UnityPanelFour(){
        bt1 = new JButton("发送请求");
        bt2 = new JButton("重置参数");
        lanchPanel();
    }

    private void lanchPanel() {
        add(bt1);
        add(bt2);
        
        setLayout(new FlowLayout());
        validate();
        setBounds(0,0,100,30);
    }


}
