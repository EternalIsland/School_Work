package crse;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CourseNode {

	private String coursesubj;
	private String coursenum;
	private Element ele;
	private ArrayList<String> meets;
	private ArrayList<CourseNode> classList;

	public CourseNode() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		classList = new ArrayList<>();

		try {
			builder = factory.newDocumentBuilder();

			Document doc = builder.parse("winter2014.xml");
			Element root = doc.getDocumentElement();

			NodeList courses = root.getElementsByTagName("course");

			for (int i = 0; i < courses.getLength(); i++) {
				Element e = (Element) courses.item(i);
				CourseNode c = new CourseNode(e);
				if (c != null) {
					classList.add(c);
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CourseNode(Element e) {
		ele = e;
		meets = new ArrayList<>();

		NodeList meetings = e.getElementsByTagName("meeting");

		coursesubj = ele.getAttribute("subject");
		coursenum = ele.getAttribute("cnum");

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
				String bldg = ee.getAttribute("bc");
				String room = ee.getAttribute("room");
				String info = "meeting" + (i + 1) + ": " + day + " " + start
						+ " to " + end + ", " + bldg + "-" + room;
				meets.add(info);
			}
		}
	}

	public ArrayList<CourseNode> classes() {
		return classList;
	}

	public String getSubj() {
		return coursesubj;
	}

	public String getNum() {
		return coursenum;
	}

	public ArrayList<String> getMeetings() {
		return meets;
	}

	public String getSections() {
		String sections = "";

		for (String s : meets) {
			sections.concat("<li>" + s + "</li>" + "\n");
		}
		System.out.println(sections);
		return sections;
	}

}
