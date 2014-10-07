import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RecurPolygon {

	private static double area = 0;
	private static Scanner sc;
	private static File pf;
	private static ArrayList<Integer> xPoints = new ArrayList<>();
	private static ArrayList<Integer> yPoints = new ArrayList<>();
	private static int x1, y1, x2, y2, x3, y3, x4, y4, x5, y5;
	private static int i = 0, j = 0;
	
	public static void main(String[] args) {

		try {
			pf = new File("poly.txt");
			sc = new Scanner(pf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(sc.hasNextLine()){
			xPoints.add(sc.nextInt());
			yPoints.add(sc.nextInt());
		}
		System.out.println(polygonArea(i, j));
	}
	
	public static double polygonArea(int i, int j){
		
		if(i >= xPoints.size() || j >= yPoints.size()){
			return area;
		}
		else{
			area = ((xPoints.get(i))*yPoints.get(j+1)) + ((xPoints.get(i+1))*yPoints.get(j+2)) + ((xPoints.get(i+2))*yPoints.get(j)) - ((xPoints.get(i+1))*yPoints.get(j)) - ((xPoints.get(i+2))*yPoints.get(j+1)) - ((xPoints.get(i))*yPoints.get(j+2));
			return polygonArea(i++, j++);
		}
	}

}
