package helloWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * @author Skye Willis
 */
public class Player extends Entity {
	private static final int PLAYER_HEIGHT=50, PLAYER_WIDTH=30;
	private static final double JUMP_VELOCITY = -1200;

	public Player(int x, int y, GamePanel canvas) {
		super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT, canvas);
	}
	public void move(int dirx) {
		this.vx = dirx * this.speed;
	}
	
	public void jump() {
		if (grounded) {
			this.vy = JUMP_VELOCITY;
			this.grounded = false;
			this.y -= 5;
			
			pauseFootCollision(1);
		}
	}
	

	@Override
	public void die() {
		// TODO Auto-generated method stub
	}

	public void draw(Graphics2D g2) {
		Color c = g2.getColor();
		g2.setColor(Color.RED);
		g2.fillRect(getX(), getY(), width, height);
//		g2.drawString(Boolean.toString(grounded), 100, 100);

		// reset color
		g2.setColor(c);
//		drawHitbox(g2);
//		d(g2);
	}

}
