/**
 * @author Matthew Hynes
 * 
 * This class accepts a command line argument in the form of an XML file for course 
 * objects and outputs an HTML file containing every building taken from "building.xml"
 *  and outputs all the courses in each room in the building. 
 * */

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CoursesInRooms {
	private static ArrayList<CourseNode> classList;
	private static ArrayList<Building> bldgList;
	private static TreeSet<String> rooms;

	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		classList = new ArrayList<>();
		bldgList = new ArrayList<>();
		rooms = new TreeSet<>();
		try {
			builder = factory.newDocumentBuilder();

			Document doc = builder.parse(args[0]);
			Element root = doc.getDocumentElement();

			NodeList courses = root.getElementsByTagName("course");

			for (int i = 0; i < courses.getLength(); i++) {
				Element e = (Element) courses.item(i);
				CourseNode cn = new CourseNode(e);
				if (cn != null) {
					classList.add(cn);
					for (String r : cn.getRooms()) {
						rooms.add(r);
					}
				}
			}

			doc = builder.parse("building.xml");
			root = doc.getDocumentElement();

			NodeList buildings = root.getElementsByTagName("building");

			// create list of every building
			for (int i = 0; i < buildings.getLength(); i++) {
				Element e = (Element) buildings.item(i);
				Building bg = new Building(e);
				if (bg != null) {
					bldgList.add(bg);
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out
				.println("<!DOCTYPE html>\n<html>\n<head>\n<title>Building Report</title>\n</head>\n<body>");

		// first check each building, then each room, then if each class is in
		// that room
		for (Building b : bldgList) {
			System.out.println("<h1>" + b.getBldg() + "</h1>");
			System.out.print("<ul>\n<li>");
			for (String r : rooms) {
				if (r.contains(b.getCode())) {
					System.out.println(r + ": ");
					for (CourseNode cn : classList) {
						if (cn.isInRoom(r)) {
							System.out.print(cn.getInfo() + ", ");
						}
					}
					System.out.println("</li>");
					System.out.print("<li>");
				}
			}
			System.out.println("</li>");
			System.out.println("</ul>");
		}
	}
}
