package helloWorld;

import javax.swing.*;

public class Game {
	 private final JFrame frame = new JFrame("Final Project - Game Name TBD");
	    private GamePanel panel = new GamePanel();

	    
	 public Game() {
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setContentPane(panel);
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	    }
	 
	    public void show() {
	        frame.setVisible(true);
	    }
}
