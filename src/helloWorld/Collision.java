package helloWorld;

/**
 * @author Skye Willis
 * contains a basic implementation to detect rectangular hitbox collision
 */
public abstract class Collision {
	protected double x, y; // stored as double for better position tracking
	protected int width, height;
	/**
	 * checks if 2 Collision object are colliding <br>
	 * based off of 
	 * <a href="https://www.jeffreythompson.org/collision-detection/rect-rect.php">
	 * 	https://www.jeffreythompson.org/collision-detection/rect-rect.php
	 * </a>
	 * @param other Collision to check against
	 * @return true if colliding
	 */
	public boolean collidesWith(Collision other) {
		return  this.x + this.width >= other.x &&
				this.x <= other.x + other.width &&
				this.y + this.height >= other.y &&
				this.y <= other.y + other.height;
	}
	
	public int getX() {return (int)x;}
	public int getY() {return (int)y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	
	protected void setPosition(double x, double y) {
		this.x = x;
		this.y = y;	
	}
	public void setPosition(int x, int y) {
		setPosition((double)x,(double)y);
	}
}
