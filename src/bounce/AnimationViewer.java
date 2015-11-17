package bounce;
import javax.swing.*;

import sprite.Sprite;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
/*
 *	======================================================================
 *	AnimationViewer.java : Moves shapes around on the screen
 *	It is the main drawing area where shapes are added and manipulated.
 *	It also contains a popup menu to clear all shapes.
 *	======================================================================
 */

public class AnimationViewer extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ArrayList<Sprite> shapes;		// the list to store all shapes
	private int defaultSpriteType = 0; 	// the default shape type
	private final int DELAY = 1000/30; 		// the default animation speed, DELAY = 1000 / FPS
	private JPopupMenu popup;				// popup menu
	private javax.swing.Timer timer;
	private int allSelected = 0;

	/** Constructor of the AnimationPanel
	 */
	public AnimationViewer() {
		timer = new javax.swing.Timer(DELAY, this);
		shapes = new ArrayList<Sprite>(); //create an ArrayList to store shapes
		popup = new JPopupMenu(); //create the popup menu
		makePopupMenu();
		timer.start();

		// add the mouse event to handle popup menu and create new shape
		addMouseListener( new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				maybeShowPopup(e);
			}

			public void mouseReleased(MouseEvent e) {
				maybeShowPopup(e);
			}

			private void maybeShowPopup(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
			
			public void mouseClicked( MouseEvent e ) {
				boolean found = false;
				for (Sprite s : shapes) {
					if ( s.contains( e.getPoint()) ) { // if the mousepoint is within a shape, then set the shape to be selected/deselected
						found = true;
						s.setSelected( ! s.isSelected() );
					 }
				}
				if (! found) createNewSprite(e.getX(), e.getY()); // if the mousepoint is not within a shape, then create a new one according to the mouse position
			}
		});
	}

	/**
	 * Notifies this AnimationViewer object of an ActionEvent.
	 */
	public void actionPerformed(ActionEvent e) {
		// Request that the AnimationPanel repaints itself.
		repaint();
	}

	/** creates a new shape
	 * @param x the x-coordinate of the mouse position
	 * @param y	the y-coordinate of the mouse position
	 */
	protected void createNewSprite(int x, int y) {
		// create a new shape dependent on all current properties and the mouse position
		switch (defaultSpriteType) {
			case 0: {
				shapes.add(new sprite.Shape(x, y, new sprite.Rectangle()));
				break;
			}
			case 1: {
				shapes.add(new sprite.Shape(x, y, new sprite.Oval()));
				break;
			}
			case 2: {
				shapes.add(new sprite.Shape(x, y, new sprite.Diamond()));
				break;
			}
			case 3: {
				shapes.add(new sprite.Shape(x, y, new sprite.Pacman()));
				break;
			}
		}
	}

	/** sets the default shape type
	 * @param s	the new shape type
	 */
	public void setDefaultShapeType(int s) {
		defaultSpriteType = s;
	}

	/** set the width for all currently selected shapes
	 * @param w	the new width value
	 */
	public void setWidth(int w) {
		for (Sprite s : shapes) {
			if (s.isSelected()) {
				s.setWidth(w);
			}
		}
	}
	
	/** set the height for all currently selected shapes
	 * @param w	the new width value
	 */
	public void setHeight(int h) {
		for (Sprite s : shapes) {
			if (s.isSelected()) {
				s.setHeight(h);
			}
		}
	}
	
	/** set the fill color for all currently selected shapes
	 * @param f the new fill color
	 */
	public void setFillColor(Color f) {
		for (Sprite s : shapes) {
			if(s.isSelected()) {
				s.setFillColor(f);
			}
		}
	}
	
	/** set the border color for all currently selected shapes
	 * @param b the new border color
	 */
	public void setBorderColor(Color b) {
		for (Sprite s : shapes) {
			if(s.isSelected()) {
				s.setStrokeColor(b);
			}
		}
	}
	
	/** set the delay value for rendering the animation panel
	 * @param d the new FPS the program should render
	 */
	public void setDelay(int d) {
		timer.setDelay(1000/d);
	}
	
	public int getDelay() {
		return timer.getDelay();
	}
	
	public void startTimer() {
		timer.start();
	}
	
	public void stopTimer() {
		timer.stop();
	}
	
	/** removes all shapes from our vector
	 */
	public void clearAllSprites() {
		shapes.clear();
	}
	
	/** selects all shapes from our vector
	 */
	public void selectAllSprites() {
		for(Sprite s: shapes) {
			s.setSelected(true);
		}
	}
	
	/** deselects all shapes from our vector
	 */
	public void deselectAllSprites() {
		for(Sprite s: shapes) {
			s.setSelected(false);
		}
	}
	
	/** creates the popup menu for our animation program
	 */
	protected void makePopupMenu() {
	// Clear all
		JMenuItem clearAll = new JMenuItem("Clear All");
		clearAll.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllSprites();
			}
		});
		
		JMenuItem selectAll = new JMenuItem("Select/deselect All");
		selectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(allSelected == 0) {
					selectAllSprites();
					allSelected = 1;
				} else {
					deselectAllSprites();
					allSelected = 0;
				}
			}
		});
		
		popup.add(selectAll);
		popup.add(clearAll);
	 }

	/**	updates the painting area
	 * @param g	the graphics control
	 */
	public void update(Graphics g){
		paint(g);
	}

	/**	moves and paints all shapes within the animation area
	 * @param g	the Graphics control
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Calculate bounds of animation screen area.
		int boundaryWidth = getSize().width;
		int boundaryHeight = getSize().height;

		for(Sprite s : shapes) {
			s.paint(g);
			s.move(boundaryWidth, boundaryHeight);
		}
	}
}

