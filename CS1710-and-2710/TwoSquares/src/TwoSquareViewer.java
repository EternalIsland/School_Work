/**
 * A class to test the TwoSquareComponent class.
 * 
 * @author Matt Hynes
 */

import javax.swing.JFrame;


public class TwoSquareViewer extends JFrame{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TwoSquareComponent sq = new TwoSquareComponent();
		
		JFrame frame = new JFrame();

	    frame.setSize( sq.getPreferredSize() );
        frame.setTitle( sq.getClass().getName() );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.add( sq );
        frame.setVisible( true );

	}

}
