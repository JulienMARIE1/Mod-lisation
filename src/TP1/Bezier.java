package TP1;

import java.awt.Point;
import java.util.ArrayList;

public class Bezier {
	private ArrayList<Point> m_Points;
	private ArrayList<Point> m_BezierPoints;
	public Bezier (ArrayList<Point> Points) {
		m_Points = new ArrayList<>(Points);
		m_BezierPoints = new ArrayList<>(m_Points.size() * m_Points.size() );
	}

	public Point calculBezier () {
		float t;
		Point pointRecup = null;
		for (int i = 0; i < m_Points.size() * m_Points.size(); ++i)
			m_BezierPoints.add(null);
		for (int j = 0;  j < m_Points.size(); ++j) {
			m_BezierPoints.set(j*m_Points.size(), m_Points.get(j));
		}
		Point tmp1 = null;
		Point tmp2;
		for (t = 0; t <= 1; t += 0.1) {
			
			for (int i = 1; i < m_Points.size(); ++i) {
				for (int j = 0; j < m_Points.size() - i; ++j) {
					tmp1 = new Point((int)((1-t)*m_BezierPoints.get((i-1)+j * m_Points.size()).getX()), (int)((1-t)*m_BezierPoints.get((i-1)+j * m_Points.size()).getY()));
					tmp2 = new Point((int)((t)*m_BezierPoints.get((i-1)+(j+1) * m_Points.size()).getX()), (int)((t)*m_BezierPoints.get((i-1)+(j+1) *m_Points.size() ).getY()));
					tmp1 = new Point (tmp1.x + tmp2.x, tmp1.y + tmp2.y);
					m_BezierPoints.set(i + j * m_Points.size(), tmp1);
				}
			}
			pointRecup = m_BezierPoints.get(m_Points.size()-1);
		}
		return pointRecup;
	}
	public ArrayList<Point> getBezierPoint(){
		return m_BezierPoints;
	}
	
}
