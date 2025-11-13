package helloWorld;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
	// may be work adding a boolean[] for keys pressed
	
	private Player player;
	private boolean shooting;
	private boolean[] moveAxis;
	
	public Controller(Player player) {
		this.player = player;
		this.moveAxis = new boolean[2];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		switch (e.getKeyCode()) {
			case KeyEvent.VK_D -> movePlayer(1, true);
			case KeyEvent.VK_A -> movePlayer(-1, true);
			case KeyEvent.VK_SPACE -> player.jump();
			case KeyEvent.VK_ENTER -> { player.shoot();
			}
			case KeyEvent.VK_S -> player.setPressingDown(true);
				
			};
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		super.keyReleased(e);
		switch (e.getKeyCode()) {
			case KeyEvent.VK_D -> movePlayer(1, false);
			case KeyEvent.VK_A -> movePlayer(-1, false);
			case KeyEvent.VK_S -> player.setPressingDown(false);
		}
	}
	
	private void movePlayer(int dir, boolean enabled) {
		if (dir > 0) this.moveAxis[1] = enabled;
		else if (dir < 0) this.moveAxis[0] = enabled;
		
		if (this.moveAxis[0] && !this.moveAxis[1]) player.move(-1);
		if (!this.moveAxis[0] && this.moveAxis[1]) player.move(1);
		if (this.moveAxis[0] == this.moveAxis[1]) player.move(0);
		
	}
	
}
