package helloWorld;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Viewer {
	
	public static void createGUI() {
		JFrame frame = new JFrame("Final Project - Game Name TBD");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.requestFocusInWindow();
		frame.pack();                            
        frame.setLocationRelativeTo(null); 
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> createGUI());
	}

}


