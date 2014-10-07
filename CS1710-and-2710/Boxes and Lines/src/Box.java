import java.awt.geom.Rectangle2D;

public class Box {
	private double w, h;
	private double r, g, b;
	
	public Box(double w, double h, double r, double g, double b){
		this.w = w;
		this.h = h;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public double getWidth(){
		return w;
	}
	public double getHeight(){
		return h;
	}
	public double getRed(){
		return r;
	}
	public double getGreen(){
		return g;
	}
	public double getBlue(){
		return b;
	}
	
}
