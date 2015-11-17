package sprite;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Diamond implements PaintStrategy {
	private Polygon diamond;
	
	@Override
	public void drawShape(Graphics g, int fX, int fY, int fWidth, int fHeight) {
		Graphics2D g2d = (Graphics2D) g;
		
		int[] xPoints = {fX, fX+fWidth/2, fX+fWidth, fX+fWidth/2};
		int[] yPoints = {fY+fHeight/2, fY, fY+fHeight/2, fY+fHeight};
		
		diamond = new Polygon(xPoints, yPoints, xPoints.length);
		
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(diamond);
	}

	@Override
	public void fillShape(Graphics g, int fX, int fY, int fWidth, int fHeight) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.fill(diamond);
	}
}
