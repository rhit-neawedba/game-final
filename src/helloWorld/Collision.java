package helloWorld;

/**
 * @author Skye Willis
 * contains a basic implementation to detect rectangular hitbox collision
 */
public abstract class Collision {
	protected int x, y, width, height;
	/**
	 * checks if 2 Collision object are colliding
	 * @param other Collision to check against
	 * @return true if colliding
	 */
	public boolean collidesWith(Collision other) {
		// https://www.jeffreythompson.org/collision-detection/rect-rect.php
		return  this.x + this.width >= other.x &&
				this.x <= other.x + other.width &&
				this.y + this.height >= other.y &&
				this.y <= other.y + other.height;
	}
}
