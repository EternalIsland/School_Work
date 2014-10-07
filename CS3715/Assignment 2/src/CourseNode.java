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
	private String subjCode, crseNum, bldg, room;
	private ArrayList<String> buildings, rooms;

	public CourseNode(Element e) {
		ele = e;
		buildings = new ArrayList<>();
		rooms = new ArrayList<>();

		NodeList meetings = e.getElementsByTagName("meeting");

		subjCode = ele.getAttribute("subject");
		crseNum = ele.getAttribute("number");

		for (int i = 0; i < meetings.getLength(); i++) {
			Element ee = (Element) meetings.item(i);
			if (ee != null) {
				bldg = ee.getAttribute("building");
				room = ee.getAttribute("room");
				buildings.add(bldg);
				rooms.add(bldg + "-" + room);
			}
		}
	}

	public boolean isInBuilding(String bldg) {
		if (buildings.contains(bldg)) {
			return true;
		}
		return false;
	}

	public String getInfo() {
		return subjCode + "-" + crseNum;
	}

	public boolean isInRoom(String room) {
		if (rooms.contains(room)) {
			return true;
		}
		return false;
	}

	public ArrayList<String> getRooms() {
		return rooms;
	}
}
