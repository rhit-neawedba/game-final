package helloWorld;

import java.awt.Graphics2D;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Skye Willis
 */
public abstract class Entity extends Collision {
	
	// physical constants
	private static final int GRAVITY = 30;
	private final double MAX_VELOCITY = 30d;
	
	protected int speed; int health;
	protected double vx, vy; // velocity x, and velocity y
	protected boolean grounded = false;
	private Foot foot;
	
	private long timeStamp = System.currentTimeMillis(); // timestamp in milliseconds for delta time
	
	public Entity(int x, int y, int width, int height) {
		this.x = x; this.y = y; this.width = width; this.height = height;
		this.foot = new Foot(x, y+height, width);
	}
	
	abstract public void die();
	public void applyPhysics(Collision[] staticColliders) {
		
		double deltaTime = getDeltaTime();
		
		// apply gravity
//		this.vy += GRAVITY * deltaTime;
		
		if (staticColliders != null) {
			for (Collision c : staticColliders) {
				// this would be more correct if you have a separate collision box just for the foot
				if (foot.collidesWith(c)) {
					this.vy = 0d;
					this.grounded = true;
				}
				else this.grounded = true;
			}
		}
		
		this.vx = Math.clamp(this.vx, -MAX_VELOCITY, MAX_VELOCITY);
		this.vy = Math.clamp(this.vy, -MAX_VELOCITY, MAX_VELOCITY);
		
		double newX = this.x + (this.vx * deltaTime);
		double newY = this.y + (this.vy * deltaTime);
		
		setPosition(newX, newY);
		
	}
	
	protected void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
		
		this.foot.setPosition(x, y+this.height);
	}
	public void setPosition(int x, int y) {
		setPosition((double)x,(double)y);
	}
	
	// deltatime for physics
	protected double getDeltaTime() {
		long newTime = System.currentTimeMillis();
		long deltaMilis = newTime-timeStamp;
		timeStamp = newTime;
		return deltaMilis / 1.0e3;
	}
	
	/**
	 * draws the Entity on a component
	 * @param g2
	 */
	abstract public void draw(Graphics2D g2);
	
}

/**
 * @author Skye Willis
 * acts as a collider to help Entity detect when it is on the ground
 */
class Foot extends Collision {
	private static final int HEIGHT = 10;
	
	public Foot(int x, int y, int width) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = HEIGHT;
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
}
