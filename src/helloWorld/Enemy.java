
package helloWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;


/**
 * Enemy
 * Creates an Enemy
 * @author Benjamin Neawedde
 */
public class Enemy extends Entity { 
	int attackRadius;
	int idleRadius;
	int damage;
	Color color = new Color(255,145,1); 
	private BufferedImage sprite;
    private boolean spriteLoaded = false;
    public boolean hasDied = false;

    private static final int IDLE_FRAME_MAX = 2000;
    private int idleFrameCount = 0;
    private int idleFrameMax;
    /* where did these even come from and why?
     * - Skye
     */
    private long newTime = System.currentTimeMillis();
	private long timeStamp = newTime;
	private long deltaMilis = newTime-timeStamp;
	protected double deltatime = deltaMilis / 1.0e3;

	public Enemy(int x, int y, int width, int height, GamePanel canvas) {
		super(x, y, width, height, canvas);
		Random rand = new Random();
		this.attackRadius = 70;
		this.idleRadius = 100; 
		this.damage = 10;
		this.vx = 100;
		
//		System.out.println(this.idleRadius/this.vx);

		try {
			sprite =  ImageIO.read(Enemy.class.getResource("enemy.png")); 
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
//			System.out.printf("%f - %f\n", idleFrameCount*deltatime,this.idleRadius/this.vx);
			if (idleFrameCount*deltatime >= Math.abs(this.idleRadius/this.vx)) { // after a number of ticks, flip change the enemy direction
				vx = -vx;
				idleFrameCount = 0; // reset the frame count to 0 to restart the cycle
			}
			idleFrameCount += 1;
		}

	}

	public void attack(Player player) {
		if (player.getY() <= 10 + this.getY() && player.getY() >= 10 - this.getY() && Math.abs(this.getX() - player.getX()) <= this.attackRadius && player.vy <= 0) { //may change 10
			//Conditions: Player's y is between 10 + enemy and 10 - enemy and within attack radius and player is not falling into enemy
			if (player.getX() > this.getX()) {
				player.health -= damage;
				this.x = this.x + 1;
			}
			else if (player.getX() < this.getX()) {
				player.health -= damage;
				this.x = this.x - 1;
			}
		}
		if (player.health <= 0) {
			player.playerDied = true;
		}
	}

	/**
	 * draw
	 * Draws the enemy, using the sprite if it loads, and a colored rectangle similar to it if not
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		if (spriteLoaded) {
//    		g2.drawImage(sprite, x, y, width, height, null);
    		g2.drawImage(sprite, getX(), getY(), width, height, null);
    	}
    	else {
	        g2.setColor(color);
	        g2.fillRect(getX(), getY(), width, height);
    	}
    }

	public void isAttacked(Player player) {
		if(this.collidesWith(player) && player.getY() < this.getY()) { //in other words, if player lands on enemy from above 
			this.die(); //maybe add some space in the getY term
		}
	}

	@Override
	public void die() {
		this.hasDied = true;

	}


	public void tick(List<Platform> staticColliders, Player player) {
		applyPhysics(staticColliders);
//		System.out.printf("%f,%f",x,y);
//		System.out.printf("%f,%f",x,y);
		this.trackPlayer(player);
	}
}