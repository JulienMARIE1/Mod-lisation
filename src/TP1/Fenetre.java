package TP1;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Fenetre extends JFrame {
	public Fenetre (){
		super();
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		setTitle("Bezier");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width/2, height/2);
		setLocationRelativeTo(null);
	}

}
