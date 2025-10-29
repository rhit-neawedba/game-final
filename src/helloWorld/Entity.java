package helloWorld;

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
	
	private int timeStamp = LocalDateTime.now().getNano(); // timestamp in milliseconds for delta time
	
	public Entity(int x, int y, int width, int height) {
		this.foot = new Foot(x, y+height, width);
	}
	
	abstract public void die();
	public void applyPhysics(Collision[] staticColliders) {
		
		double deltaTime = getDeltaTime();
		
		// apply gravity
		this.vy += GRAVITY * deltaTime;
		
		for (Collision c : staticColliders) {
			// this would be more correct if you have a separate collision box just for the foot
			if (foot.collidesWith(c)) {
				this.vy = 0d;
				this.grounded = true;
			}
			else this.grounded = true;
		}
		
		this.vx = Math.clamp(this.vx, -MAX_VELOCITY, MAX_VELOCITY);
		this.vy = Math.clamp(this.vy, -MAX_VELOCITY, MAX_VELOCITY);
		
		double newX = this.x + this.vx * deltaTime;
		double newY = this.y + this.vy * deltaTime;
		
		SetPosition(newX, newY);
		
	}
	
	public void SetPosition(double x, double y) {
		SetPosition((int)x, (int)y);
	}
	public void SetPosition(int x, int y) {
		this.x = x;
		this.y = y;
		// move foot with entity
		this.foot.setPosition(x, y+this.height);
	}
	
	// deltatime for physics
	protected double getDeltaTime() {
		int newTime = LocalDateTime.now().getNano();
		int deltaNano = newTime-timeStamp;
		timeStamp = newTime;
		return deltaNano / 1.0E6d;
	}
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
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
