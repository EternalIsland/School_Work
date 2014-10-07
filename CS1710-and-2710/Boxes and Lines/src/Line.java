/**
 * A class containing all the methods needed to create an array of boxes
 * and draw them on a line of indicated width.
 * 
 * @author Matt Hynes
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Line{
	private int i = 0;
	private double w;
	private double maxHeight = Double.MIN_VALUE;
	ArrayList<Box> boxes = new ArrayList<Box>();
	private static double xSum = 0; //total width on current line
	
	public Line(double w){
		this.w = w;
	}
	public boolean addBox(Box b){
		if(xSum < w){
			boxes.add(b);
			if(boxes.size() == 1 ){ //draws the first box at (0,0)
				xSum = 0;
			}
			else if(boxes.size() == 2){ //draws the second box at the previous box's width
				xSum = boxes.get(0).getWidth();
			}
			else{ //draws all subsequent boxes at the difference bewteen their widths an the previous one's
				xSum += boxes.get(i - 1).getWidth();
			}
			return true;
		}
		else if(xSum >= w){
			for(Box bb: boxes){ //updating max height to be equal to the tallest box on the previous line
				maxHeight = Math.max(maxHeight, bb.getHeight());
			}
			xSum = 0;
			boxes.add(b);
			return true;
		}
		return false;	
	}
	public double getLineWidth(){
		return xSum;
	}
	public double getMaxHeight(){
		return maxHeight;
	} 
	public void draw( Graphics2D g2d, double x, double y){	
		while(i < boxes.size()){
				Rectangle2D rect = new Rectangle2D.Double(x, y, boxes.get(i).getWidth(), boxes.get(i).getHeight());
				g2d.setPaint( new Color( (float) boxes.get(i).getRed(), (float) boxes.get(i).getGreen(), (float)boxes.get(i).getBlue()));
				g2d.fill(rect);
				i++;
		}
	}
}