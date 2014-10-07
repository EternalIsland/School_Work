/**
 * @author Matthew Hynes (2010200318)
 * 
 * A client which accepts a course name and number and requests all 
 * the meeting times for that particular course from the server. 
 * The client receives the meeting times and prints them out.
 */

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MeetingClient {

	public static void main(String[] args) {
		if (args.length != 4) {
			System.out
					.println("usage: java MeetingClient host port course_subject course_number");
			System.exit(1);
		}

		int port = 0;
		String host = null;

		String crseSubj = args[2];
		String crseNum = args[3];

		try {
			host = args[0];
			port = Integer.parseInt(args[1]);

			InetAddress server = InetAddress.getByName(host);
			Socket sock = new Socket(server, port);
			System.out.println("connect " + sock);

			DataOutputStream out = new DataOutputStream(
					new BufferedOutputStream(sock.getOutputStream()));
			PrintWriter pw = new PrintWriter(out, true);

			DataInputStream in = new DataInputStream(sock.getInputStream());
			Scanner sc = new Scanner(in);

			pw.println(crseSubj);
			pw.println(crseNum);

			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
			// out.close();
			// in.close();
			// pw.close();
			System.out.println("closing " + sock);
			sock.close();

		} catch (NumberFormatException e) {
			System.out.println("bad port number");
			System.exit(1);
		} catch (UnknownHostException e) {
			System.out.println("bad host name");
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
