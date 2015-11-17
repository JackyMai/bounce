package sprite;

import java.awt.Graphics;

public interface PaintStrategy {
	public void drawShape(Graphics g, int fX, int fY, int fWidth, int fHeight);
	
	public void fillShape(Graphics g, int fX, int fY, int fWidth, int fHeight);
}
