package com.sinodata.gui;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class JTabbedPane4MainFrame extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane jTabbedPane = new JTabbedPane();// 存放选项卡的组件
	private String[] tabNames = {"统一账户平台、综合接入即开票（有BASE64）","综合接入电脑票（无BASE64）"};
	
	public JTabbedPane4MainFrame(){
		JPanel jPanelFirst = new UnityPanel();// 第一个标签下的JPanel
		jTabbedPane.addTab(tabNames[0],null,jPanelFirst,"first");// 加入第一个页面 
		jTabbedPane.setMnemonicAt(0, KeyEvent.VK_0);// 设置第一个位置的快捷键为0
		
		JPanel jPanelSecond = new AccessPanel();// 第二个标签下的JPanel 
		jTabbedPane.addTab(tabNames[1],null,jPanelSecond,"second");// 加入第二个页面 
		jTabbedPane.setMnemonicAt(1, KeyEvent.VK_1);// 设置第二个位置的快捷键为1
		
		setLayout(new GridLayout(1,1));//设置布局方式
		add(jTabbedPane);//将选项卡组件添加到JPanel中
	}
}
