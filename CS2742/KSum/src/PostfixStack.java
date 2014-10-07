/** Matthew Hynes (201200318) 
* Evaluates arithemtic operations input in postfix notation.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class PostfixStack {

	private static int opRnd1, opRnd2, result;
	private static Stack<Integer> stack;
	private static BufferedReader in;
	private static String str;

	public static void main(String[] args) {
		stack = new Stack<>();
		in = new BufferedReader(new InputStreamReader(System.in));
		try {
			str = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		postfixEval(str);
	}

	private static void postfixEval(String pexp) {
		String[] ops = pexp.split("\\s+");

		for (String s : ops) {
			if (!s.equals("+") && !s.equals("-") && !s.equals("*")
					&& !s.equals("/")) {
				try {
					stack.push(Integer.parseInt(s));
				} catch (NumberFormatException ex) {
					System.out.println("Malformed input.");
					return;
				}
			} else {
				String op = s;
				if (stack.size() >= 2) {
					opRnd2 = stack.pop();
					opRnd1 = stack.pop();

					if (op.equals("+")) {
						result = opRnd1 + opRnd2;
					} else if (op.equals("-")) {
						result = opRnd1 - opRnd2;
					} else if (op.equals("*")) {
						result = opRnd1 * opRnd2;
					} else if (op.equals("/")) {
						result = opRnd1 / opRnd2;
					}
					stack.push(result);
				} else {
					System.out.println("Malformed input.");
					return;
				}
			}
		}
		System.out.println("Answer is " + result);
	}

}
