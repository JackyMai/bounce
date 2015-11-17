package sprite;

import java.awt.Graphics;

public class Oval implements PaintStrategy {
	@Override
	public void drawShape(Graphics g, int fX, int fY, int fWidth, int fHeight) {
		g.drawOval(fX,fY,fWidth,fHeight);
	}

	@Override
	public void fillShape(Graphics g, int fX, int fY, int fWidth, int fHeight) {
		g.fillOval(fX,fY,fWidth,fHeight);
	}

}
