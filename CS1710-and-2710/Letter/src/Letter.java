/**
 * A program that constructs a simple letter using a sender name,
 * a recipient name, and a method that adds each to an array list.
 * @author Matt Hynes
 */

import java.util.ArrayList;

public class Letter {
	
	ArrayList<String> lines = new ArrayList<>();
	String from, to;
	
	public Letter(String from, String to){
		this.from = from;
		this.to = to;
		System.out.println("Dear " + to + ":\n"); //recipient's name
	}
	
	public void addLine(String line){
		lines.add(line); 
	}
	
	public void getText(){
		for(String s : lines){
			System.out.println(s);
		}
		System.out.println("\nSincerely,\n");
		System.out.println(this.from); //print sender's name
	}
}