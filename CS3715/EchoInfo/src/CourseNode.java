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
	private String subjCode, crseNum, section, days, start, end;
	private ArrayList<String> meets;

	public CourseNode(Element e) {
		ele = e;
		meets = new ArrayList<>();

		NodeList meetings = e.getElementsByTagName("meeting");
		NodeList sections = e.getElementsByTagName("section");

		subjCode = ele.getAttribute("subject");
		crseNum = ele.getAttribute("cnum");

		for (int i = 0; i < meetings.getLength(); i++) {
			Element ee = (Element) meetings.item(i);
			if (ee != null) {
				String bldg = ee.getAttribute("bc");
				String room = ee.getAttribute("room");
				String info = bldg + " " + room;
				meets.add(info);

				days = ee.getAttribute("days");
				start = ee.getAttribute("start");
				end = ee.getAttribute("end");
			}
		}
		Element sec = (Element) sections.item(0);
		section = sec.getAttribute("seq");
	}

	public String getSubj() {
		return subjCode;
	}

	public String getNum() {
		return crseNum;
	}

	public boolean isInRoom(String bldg, String room) {
		String bldgRoom = bldg + " " + room;
		if (meets.contains(bldgRoom)) {

			return true;
		}
		return false;
	}

	public String section() {
		return "section: " + section;
	}

	public String start() {
		return "start: " + start;
	}

	public String end() {
		return "end: " + end;
	}

	public String days() {
		return "days: " + days;
	}
}
