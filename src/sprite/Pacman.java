package sprite;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

public class Pacman implements PaintStrategy {
	private Arc2D.Float pacman;
	private double angleStart = 45;
	private double angleExtent = 270;
	private int closing = 1;
	
	@Override
	public void drawShape(Graphics g, int fX, int fY, int fWidth, int fHeight) {
		Graphics2D g2d = (Graphics2D) g;
		pacman = new Arc2D.Float(Arc2D.PIE);
		
		int frame = Math.min(fWidth, fHeight);
		
		pacman.setArc(fX, fY, frame, frame, angleStart, angleExtent, Arc2D.PIE);
		
		// If the Pacman is in a closing state, decrease angleStart and increase
		// angleExtend, else, do the opposite
		if(closing == 1) {
			angleStart--;
			angleExtent+=2;
		} else {
			angleStart++;
			angleExtent-=2;
		}
		
		// If the angleStart value is 0 (or lower), then Pacman should now open
		// its mouth. Else if angleStart equals to the default angle of 45 degress,
		// then Pacman should now close its mouth.
		if(angleStart <= 0) {
			closing = 0;
		} else if(angleStart >= 45) {
			closing = 1;
		}
		
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(pacman);
	}

	@Override
	public void fillShape(Graphics g, int fX, int fY, int fWidth, int fHeight) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.fill(pacman);
	}

}
