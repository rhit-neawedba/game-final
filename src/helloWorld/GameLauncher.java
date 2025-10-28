package helloWorld;

import javax.swing.SwingUtilities;

public class GameLauncher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater(() -> new Game().show());
		

	}

}