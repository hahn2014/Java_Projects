package com.painter.menus;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.painter.draw.DrawPanel;
import com.painter.draw.shapes.Square;
import com.painter.listeners.DrawPanelListener;
import com.painter.main.MainFrame;

public class MainMenu extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	private MainFrame mf;
	
	//init menu file items
	private JMenuItem newAction = new JMenuItem("New");
	private JMenuItem openAction = new JMenuItem("Open File...");
	private JMenuItem saveAction = new JMenuItem("Save");
	private JMenuItem saveAsAction = new JMenuItem("Save As...");
	private JMenuItem printAction = new JMenuItem("Print");
	private JMenuItem exitAction = new JMenuItem("Exit");
	
	//init menu edit items
	private JMenuItem undoAction = new JMenuItem("Undo");
	private JMenuItem redoAction = new JMenuItem("Redo");
	private JMenuItem cutAction = new JMenuItem("Cut");
	private JMenuItem copyAction = new JMenuItem("Copy");
	private JMenuItem pasteAction = new JMenuItem("Paste");
	
	public MainMenu(MainFrame mf) {
		
		this.mf = mf;
		
		//init the menu tabs
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		
		//add to menu
		this.add(fileMenu);
		this.add(editMenu);
		
		//add menu items to menus
		//file
		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.addSeparator();
		fileMenu.add(saveAction);
		fileMenu.add(saveAsAction);
		fileMenu.addSeparator();
		fileMenu.add(printAction);
		fileMenu.addSeparator();
		fileMenu.add(exitAction);
		//edit
		editMenu.add(undoAction);
		editMenu.add(redoAction);
		editMenu.addSeparator();
		editMenu.add(cutAction);
		editMenu.add(copyAction);
		editMenu.add(pasteAction);
		
		initFileActions();
		initEditActions();
	}
	
	private void initFileActions() {
		newAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//create large square
				Square s = new Square(0, 0, mf.getWidth(), mf.getHeight(), Color.LIGHT_GRAY);
				DrawPanelListener.panel.setTmpShape(s);
			}
		});
		openAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		saveAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		saveAsAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DrawPanel p = mf.getDrawPanel();
				int w = p.getWidth();
				int h = p.getHeight();
				
				BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				Graphics2D g2d = bi.createGraphics();
				p.paint(g2d);
				
				JFileChooser chooser = new JFileChooser();
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG (.png)", "png"));
				chooser.setFileFilter(chooser.getChoosableFileFilters() [1]);
				
				int r = chooser.showSaveDialog(mf);
				
				if (r == JFileChooser.APPROVE_OPTION) {
					File output = new File(chooser.getSelectedFile() + ".png");
					mf.setTitle("Java Painter - " + chooser.getSelectedFile() + ".png");
					try {
						ImageIO.write(bi, ".png", output);
					} catch (IOException er) {
						er.printStackTrace();
					}
				} else {
					
				}
			}
		});
		printAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		exitAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); //will check if is saved later on
			}
		});
	}
	
	private void initEditActions() {
		undoAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		redoAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		cutAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		copyAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		pasteAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}