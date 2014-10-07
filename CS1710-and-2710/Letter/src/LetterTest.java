/**
 * A tester class for the Letter class.
 * @author Matt Hynes
 */

public class LetterTest {

	public static void main(String[] args) {
		Letter ltr = new Letter("Mary", "John");
		ltr.addLine("I am sorry we must part.");
		ltr.addLine("I wish you all the best.");
		ltr.getText();
	}

}
