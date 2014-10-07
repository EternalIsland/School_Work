/**
 * @author Matthew Hynes
 * 
 * Similar to the CourseNode class but it creates a building object for each building in "buildings.xml".
 */

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Building {
	private Element ele;
	private String code, bldg;

	public Building(Element e) {
		ele = e;

		code = e.getAttribute("code");
		bldg = e.getTextContent();
	}

	public String getBldg() {
		return bldg;
	}

	public String getCode() {
		return code;
	}
}