package helloWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * @author Skye Willis
 */
public class Player extends Entity {
	private static final int PLAYER_HEIGHT=50, PLAYER_WIDTH=50;

	public Player(int x, int y) {
		super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
	}
	
	public void handleKeyEvent(KeyEvent e) {
		this.applyPhysics(null);
		switch (e.getKeyCode()) {
			case KeyEvent.VK_D -> this.vx = 10;
			case KeyEvent.VK_A -> this.vx = -10;
		}
		System.out.printf("x: %f\n", this.x);
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
	}

	public void draw(Graphics2D g2) {
		Color c = g2.getColor();
		g2.setColor(Color.RED);
		g2.fillRect(getX(), getY(), width, height);
		g2.setColor(c);
	}
	
}
