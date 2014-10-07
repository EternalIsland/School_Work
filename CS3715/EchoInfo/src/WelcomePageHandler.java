import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class WelcomePageHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Headers responseHeaders = exchange.getResponseHeaders();
		responseHeaders.set("Content-Type", "text/html");
		exchange.sendResponseHeaders(200, 0);
		PrintWriter responseBody = new PrintWriter(exchange.getResponseBody());

		BufferedReader br = new BufferedReader(new FileReader("welcome.html"));
		String line;
		while ((line = br.readLine()) != null) {
			responseBody.println(line);
		}
		responseBody.close();
	}
}
