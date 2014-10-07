import java.util.ArrayList;
import java.util.Scanner;


public class ParityCheck {

	public static void main(String[] args) {
		System.out.println("Enter your integers, each one on it's own line. Enter any non-integer to quit:");
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> intp = new ArrayList<>();
		int par = 0, sum = 0;
		
		while(in.hasNextInt()){
			intp.add(in.nextInt());
		}
		for(int i = 0; i < intp.size(); i++){
			int input = intp.get(i);
			while(input !=0){
				if((input&1) != 0){
					sum++;
					input >>>= 1;
				}
			}
			if(sum %2 == 0){
				par = 1;
			}
			else{
				par = 0;
			}
			System.out.println("n=" + intp.get(i) + " " + "p=" + par);
		}		
	}
}
