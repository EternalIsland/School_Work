/**
 * A class containing the parameters 
 * of the two squares to be drawn.
 * 
 * @author Matt Hynes
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class TwoSquareComponent extends JPanel{
	private Rectangle2D square1 = new Rectangle2D.Double(30.0, 80.0, 100.0, 100.0);
	private Rectangle2D square2 = new Rectangle2D.Double(150.0, 80.0, 100.0, 100.0);
	
	public TwoSquareComponent(){
		setPreferredSize( new Dimension( 250, 150 ) );
		setBackground( Color.white );
	}
	
	 protected void paintComponent( Graphics g ) {
	        super.paintComponent( g );
	        Graphics2D g2d = (Graphics2D)g;

	        g2d.setPaint( Color.PINK );
	        g2d.fill( square1 );
	        g2d.setPaint( new Color( 0.5f, 0.0f, 0.9f ) );
	        g2d.fill( square2 );
	    }
}
