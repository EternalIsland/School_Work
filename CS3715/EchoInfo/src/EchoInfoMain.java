/**
 * @author Matthew Hynes (201200318)
 * 
 * Main starting area for the server. If no pages are specified (ie; a context of '/'), 
 * then a standard welcome page with some testing links is displayed.
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

public class EchoInfoMain {

	public static void main(String[] args) {
		// using default address given on assignment page, can be changed to
		// anything
		InetSocketAddress addr = new InetSocketAddress(8984);
		try {
			HttpServer server = HttpServer.create(addr, 5);
			server.createContext("/", new WelcomePageHandler());
			server.createContext("/courses", new CourseHandler());
			server.createContext("/rooms", new RoomsHandler());
			server.setExecutor(Executors.newCachedThreadPool());
			server.start();
			System.out.println("Server listening on port " + addr.getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
