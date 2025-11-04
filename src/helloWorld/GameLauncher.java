package helloWorld;

import javax.swing.SwingUtilities;

public class GameLauncher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Game().show());
		//Test
	}
}

//adding to this because i think my commits are messed up 