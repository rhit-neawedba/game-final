package helloWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Enemy
 * Creates an Enemy
 * @author neawedba
 */
public class Enemy extends Entity { 
	int attackRadius;
	int idleRadius;
	int damage;
	Color color = new Color(107,67,173); //change to close to enemy color later
	private BufferedImage sprite;
    private boolean spriteLoaded = false;
    
    private static final int IDLE_FRAME_MAX = 2000;
    private int idleFrameCount = 0;
	
	public Enemy(int x, int y, int width, int height, GamePanel canvas) {
		super(x, y, width, height, canvas);
		this.attackRadius = attackRadius; //perhaps 40?
		this.idleRadius = idleRadius; //about 20?
		this.damage = damage; //10?
		
		try {
			sprite =  ImageIO.read(Enemy.class.getResource("tennis.png")); //change this to enemy.png
			spriteLoaded = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			spriteLoaded = false;
			//e.printStackTrace();
		}
	}
	
	public int getAttackRadius() {
		return attackRadius;
	}
	public void setAttackRadius(int attackRadius) {
		this.attackRadius = attackRadius;
	}
	
	/*
	public int getIdleRadius() {
		return idleRadius;
	}
	public void setIdleRadius(int idleRadius) {
		this.idleRadius = idleRadius;
	}
	*/
	
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * trackPlayer
	 * Controls all autonomous movement of the Enemy
	 * Help on the delays from:
	 * https://stackoverflow.com/questions/23283041/how-to-make-java-delay-for-a-few-seconds
	 * @param player
	 */
	private void trackPlayer(Player player) {
		//fall if off platform - Enemy cannot jump to add a level of strategy to the player's gameplay
		
		// this can probably be called at a different time and it be fine
		//this.applyPhysics(null); // may have to redo this
		
		// this should be avoided but its here just in case
		if (player == null) return;
		
		//chase players
		
		/* changed to use the double position as opposed to the integer values
		 * for more precision (probably not needed)
		 * there is a way to raise a number to any power (Math.pow()),
		 * but this is an easy solution
		 * 
		 * also, it would be wise to compare against the center
		 * of the player but this works for now
		 */
		double dx = this.x - player.x; 
		double dy = this.y - player.y; 
		
		// the math here was off
		// `^` represents a bitwise XOR not exponentiation
 		if (dx*dx + dy*dy < (attackRadius*attackRadius)
				&& player.getX() > this.getX()) {
			this.vx = speed;
		}
		else if (dx*dx + dy*dy < (attackRadius*attackRadius)  
				&& player.getX() < this.getX()) {
			this.vx = -speed;
		}
		
		//patrol pattern
		
		/* from the stack overflow question referenced in the javadoc
		 * 
		 * "If this is in a Swing GUI, then get rid of all calls to
		 * Thread.sleep(...) as doing so can put the entire GUI to 
		 * sleep rendering it useless. Instead use a Swing Timer 
		 * to produce any delays in the GUI while letting it still 
		 * update its graphics."
		 * 
		 * https://stackoverflow.com/questions/23283041/how-to-make-java-delay-for-a-few-seconds
		 */
		
//		else {
//		for(int i = 0; i < idleRadius; i++) {
//			x+= speed;
//		}
//		
//		Thread.sleep(25);
//		for(int i = 0; i < idleRadius; i++) {
//			x-= speed;
//		}
//		Thread.sleep(25);
//	}
 		
		else {
			if (idleFrameCount == IDLE_FRAME_MAX) { // after a number of ticks, flip change the enemy direction
				vx = -vx;
				idleFrameCount = 0; // reset the frame count to 0 to restart the cycle
			}
			idleFrameCount += IDLE_FRAME_MAX;
		}		
		
	}
	
	public void attack(Player player) {
//		if (player.getY() == this.getY() && Math.abs(this.getX() - player.getX()) <= this.attackRadius) {
//			player.loseHealth(damage); 
//		}
	}
	
	/**
	 * draw
	 * Draws the enemy, using the sprite if it loads, and a colored rectangle similar to it if not
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		if (spriteLoaded) {
//    		g2.drawImage(sprite, x, y, width, height, null);
    	}
    	else {
	        g2.setColor(color);
	        g2.fillRect(getX(), getY(), width, height);
    	}
    }

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}
	

	public void tick(Collision[] staticColliders, Player player) {
		applyPhysics(staticColliders);
		System.out.printf("%f,%f",x,y);
		this.trackPlayer(player);
	}

//	@Override
//	public void applyPhysics(Collision[] staticColliders) {
//		tick(staticColliders, null);
//	}
}