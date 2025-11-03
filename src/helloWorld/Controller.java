package helloWorld;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
	// may be work adding a boolean[] for keys pressed
	
	private Player player;
	
	public Controller(Player player) {
		this.player = player;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		switch (e.getKeyCode()) {
			case KeyEvent.VK_D -> player.move(1);
			case KeyEvent.VK_A -> player.move(-1);
			case KeyEvent.VK_SPACE -> player.jump();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		super.keyReleased(e);
		switch (e.getKeyCode()) {
			case KeyEvent.VK_D -> player.move(0);
			case KeyEvent.VK_A -> player.move(0);
		}
	}
	
}
