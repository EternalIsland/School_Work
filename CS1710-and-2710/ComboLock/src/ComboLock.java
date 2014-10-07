/**
 * A program that simulates a combination lock and generates
 * three random numbers for the combo from 0 - 39 which the
 * user will have to guess correctly to open the lock.
 * @author Matt Hynes
 *
 */

public class ComboLock {
	
	private int secret1, secret2, secret3, dial, count;
	private boolean result1, result2, result3; //booleans to test against each secret
	
	public ComboLock(int secret1, int secret2, int secret3){
		this.secret1 = secret1;
		this.secret2 = secret2;
		this.secret3 = secret3;
		dial = 0;
		count = 0;
	}
	
	public void reset(){
		dial = 0;
		count = 0;
	}
	
	public void turnLeft(int ticks){
		dial += ticks;
		result2 = (secret2 == dial);
	}
	
	public void turnRight(int ticks){
		dial -= ticks; //right is taken to be in the negative direction
		count++; //keep track of how many times turnRight is called
		if(count == 1){
			result1 = (secret1 == Math.abs(dial)); //returning the positive value
		}
		else if(count == 2){ //determining whether the lock being turned right the first or second time
			result3 = (secret3 == Math.abs(dial));
		}
	}
	
	public boolean open(){
		if((result1) && (result2) && (result3)){
			System.out.println("True. Lock opened.");
			return true;
		}
		else{
			System.out.println("False. Lock closed.");
			return false;
		}
	}
}
