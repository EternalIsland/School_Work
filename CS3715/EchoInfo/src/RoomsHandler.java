/**
 * @author Matthew Hynes (201200318)
 * 
 * Handles the requests to /rooms/building-code/room-number. Will return all courses being taught in that particular room.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RoomsHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Headers responseHeaders = exchange.getResponseHeaders();
		responseHeaders.set("Content-Type", "text/html");
		exchange.sendResponseHeaders(200, 0);
		PrintWriter responseBody = new PrintWriter(exchange.getResponseBody());
		URI uri = exchange.getRequestURI();
		String path = uri.getPath();
		String[] bldgRoom = path.split("rooms/");
		String bldg = bldgRoom[1].split("/")[0];
		String room = bldgRoom[1].split("/")[1];

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		try {
			builder = factory.newDocumentBuilder();

			Document doc = builder.parse("winter2014.xml");
			Element root = doc.getDocumentElement();

			NodeList courses = root.getElementsByTagName("course");

			// print HTML code which opens unordered list of courses
			responseBody
					.println("<!DOCTYPE html>\n<html>\n<head>\n<title>Courses Taught</title>\n</head>\n<body>\n<ol>");

			for (int i = 0; i < courses.getLength(); i++) {
				Element e = (Element) courses.item(i);
				CourseNode cn = new CourseNode(e);
				// make sure CourseNode object matches given building and room
				if (cn != null && cn.isInRoom(bldg, room)) {
					responseBody.println("<li>" + cn.getSubj() + " "
							+ cn.getNum() + "<ul>" + "<li>" + cn.section()
							+ " " + cn.days() + " " + cn.start() + " "
							+ cn.end() + "</li>" + "</ul>" + "</li>\n");
				}
			}
			responseBody.println("</ol>\n</body>\n</html>");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		responseBody.close();
	}
}
