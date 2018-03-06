package TP1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DessinPoint extends JPanel{
	private ArrayList<Point> m_Points;
	private ArrayList<Point> m_Bezier; 
	private Bezier b;
	public DessinPoint () {
		m_Points = new ArrayList<>();
		m_Bezier = new ArrayList<>();
		
		this.addMouseListener(new GestionPoint()); 
		this.addMouseMotionListener(new MouseMotion());
	}
	
	private class GestionPoint extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			boolean exist = false;
			for (Point point : m_Points) {
				if (point.x > e.getX() - 8 &&  point.x < e.getX() + 8 && 
					point.y > e.getY() - 8 && point.y < e.getY() + 8) {
					exist = true;
					break;
				} 				
			}
		 	if (!exist) {
		 		m_Points.add(new Point (e.getX(), e.getY()));
		 		b = new Bezier(m_Points);
				if (m_Points.size() > 0)
					m_Bezier.add(b.calculBezier());
		 	}

			repaint();
		}
		
	}
	private class MouseMotion extends MouseMotionAdapter{
		public void mouseDragged (MouseEvent e) {
			for (Point point : m_Points) {
				if (point.x > e.getX() - 8 &&  point.x < e.getX() + 8 && 
					point.y > e.getY() - 8 && point.y < e.getY() + 8) {
					point.setLocation(new Point(e.getX(), e.getY()));
					ArrayList<Point> tmp = new ArrayList<>(); 
					int cpt = 0;
					for (Point p : m_Points) {
						tmp.add(p);
						b = new Bezier(tmp);
						m_Bezier.set(cpt, b.calculBezier());
						++cpt;
					}
					break;
				} 
				
			}
			repaint();
		}
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
	
		g2.setColor(Color.black);
		for (Point p : m_Points) {
			g2.fill(new Rectangle2D.Double(p.getX(), p.getY(), 8, 8));
			g2.draw(new Rectangle2D.Double(p.getX(), p.getY(), 8, 8));
			
		}
		for (int i = 0; i < m_Points.size()-1; ++i) {
			g2.draw(new Line2D.Double(m_Points.get(i), m_Points.get(i+1)));
		}
		g2.setColor(Color.RED);
		for (Point p : m_Bezier) {
			if (p != null) {
				g2.fill(new Rectangle2D.Double(p.getX(), p.getY(), 8, 8));			

				g2.draw(new Rectangle2D.Double(p.getX(), p.getY(), 8, 8));			
			}
		}
		try {
			for (int i = 0; i < m_Bezier.size(); ++i) {
				if (m_Bezier.get(i) != null)
					g2.draw(new Line2D.Double(m_Bezier.get(i), m_Bezier.get(i+1)));
			}	
		} catch (Exception e) {
			
		}
		
	}
	
}
