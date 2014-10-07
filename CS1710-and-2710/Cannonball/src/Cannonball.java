/**
 * A program that calculates the path of a cannon ball using
 * formulas for projectile motion.
 * @author Matt Hynes
 *
 */
public class Cannonball {
	private static final double AG = -9.81; //acceleration due to gravity
	private static double xi; //initial x position
	private double xpos, ypos; //current x and y positions
	private double xvel, yvel; //current x and y velocities
	
	public Cannonball(double xi){
		this.xi = xi;
		ypos = 1; //giving ypos any value so move loop will start, does not affect results
	}
	private void move(double sec){
		//calculating current x and y positions with the formula xf = xi + vi * t + 1/2 * a * t^2
		xpos = xi + (xvel * sec); 
		ypos = (yvel * sec) - (4.9 * (sec * sec));
	}
	private double getY(){
		return ypos;
	}
	private double getX(){
		return xpos;
	}
	public void shoot(double a, double vi){ //angle and initial velocity
		xvel = vi * Math.cos(Math.toRadians(a));
		yvel = vi * Math.sin(Math.toRadians(a));
		double sec = 0.1;
		//printing the x and y positions at 0.0 seconds.
		System.out.print("Y: 0.00m ");
		System.out.printf("X: %.2f m %n", getX());
		
		while(ypos > 0){
			move(sec);
			sec += 0.1;
			if(ypos < 0 ){ //sets ypos equal 0 so the final value is not negative
				ypos = 0;
			}
			//printing x and y positions for every movement
			System.out.printf("Y: %.2fm ", getY());
			System.out.printf("X: %.2f m %n", getX());
		}
		
	}
}
