package sprite;

import java.awt.Graphics;

public class Rectangle implements PaintStrategy {
	@Override
	public void drawShape(Graphics g, int fX, int fY, int fWidth, int fHeight) {
		g.drawRect(fX,fY,fWidth,fHeight);
	}

	@Override
	public void fillShape(Graphics g, int fX, int fY, int fWidth, int fHeight) {
		g.fillRect(fX+1,fY+1,fWidth-1,fHeight-1);
	}
}
