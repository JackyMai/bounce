package sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public interface Shapeable {
	/**
	 * Moves this Shape object within the specified bounds. On hitting a
	 * boundary the Shape instance bounces off and back into the two-
	 * dimensional world.
	 * @param boundaryWidth width of two-dimensional world.
	 * @param boundaryHeight height of two-dimensional world.
	 */
	public void move(int boundaryWidth, int boundaryHeight);

	/**
	 * Method to be implemented by concrete subclasses to handle subclass
	 * specific painting.
	 * @param painter the Painter object used for drawing.
	 */
	public void paint(Graphics g);

	/** abstract contains method
	 * Returns whether the point p is inside the shape or not.
	 * @param p	the mouse point
	 */
	public boolean contains(Point p);

	/**
	 * Returns this Shape object's x position.
	 */
	public int x();

	/**
	 * Returns this Shape object's y position.
	 */
	public int y();

	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaX();

	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaY();

	/**
	 * Returns this Shape's width.
	 */
	public int width();

	/**
	 * Returns this Shape's height.
	 */
	public int height();

	/** Set the width of the shape.
	 * @param w 	the width value
	 */
	public void setWidth(int w);

	/** Set the height of the shape.
	 * @param h 	the height value
	 */
	public void setHeight(int h);

	public void setFillColor(Color f);

	public void setBorderColor(Color b);

	/** Return the selected property of the shape.
	 * @return the selected property
	 */
	public boolean isSelected();

	/** Set the selected property of the shape.
	 *  When the shape is selected, its handles are shown.
	 * @param s 	the selected value
	 */
	public void setSelected(boolean s);

	/** Draw the handles of the shape
	 * @param g 	the Graphics control
	 */
	public void drawHandles(Graphics g);

	/**
	 * Returns a String whose value is the fully qualified name of this class
	 * of object. E.g., when called on a RectangleShape instance, this method
	 * will return "bounce.RectangleShape".
	 */
	public String toString();

	public boolean equals(Object obj);

}