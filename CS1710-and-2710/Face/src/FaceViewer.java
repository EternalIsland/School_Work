/**
 * A class to test the FaceComponent class
 * 
 * @author Matt Hynes
 */

import javax.swing.JFrame;


public class FaceViewer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args){
		FaceComponent fc = new FaceComponent();
		
		JFrame frame = new JFrame();

	    frame.setSize( fc.getPreferredSize() );
        frame.setTitle( fc.getClass().getName() );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.add( fc );
        frame.setVisible( true );
	}

}
