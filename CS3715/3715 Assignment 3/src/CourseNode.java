

/**
 * @author Matthew Hynes
 * 
 * A class to hold course objects that contain info about each course in 
 * the XML file such as meeting times and room locations.
 */

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CourseNode {
	private Element ele;
	private String subjCode, crseNum;
	private ArrayList<String> meets;

	public CourseNode(Element e) {
		ele = e;
		meets = new ArrayList<>();

		NodeList meetings = e.getElementsByTagName("meeting");

		subjCode = ele.getAttribute("subject");
		crseNum = ele.getAttribute("number");

		for (int i = 0; i < meetings.getLength(); i++) {
			Element ee = (Element) meetings.item(i);
			if (ee != null) {
				String day = ee.getAttribute("day");
				if (day.equals("M")) {
					day = "Monday";
				} else if (day.equals("T")) {
					day = "Tuesday";
				} else if (day.equals("W")) {
					day = "Wednesday";
				} else if (day.equals("R")) {
					day = "Thursday";
				} else if (day.equals("F")) {
					day = "Friday";
				}
				String start = ee.getAttribute("start");
				String end = ee.getAttribute("end");
				String bldg = ee.getAttribute("building");
				String room = ee.getAttribute("room");
				String info = "meeting" + (i + 1) + ": " + day + " " + start
						+ " to " + end + ", " + bldg + "-" + room;

				meets.add(info);
			}
		}
	}

	public String getSubj() {
		return subjCode;
	}

	public String getNum() {
		return crseNum;
	}

	public ArrayList<String> getMeetings() {
		return meets;
	}
}
