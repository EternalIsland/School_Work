/**
 * @author Matthew Hynes (201200318)
 * 
 * A server which receives a request from a MeetingClient object for a particular course 
 * and responds with all the meeting times for that course. 
 */

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MeetingServer extends Thread {

	private Socket sock;
	private Logger log;
	private FileHandler fh;

	public MeetingServer(Socket sock) {
		this.sock = sock;

		try {
			log = Logger.getGlobal();
			fh = new FileHandler("meeting.log");
			log.addHandler(fh);
			SimpleFormatter sf = new SimpleFormatter();
			fh.setFormatter(sf);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			log.info("accepted: " + this.sock);

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder;

			builder = factory.newDocumentBuilder();

			Document doc = builder.parse("slot-book.xml");
			Element root = doc.getDocumentElement();

			NodeList courses = root.getElementsByTagName("course");

			DataInputStream in = new DataInputStream(sock.getInputStream());
			Scanner rd = new Scanner(in);

			DataOutputStream out = new DataOutputStream(
					new BufferedOutputStream(sock.getOutputStream()));
			PrintWriter pw = new PrintWriter(out, true);

			String crseSubj = "";
			String crseNum = "";

			while (rd.hasNextLine()) {
				crseSubj = rd.nextLine();
				crseNum = rd.nextLine();

				for (int i = 0; i < courses.getLength(); i++) {
					Element e = (Element) courses.item(i);
					CourseNode cn = new CourseNode(e);
					if (cn != null) {
						if (cn.getSubj().equals(crseSubj)
								&& cn.getNum().equals(crseNum)) {
							for (String s : cn.getMeetings()) {
								pw.println(s);
								pw.flush();
							}
						}
					}
				}
				sock.close();
				log.info("closed: " + sock);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(5);

		try {
			ServerSocket list = new ServerSocket(0);
			System.out.println("Server port: " + list.getLocalPort());

			while (true) {
				Socket sock = list.accept();
				pool.execute(new MeetingServer(sock));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		pool.shutdown();
	}
}