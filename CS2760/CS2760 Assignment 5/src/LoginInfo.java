/**
 * @author Matthew Hynes (201200318)
 * @class CS 2760
 * @date 1/11/2013
 * 
 * A program that reads a file containing usernames and info about 
 * them (e.g. login date) and allows the user to select from a list 
 * of all usernames and filter info for that username.
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LoginInfo extends JFrame {

	private JPanel contentPane;
	private BufferedReader rd;
	private Scanner sc;
	private ArrayList<String> alLogins, alInfo;
	private String line;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginInfo frame = new LoginInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginInfo() {
		setTitle("Login Infomator 3000");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));

		final JTextArea textArea = new JTextArea();
		textArea.setRows(12);
		textArea.setColumns(35);
		panel.add(textArea, BorderLayout.EAST);

		System.out.println("Input file to be read: ");

		sc = new Scanner(System.in);
		alLogins = new ArrayList<>();
		alInfo = new ArrayList<>();

		try {
			// read file name
			rd = new BufferedReader(new FileReader(sc.next()));

			while ((line = rd.readLine()) != null) {
				// add info to text area
				textArea.append(line + "\n");
				alInfo.add(line);
				// get username
				alLogins.add(line.substring(0, line.indexOf(' ')));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		/**
		 * A set is used to make sure that there are no duplicates in the
		 * username list. The Java Set class automatically removes all
		 * duplicates to conform to the nature of the abstract set structure.
		 */
		Set<String> logSet = new LinkedHashSet<>(alLogins);
		alLogins.clear();
		alLogins.addAll(logSet);

		String[] logins = alLogins.toArray(new String[alLogins.size()]);
		final String[] info = alInfo.toArray(new String[alInfo.size()]);

		final JList list = new JList(logins);

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				// selected username
				String uName = (String) list.getSelectedValue();

				textArea.setText("");

				// output lines of info that contain username
				for (String s : info) {
					if ((s.substring(0, s.indexOf(' '))).equals(uName)) {
						textArea.append(s + "\n");
					}
				}
			}
		});
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(list, BorderLayout.WEST);

	}
}
