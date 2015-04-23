package com.painter.menus;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import com.painter.staticComponents.StaticVariables;

public class MainToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> combo;
	private JLabel comboLabel = new JLabel("Tool: ");
	
	private JLabel colorChoser = new JLabel("Color: ");
	private JButton colorButton = new JButton("      ");
	
	private Color c = StaticVariables.shapeColor;
	
	public MainToolBar() {
		combo = new JComboBox<>();
		combo.addItem("Square");
		combo.addItem("Oval");
		combo.addItem("Pencil");
		combo.addItem("Line");
		combo.addItem("Fill");
		
		setOpaque(true);
		colorButton.setForeground(c);
		colorButton.setBackground(c);
		
		//action listener
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StaticVariables.shapeType = combo.getSelectedItem().toString();
			}
		});
		
		colorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c = JColorChooser.showDialog(MainToolBar.this,  "Custom Color", c);
				colorButton.setForeground(c);
				colorButton.setBackground(c);
				StaticVariables.shapeColor = c;
			}
		});
		
		//add
		this.add(comboLabel);
		this.add(combo);
		this.add(colorChoser);
		this.add(colorButton);
	}
}