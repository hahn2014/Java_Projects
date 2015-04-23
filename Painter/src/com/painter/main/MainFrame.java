package com.painter.main;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.painter.draw.DrawPanel;
import com.painter.menus.MainMenu;
import com.painter.menus.MainToolBar;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final Dimension SIZE = new Dimension(800, 600);
	
	private DrawPanel panel = new DrawPanel();
	
	public MainFrame() {
		super("Java Painter - Untitled.jpg");
		setLayout(new BorderLayout());
		//add panel to frame
		add(panel, BorderLayout.CENTER);
		
		//add menu bar to top
		MainMenu topMenu = new MainMenu(this);
		setJMenuBar(topMenu);
		
		//add ToolBar
		MainToolBar topTool = new MainToolBar();
		add(topTool, BorderLayout.NORTH);
		
		//init shit
		setSize(SIZE);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		new MainFrame();
	}
	public DrawPanel getDrawPanel() {
		return panel;
	}
}