package com.sinodata.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jTabbedPane = new JTabbedPane4MainFrame();
	private JMenuBar menuBar;
	private JMenu jmenuFile;
	private JMenuItem open,quit;
	MainFrame() {
		super("接入测试工具");
		menuBar = new JMenuBar();
		jmenuFile = new JMenu("配置文件(F)");
		// 设置助记符为F，按下ALT + F 可以触发该菜单
		jmenuFile.setMnemonic('F');

		open = new JMenuItem("打开");
		quit = new JMenuItem("退出");

		jmenuFile.add(open);
		// 设置菜单分隔符
		jmenuFile.addSeparator();
		jmenuFile.add(quit);

		menuBar.add(jmenuFile);

		// 设置菜单栏，使用这种方式设置菜单栏可以不占用布局空间
		setJMenuBar(menuBar);

		add(jTabbedPane);
		setBounds(100, 50, 640, 870);
		setVisible(true);
		addWindowListener(new WindowsMonitor());
	}

	class WindowsMonitor extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent arg0) {
			System.out.println("window is exiting...");
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		// 使用一个标准的主函数，即SwingUtilities的函数invokeLater来保证GUI在事件分发线程中创建
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}
}
