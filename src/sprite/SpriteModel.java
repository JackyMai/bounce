package sprite;

import java.awt.Color;
import java.awt.Point;

public interface SpriteModel
{
	/**
	 * Customisable movement.
	 *
	 * @param bW
	 *            width of movement area
	 * @param bH
	 *            height of movement area
	 */
	public abstract void move(int bW, int bH);

	public abstract int getX();

	public abstract int getY();

	/**
	 * Customisable selection
	 *
	 * @param p
	 *            selection pointer (e.g. from a mouse-click event)
	 *
	 * @return true, if p is within the sprite's selection area.
	 */
	public abstract boolean contains(Point p);

	public abstract void setSelected(boolean s);

	public abstract boolean isSelected();

	/**
	 * Customisable size
	 */
	public abstract int getWidth();

	public abstract void setWidth(int w);

	public abstract int getHeight();

	public abstract void setHeight(int h);

	/**
	 * Customisable color
	 */
	public abstract Color getStrokeColor();

	public abstract void setStrokeColor(Color c);

	public abstract Color getFillColor();

	public abstract void setFillColor(Color c);
}