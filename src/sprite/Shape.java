package sprite;
import java.awt.*;

public class Shape implements Sprite {
	// === Constants for default values. ===
	protected static final int DEFAULT_X_POS = 0;
	protected static final int DEFAULT_Y_POS = 0;
	protected static final int DEFAULT_DELTA_X = 5;
	protected static final int DEFAULT_DELTA_Y = 5;
	protected static final int DEFAULT_WIDTH = 35;
	protected static final int DEFAULT_HEIGHT = 45;
	protected static final Color DEFAULT_FILL = Color.blue;
	protected static final Color DEFAULT_BORDER = Color.black;
	// ===

	// === Instance variables, accessible by subclas1ses.
	protected int fX;
	protected int fY;
	protected int fDeltaX;
	protected int fDeltaY;
	protected int fWidth;
	protected int fHeight;
	protected Color fFill;
	protected Color fBorder;
	protected boolean fSelected = false;  // draw handles if selected
	protected PaintStrategy fPaintStrategy;
	
	/**
	 * Creates a Shape object with default values for instance variables.
	 */
	public Shape(PaintStrategy paintStrategy) {
		this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FILL, DEFAULT_BORDER, paintStrategy);
	}

	/**
	 * Creates a Shape object with a specified x and y position.
	 */
	public Shape(int x, int y, PaintStrategy paintStrategy) {
		this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FILL, DEFAULT_BORDER, paintStrategy);
	}

	/**
	 * Creates a Shape instance with specified x, y, deltaX and deltaY values.
	 * The Shape object is created with a default width and height.
	 */
	public Shape(int x, int y, int deltaX, int deltaY, PaintStrategy paintStrategy) {
		this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FILL, DEFAULT_BORDER, paintStrategy);
	}

	/**
	 * Creates a Shape instance with specified x, y, deltaX, deltaY, width and
	 * height values.
	 */
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height, Color fill, Color border, PaintStrategy paintStrategy) {
		if(paintStrategy instanceof Pacman) {
			int minFrame = Math.min(width, height);
			
			fWidth = minFrame;
			fHeight = minFrame;
		} else {
			fWidth = width;
			fHeight = height;
		}
		
		fX = x;
		fY = y;
		fDeltaX = deltaX;
		fDeltaY = deltaY;
		fFill = fill;
		fBorder = border;
		fPaintStrategy = paintStrategy;
	}

	/* (non-Javadoc)
	 * @see sprite.Shapeable#move(int, int)
	 */
	public void move(int boundaryWidth, int boundaryHeight ) {
		int nextX = fX + fDeltaX;
		int nextY = fY + fDeltaY;

		if (nextX <= 0) {
			nextX = 0;
			fDeltaX = -fDeltaX;
		} else if (nextX + fWidth >= boundaryWidth) {
			nextX = boundaryWidth - fWidth;
			fDeltaX = -fDeltaX;
		}

		if (nextY <= 0) {
			nextY = 0;
			fDeltaY = -fDeltaY;
		} else if (nextY + fHeight >= boundaryHeight) {
			nextY = boundaryHeight - fHeight;
			fDeltaY = -fDeltaY;
		}
		
		fX = nextX;
		fY = nextY;
	}

	public void paint(Graphics g) {
		g.setColor(fBorder);
		fPaintStrategy.drawShape(g,fX,fY,fWidth,fHeight);
		g.setColor(fFill);
		fPaintStrategy.fillShape(g,fX,fY,fWidth,fHeight);
		
		drawHandles(g);
	};
	
	public boolean contains(Point mousePt) {
		if(fPaintStrategy instanceof Rectangle) {
			return (fX <= mousePt.x && mousePt.x <= (fX+ fWidth + 1)  &&  fY <= mousePt.y && mousePt.y <= (fY + fHeight + 1));
		} else if (fPaintStrategy instanceof Oval || fPaintStrategy instanceof Pacman) {
			Point EndPt = new Point(fX + fWidth, fY + fHeight);
			
			double dx = (2 * mousePt.x - fX - EndPt.x) / (double) fWidth;
			double dy = (2 * mousePt.y - fY - EndPt.y) / (double) fHeight;
			
			return dx * dx + dy * dy < 1.0;
		} else {
			double dx = Math.abs(mousePt.x - (fX + fWidth/2));
			double dy = Math.abs(mousePt.y - (fY + fHeight/2));
			
			return (dx/(fWidth/2) + dy/(fHeight/2)) < 1.0;
		}
	};

	/* (non-Javadoc)
	 * @see sprite.Shapeable#x()
	 */
	public int getX() {
		return fX;
	}

	/* (non-Javadoc)
	 * @see sprite.Shapeable#y()
	 */
	public int getY() {
		return fY;
	}

	/* (non-Javadoc)
	 * @see sprite.Shapeable#deltaX()
	 */
	public int deltaX() {
		return fDeltaX;
	}

	/* (non-Javadoc)
	 * @see sprite.Shapeable#deltaY()
	 */
	public int deltaY() {
		return fDeltaY;
	}

	/* (non-Javadoc)
	 * @see sprite.Shapeable#width()
	 */
	public int getWidth() {
		return fWidth;
	}

	/* (non-Javadoc)
	 * @see sprite.Shapeable#height()
	 */
	public int getHeight() {
		return fHeight;
	}

	/* (non-Javadoc)
	 * @see sprite.Shapeable#setWidth(int)
	 */
	public void setWidth(int w) {
		if(fPaintStrategy instanceof Pacman) {
			fHeight = w;
		}
		
		fWidth = w;
	}

	/* (non-Javadoc)
	 * @see sprite.Shapeable#setHeight(int)
	 */
	public void setHeight(int h) {
		if(fPaintStrategy instanceof Pacman) {
			fHeight = h;
		}
		fHeight = h;
	}
	
	/* (non-Javadoc)
	 * @see sprite.Shapeable#setFillColor(java.awt.Color)
	 */
	public void setFillColor(Color f) {
		fFill = f;
	}
	
	public Color getFillColor() {
		return fFill;
	}
	
	/* (non-Javadoc)
	 * @see sprite.Shapeable#setBorderColor(java.awt.Color)
	 */
	public void setStrokeColor(Color b) {
		fBorder = b;
	}
	
	public Color getStrokeColor() {
		return fBorder;
	}

	/* (non-Javadoc)
	 * @see sprite.Shapeable#isSelected()
	 */
	public boolean isSelected() {
		return fSelected;
	}

	/* (non-Javadoc)
	 * @see sprite.Shapeable#setSelected(boolean)
	 */
	public void setSelected(boolean s) {
		fSelected = s;
	}

	/* (non-Javadoc)
	 * @see sprite.Shapeable#drawHandles(java.awt.Graphics)
	 */
	public void drawHandles(Graphics g) {
	// if the shape is selected, then draw the handles
		if (isSelected()) {
			g.setColor(Color.black);
			g.fillRect(fX -2, fY-2, 4, 4);
			g.fillRect(fX + fWidth -2, fY + fHeight -2, 4, 4);
			g.fillRect(fX -2, fY + fHeight -2, 4, 4);
			g.fillRect(fX + fWidth -2, fY-2, 4, 4);
		}
	}

	/* (non-Javadoc)
	 * @see sprite.Shapeable#toString()
	 */
	public String toString() {
		return "[" + this.getClass().getName() + "," + fX + "," + fY + "," + fWidth + "," + fHeight + "]";
	}
	
	/* (non-Javadoc)
	 * @see sprite.Shapeable#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (! (obj instanceof Sprite)) {
			return false;
		}
		
		Shape s = (Shape)obj;
		return fX == s.fX && fY == s.fY && fDeltaX == s.fDeltaX && fDeltaY == s.fDeltaY && fWidth == s.fWidth && fHeight == s.fHeight && fSelected == s.fSelected;
	}	
}
