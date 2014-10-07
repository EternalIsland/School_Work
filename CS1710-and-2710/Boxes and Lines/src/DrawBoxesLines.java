/**
 * A class that creates a line object and uses its methods to 
 * draw a list of boxes gathered from scanner input.
 * 
 * @author Matt Hynes
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;


public class DrawBoxesLines{
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		double lw = in.nextDouble();
		BufferedImage img = new BufferedImage(300,200, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = img.createGraphics(); 
        Line l = new Line(lw);
		while(in.hasNextDouble()){
			double w = in.nextDouble();
			double h = in.nextDouble();
			double r = in.nextDouble();
			double g = in.nextDouble();
			double b = in.nextDouble();
			Box bb = new Box(w,h,r,g,b);	
			l.addBox(bb);
			l.draw( g2d, l.getLineWidth(), l.getMaxHeight() );
		}
		ImageIO.write(img, "png", new File( "test.png" ) );		
	}
}
