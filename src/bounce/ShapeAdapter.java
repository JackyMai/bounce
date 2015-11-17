package bounce;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import sprite.Shape;
import sprite.Shapeable;
import sprite.Sprite;

public class ShapeAdapter implements Shapeable {
	Sprite sprite;
	
	public ShapeAdapter(Sprite sprite) {
		this.sprite = sprite;
	}

	public void move(int boundaryWidth, int boundaryHeight) {
		sprite.move(boundaryWidth, boundaryHeight);
	}
	
	public void paint(Graphics g) {
		sprite.paint(g);
		
	}
	
	public boolean contains(Point p) {
		return sprite.contains(p);
	}
	
	public int x() {
		return sprite.getX();
	}
	
	public int y() {
		return sprite.getY();
	}
	
	public int deltaX() {
		return ((Shape)sprite).deltaX();
	}
	
	public int deltaY() {
		return ((Shape)sprite).deltaY();
	}
	
	public int width() {
		return sprite.getWidth();
	}
	
	public int height() {
		return sprite.getHeight();
	}
	
	public void setWidth(int w) {
		sprite.setWidth(w);
	}
	
	public void setHeight(int h) {
		sprite.setHeight(h);
	}

	public void setFillColor(Color f) {
		sprite.setFillColor(f);
	}
	
	public void setBorderColor(Color b) {
		sprite.setStrokeColor(b);
	}
	
	public boolean isSelected() {
		return sprite.isSelected();
	}

	public void setSelected(boolean s) {
		sprite.setSelected(s);
	}

	public void drawHandles(Graphics g) {
		sprite.drawHandles(g);
	}
	
	public boolean equals(Object obj) {
		if (! (obj instanceof ShapeAdapter)) {
			return false;
		}
		
		ShapeAdapter s = (ShapeAdapter)obj;
		return x() == s.x() && y() == s.y() && deltaX() == s.deltaX() && deltaY() == s.deltaY() && width() == s.width() && height() == s.height() && isSelected() == s.isSelected();
	}	
}
