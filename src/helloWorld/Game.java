package helloWorld;

import javax.swing.*;

public class Game {
	 private final JFrame frame;
	    private GamePanel panel;

	    
	 public Game() {
		 	frame = new JFrame("Final Project - Game Name TBD");
		 	panel = new GamePanel();
		 	
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setContentPane(panel);
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	    }
	 
	    public void show() {
	        frame.setVisible(true);
	        panel.requestFocusInWindow();
	    }
}