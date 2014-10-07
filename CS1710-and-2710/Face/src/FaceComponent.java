/**
 * A class that contains the components to draw
 * a basic, purple-coloured face
 * 
 * @author Matt Hynes
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;


public class FaceComponent extends JPanel{
	
	private Ellipse2D face = new Ellipse2D.Double(50, 50, 100, 100);
	private Ellipse2D eye1 = new Ellipse2D.Double(75, 75, 10, 10);
	private Ellipse2D eye2 = new Ellipse2D.Double(115, 75, 10, 10);
	private Line2D.Double mouth = new Line2D.Double(75, 120, 125, 120);
	private Stroke stroke1 = new BasicStroke(1.8f);
	
	public FaceComponent(){
		setPreferredSize( new Dimension( 250, 150 ) );
		setBackground( Color.white );

	}
	
	protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        Graphics2D g2d = (Graphics2D)g;

        g2d.setPaint( new Color( 0.5f, 0.0f, 0.9f ) );
        g2d.setStroke(stroke1);
        g2d.draw(face);
        g2d.draw(eye1);
        g2d.draw(eye2);
        g2d.draw(mouth);
    }

}
