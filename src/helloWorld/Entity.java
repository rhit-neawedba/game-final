package helloWorld;

import java.awt.Graphics2D;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Skye Willis
 */
public abstract class Entity extends Collision {
	
	// physical constants
	private static final int GRAVITY = 2000;
	private static final double epsilon = .1;
	private final double MAX_VELOCITY = 3000d;
	
	protected int health, maxHealth;
	protected int speed = 100; // default speed of 100
	protected double vx, vy; // velocity x, and velocity y
	protected boolean grounded = false;
	private Foot foot;
	private UpperBody upperBody;
	
	private long timeStamp = System.currentTimeMillis(); // timestamp in milliseconds for delta time
	protected double deltatime;
	private GamePanel canvas; // to keep entities on screen or detect when they fall off
	
	public boolean isDead = false;
	private boolean offscreen;
	
	public Entity(int x, int y, int width, int height, GamePanel canvas) {
		this.x = x; this.y = y; this.width = width; this.height = height;
		this.foot = new Foot(x, y+height, width);
		this.upperBody = new UpperBody(x, y, width, height);
		this.canvas = canvas;
	}
	
	abstract public void die();
	public void applyPhysics(List<Platform> staticColliders) {
		getDeltaTime();
		
		// apply gravity
		if (!grounded) {
			this.vy += GRAVITY * deltatime;
		}
		
		this.grounded = false;
		
		// collision with the bottom of the window
		if (this.y+this.height > canvas.getHeight()-epsilon) {
			this.vy = 0d;
			this.y = canvas.getHeight() - this.height; // snap to floor
			this.grounded = true;
		}
		
		// world collision
		if (staticColliders != null) {
			for (Collision c : staticColliders) {
				// body collision
				if (collidesWith(c) && !foot.collidesWith(c)) {
//					System.out.printf("(%f,%f)\n", x-tempx, y-tempy);
					boolean upperCollide = upperBody.collidesWith(c);
					for(int i = 0; collidesWith(c) && i < 10; i++) {
						int sign = (i % 2) * 2-1;
						x -= 0.1d*sign*i;
						if (!upperCollide) y -= vy*deltatime*sign*i;
						else y += i;
					}
					if (!upperCollide) vy = 0;
				}
				
				// foot collision
				if (foot.collidesWith(c) && !foot.isPaused(deltatime)) {
					this.vy = 0d;
					this.grounded = true;
					this.y = c.y - this.height;
				}
				
//				else this.grounded |= false;
			}
		}
		
		this.vx = Math.clamp(this.vx, -MAX_VELOCITY, MAX_VELOCITY);
		this.vy = Math.clamp(this.vy, -MAX_VELOCITY, MAX_VELOCITY);
		
		double newX = this.x + (this.vx * deltatime);
		double newY = this.y + (this.vy * deltatime);
		
		setPosition(newX, newY);
		
	}
	
	protected void setPosition(double x, double y) {
		super.setPosition(x, y);
		
		this.foot.setPosition(x, y+this.height);
		this.upperBody.setPosition(x, y);
	}
	
	public double getVy() { return this.vy; }
	public double getVx() { return this.vx; }
	
	protected void pauseFootCollision(double time) {
		foot.pause(time/1E3d);
	}
	
	// deltatime for physics
	/**
	 * get the difference in time from the call to the last call
	 * @return deltatime in seconds
	 */
	private void getDeltaTime() {
//		deltatime = .001d;
//		return;
		long newTime = System.currentTimeMillis();
		long deltaMilis = newTime-timeStamp;
		timeStamp = newTime;
		deltatime = deltaMilis / 1.0e3;
	}
	
	protected void offscreenCheck() {
		if ((this.x) > (this.canvas.getWidth() + 10) || (this.x) < -10){
			if (vx > 0) {
			this.setPosition(-5, this.y);
			}
		else {
			this.setPosition(this.canvas.getWidth() + 5, this.y);
			}
		}
	}
	/**
	 * draws the Entity on a component
	 * @param g2
	 */
	abstract public void draw(Graphics2D g2);
	
//	public void d(Graphics2D g2) {
//		this.foot.d(g2);
//		this.upperBody.d(g2);
//	}

}

/**
 * @author Skye Willis
 * acts as a collider to help Entity detect when it is on the ground
 */
class Foot extends Collision {
	private static final int HEIGHT = 3;
	private double footPause = 0;
	
	public Foot(int x, int y, int width) {
		this.x = x;
		this.y = y;
		this.width = width-2;
		this.height = HEIGHT;
	}

	public void pause(double time) {
		footPause = time;
	}
	
	public boolean isPaused(double deltatime) {
		if (footPause > 0) {
			footPause -= deltatime;
			return true;
		}
		return false;
	}
	
	@Override
	public void setPosition(double x, double y) {
		super.setPosition(x+1, y);
	}
	
	// debug hitbox draw
//	public void d(Graphics2D g2) {
//		g2.drawRect(getX(), getY(), width, height);
//	}
	
}

class UpperBody extends Collision{
	private static int EXPAND = 4;
	private static int HEAD_EXPAND = 10;
	public UpperBody(int x, int y, int width, int height) {
		this.x = x-EXPAND*2;
		this.y = y+HEAD_EXPAND;
		this.width = width+4*EXPAND;
		this.height = height-1-HEAD_EXPAND;
	}
	
	@Override
	public void setPosition(double x, double y) {
		super.setPosition(x-EXPAND*2, y+HEAD_EXPAND);
	}
	
	
//	public void d(Graphics2D g2) {
//		g2.drawRect(getX(), getY(), width, height);
//	}
}
