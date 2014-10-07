/**A program that prompt the user for the (x,y) coordinates and radius odf
 * a circle and then draws it when a button is clicked.
 * 
 * @author Matt Hynes
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class DrawCircle extends JPanel implements ActionListener{
	
	private JButton draw;
	private static JTextField tx;
	private static JTextField ty;
	private static JTextField tr;
	
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	
	private double x, y, r;
	
	public DrawCircle(){
		setLayout(new BorderLayout());
		setPreferredSize( new Dimension( WINDOW_WIDTH, WINDOW_HEIGHT ) );
		draw = new JButton("Draw");
		draw.setForeground(Color.WHITE);
		draw.setBackground(Color.BLACK);
		 //setting action listener to read text field inputs when draw button is clicked and convert them to doubles
		draw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				x = Double.parseDouble(tx.getText());
				y = Double.parseDouble(ty.getText());
				r = Double.parseDouble(tr.getText());
			}
		});
		
		tx = new JTextField("Enter x coordinates");
		ty = new JTextField("Enter y coordinates");
		tr = new JTextField("Enter radius");
		tr.setBounds(0, 0, 80, 25);
		tx.setBounds(90, 0, 80, 25);
		ty.setBounds(170, 0, 80, 25);
		
		JPanel p = new JPanel(new GridLayout(1,3));
		p.add(tx);
		p.add(ty);
		p.add(tr);
		
		add(draw, BorderLayout.SOUTH);
		add(p, BorderLayout.NORTH);
	}
	protected void paintComponent( Graphics g ){
		//creating circle using changed coordinates and radius
		Ellipse2D.Double circ = new Ellipse2D.Double(x, y, r, r);
        super.paintComponent( g );
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor ( Color.RED );
        g2d.fill(circ);
	} 
	public static void main(String[] args){
		DrawCircle dc = new DrawCircle();
		JFrame frame = new JFrame();
		frame.setSize( dc.getPreferredSize() );
	    frame.setTitle( dc.getClass().getName() );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.add(dc);
		frame.setVisible(true);
	}
}
