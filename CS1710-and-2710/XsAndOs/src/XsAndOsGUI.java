/**Provides the GUI components for the Xs and Os game.
 * 
 * @author Matt Hynes
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class XsAndOsGUI extends JPanel implements ActionListener{
	
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 500;
	
	private int timesClicked = 0;
	private Font bFont = new Font("dialog", Font.BOLD, 70);
	
	private JFrame frame = new JFrame();
	private JButton clear = new JButton("Clear Board");
	
	private static JButton[] buttons = new JButton[9];
	
	public XsAndOsGUI(){	
		
		super(new BorderLayout());
		
		clear.setBackground(Color.GRAY);
		clear.setForeground(Color.BLACK);
		
		
		frame.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		frame.setTitle("The Best Xs and Os Game Ever");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JPanel p = new JPanel(new GridLayout(3, 3));
		//JButton[] buttons = new JButton[9];
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new JButton("");
			buttons[i].setFont(bFont);
			buttons[i].addActionListener(this);
			buttons[i].setForeground(Color.RED);
			buttons[i].setBackground(Color.BLACK);
			p.add(buttons[i]);
		}
		
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clearBoard();
			}
		});
		
		frame.add(p, BorderLayout.CENTER);
		frame.add(clear, BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent evt){
		Object o = evt.getSource();
		JButton bb = (JButton)o;
		
		if(timesClicked == 0){
			bb.setText("X");
			timesClicked++;
		}
		else if(timesClicked == 1){
			bb.setText("O");
			timesClicked++;
		}
		else{
			bb.setText("");
			timesClicked = 0;
		}
	}
	public void draw(){
		frame.setVisible(true);
	}
	public static void clearBoard(){
		for(JButton b : buttons){
			b.setText("");
		}
	}
}
