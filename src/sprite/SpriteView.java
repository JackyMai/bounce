package sprite;

import java.awt.Graphics;

public interface SpriteView
{
	/**
	 * Customisable appearance.
	 *
	 * @param g
	 *            The graphics Context.
	 */
	public abstract void paint(Graphics g);

	public abstract void drawHandles(Graphics g);
}