package bounce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class A3 extends JFrame {
	private static final long serialVersionUID = 1L;
	AnimationViewer viewer;	// panel for bouncing area
	private Font font = new Font("Sans Serif", Font.BOLD, 13);
		
	/** main method for A3
	 */
	public static void main(String[] args) {
		JFrame frame = new A3("Bouncing Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		frame.setLocation((d.width - frameSize.width) / 2, (d.height - frameSize.height) / 2);
		frame.setVisible(true);
	}

	/** Creates the main window with the specified size
	 * 	Also adds the tool/viewing panels to the window
	 */
	public A3(String title) {
		super(title);
		setSize(800, 700);
		viewer = new AnimationViewer();
		
		add(setUpToolsPanel(), BorderLayout.NORTH);
		add(viewer, BorderLayout.CENTER);
		add(setUpControlPanel(), BorderLayout.SOUTH);
	}

	/** Sets up the tools panel
	 * @return toolsPanel		the Panel
	 */
	public JPanel setUpToolsPanel() {
		//Set up the shape combo box
		ImageIcon rectangleButtonIcon = createImageIcon("rectangle.gif");
		ImageIcon ovalButtonIcon = createImageIcon("oval.gif");
		ImageIcon diamondButtonIcon = createImageIcon("diamond.gif");
		ImageIcon pacmanButtonIcon = createImageIcon("pacman.gif");
		
		// This adds more shapes to the "Shape" drop-down menu
		JComboBox<ImageIcon> shapesComboBox = new JComboBox<ImageIcon>(new ImageIcon[] {rectangleButtonIcon, ovalButtonIcon, diamondButtonIcon, pacmanButtonIcon} );
		shapesComboBox.setFont(font);
		shapesComboBox.setToolTipText("Set shape");
		shapesComboBox.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
				
				//set the default shape type based on the selection: 0 for Rectangle, 1 for oval etc
				viewer.setDefaultShapeType(cb.getSelectedIndex());
			}
		});
		
		//Set up the height TextField
		JTextField heightTxt = new JTextField("50");			// Default number for height in the textbox
		heightTxt.setFont(font);
		heightTxt.setToolTipText("Set Height");
		heightTxt.addFocusListener(new FocusListener() {		// Select all the text in the textbox when you click it
			public void focusLost(FocusEvent e) {}
			public void focusGained(FocusEvent e) {
				heightTxt.selectAll();
			}
		});
		heightTxt.addActionListener( new ActionListener() {		// Update the values entered in the textbox
			public void actionPerformed(ActionEvent e) {
				JTextField tf = (JTextField)e.getSource();
				
				try {
					int newValue = Integer.parseInt(tf.getText());
					if (newValue > 0)
						viewer.setHeight(newValue);
				} catch (Exception ex) {
					tf.setText("20");
				}
			}
		});
		
		//Set up the width TextField
		JTextField widthTxt = new JTextField("100");			// Default number for width in the textbox
		widthTxt.setFont(font);
		widthTxt.setToolTipText("Set Width");
		widthTxt.addFocusListener(new FocusListener() {			// Select all the text in the textbox when you click it
			public void focusLost(FocusEvent e) {}
			public void focusGained(FocusEvent e) {
				widthTxt.selectAll();
			}

		});
		widthTxt.addActionListener( new ActionListener() {		// Update the values entered in the textbox
			public void actionPerformed(ActionEvent e) {
				JTextField tf = (JTextField)e.getSource();
				
				try {
					int newValue = Integer.parseInt(tf.getText());
					if (newValue > 0)
						viewer.setWidth(newValue);
				} catch (Exception ex) {
					tf.setText("20");
				}
			}
		});
		
		// button for fillColor
		JButton fillButton = new JButton("Fill");
		fillButton.setFont(font);
		fillButton.setToolTipText("Set Fill Color");
		fillButton.setForeground(Color.blue);
		fillButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e) {
				Color newColor = JColorChooser.showDialog(viewer, "Fill Color", Color.black);
				
				if(newColor != null) {						// If the user does select a color
					viewer.setFillColor(newColor);			// then change the fill color of the shape
				}
			}
		});
		
		// button for borderColor
		JButton borderButton = new JButton("Border");
		borderButton.setFont(font);
		borderButton.setToolTipText("Set Border Color");
		borderButton.setForeground(Color.black);
		borderButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e) {
				Color newColor = JColorChooser.showDialog(viewer, "Border Color", Color.black);
				
				if(newColor != null) {					// If the user does select a color
					viewer.setBorderColor(newColor);	// then change the fill color of the shape
				}

			}
		});
		
		// Setting up the labels on the top tool panel
		JLabel shape = new JLabel(" Shape: ", JLabel.RIGHT);
		shape.setFont(font);
		shape.setForeground(Color.black);
		JLabel height = new JLabel("  Height: ", JLabel.RIGHT);
		height.setFont(font);
		height.setForeground(Color.black);
		JLabel width = new JLabel("  Width: ", JLabel.RIGHT);
		width.setFont(font);
		width.setForeground(Color.black);
		
		JPanel toolsPanel = new JPanel(new GridBagLayout());
		toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.X_AXIS));
		toolsPanel.add(shape);
		toolsPanel.add(shapesComboBox);
		toolsPanel.add(height);
		toolsPanel.add(heightTxt);
		toolsPanel.add(width);
		toolsPanel.add(widthTxt);
		toolsPanel.add(fillButton);
		toolsPanel.add(borderButton);
		
		return toolsPanel;
	}
	
	public JPanel setUpControlPanel() {
		// Creates buttons for starting and stopping the program 
		JButton startButton = new JButton(" Start ");
		startButton.setFont(font);
		startButton.setToolTipText("Start/resume the program");
		startButton.setForeground(Color.black);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewer.startTimer();
			}
		});
		
		JButton stopButton = new JButton(" Stop ");
		stopButton.setFont(font);
		stopButton.setToolTipText("Pause the program");
		stopButton.setForeground(Color.black);
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewer.stopTimer();
			}
		});
		
		// Creates a label and slider for adjusting the delay speed
		JLabel delayStatus = new JLabel("  FPS: " + 1000/viewer.getDelay(), JLabel.RIGHT);
		delayStatus.setFont(font);
		
		JSlider delaySlider = new JSlider(JSlider.HORIZONTAL, 10, 100, 1000/viewer.getDelay());
		delayStatus.setFont(font);
		delaySlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int sliderValue = ((JSlider)e.getSource()).getValue();
				
				delayStatus.setText("  FPS: " + sliderValue);
				viewer.setDelay(sliderValue);
			}
		});
		
		JPanel controlPanel = new JPanel(new GridBagLayout());
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		controlPanel.add(startButton);
		controlPanel.add(stopButton);
		controlPanel.add(delayStatus);
		controlPanel.add(delaySlider);
		
		return controlPanel;
	}

	/** creates the imageIcon
	 * @param	filename		the filename of the image
	 * @return ImageIcon		the imageIcon
	 */
	protected static ImageIcon createImageIcon(String filename) {
		java.net.URL imgURL = A3.class.getResource(filename);
		return new ImageIcon(imgURL);
	}
}



