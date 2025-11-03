package helloWorld;

import java.awt.Graphics2D;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Skye Willis
 */
public abstract class Entity extends Collision {
	
	// physical constants
	private static final int GRAVITY = 1000;
	private static final double epsilon = .1;
	private final double MAX_VELOCITY = 3000d;
	
	protected int health, maxHealth;
	protected int speed = 100; // default speed of 100
	protected double vx, vy; // velocity x, and velocity y
	protected boolean grounded = false;
	private Foot foot;
	
	private long timeStamp = System.currentTimeMillis(); // timestamp in milliseconds for delta time
	protected double deltatime;
	private GamePanel canvas; // to keep entities on screen or detect when they fall off
	
	public Entity(int x, int y, int width, int height, GamePanel canvas) {
		this.x = x; this.y = y; this.width = width; this.height = height;
		this.foot = new Foot(x, y+height, width);
		this.canvas = canvas;
	}
	
	abstract public void die();
	public void applyPhysics(Collision[] staticColliders) {
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
		else this.grounded = false;
		
		// world collision
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
		
		double newX = this.x + (this.vx * deltatime);
		double newY = this.y + (this.vy * deltatime);
		
		setPosition(newX, newY);
		
	}
	
	@Override
	protected void setPosition(double x, double y) {
		super.setPosition(x, y);
		
		this.foot.setPosition(x, y+this.height);
	}
	
	// deltatime for physics
	/**
	 * get the difference in time from the call to the last call
	 * @return deltatime in seconds
	 */
	private void getDeltaTime() {
		long newTime = System.currentTimeMillis();
		long deltaMilis = newTime-timeStamp;
		timeStamp = newTime;
		deltatime = deltaMilis / 1.0e3;
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
