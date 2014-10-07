/**A program that prints the system time for 10 seconds, using 
 * the java.util Timer and then stops.
 * 
 * @author Matt Hynes
 */

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class PrintTimeFor10{
	
	public static void main(String[] args){
		Timer t = new Timer();

		TimerTask task = new TimerTask(){
			int count = 0;
			final int COUNTER_LIMIT = 10;
			
			public void run(){
				Date d = new Date();
				System.out.println(d);
				count++;
				if(count > COUNTER_LIMIT){
					System.exit(1);
				}
			}	
		};
			
		t.schedule(task, 0, 1000);
	}
}
