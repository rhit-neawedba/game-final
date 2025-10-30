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
	int x;
	int y;
	int width;
	int height;
	Color color = new Color(107,67,173); //change to close to enemy color later
	private BufferedImage sprite;
    private boolean spriteLoaded = false;
	
	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.attackRadius = attackRadius;
		this.idleRadius = idleRadius;
		this.damage = damage;
		
		try {
			sprite =  ImageIO.read(Enemy.class.getResource("tennis.png")); //change this to enemy.png
			spriteLoaded = true;
		} catch (IOException e) {
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
	public int getIdleRadius() {
		return idleRadius;
	}
	public void setIdleRadius(int idleRadius) {
		this.idleRadius = idleRadius;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * trackPlayer
	 * Controls all autonomous movement of the Enemy
	 * Help on the delays from:
	 * https://stackoverflow.com/questions/23283041/how-to-make-java-delay-for-a-few-seconds
	 * @param player
	 */
	public void trackPlayer(Player player) {
//		//fall if off platform - Enemy cannot jump to add a level of strategy to the player's gameplay
//		this.applyPhysics(null); // may have to redo this
//		
//		//chase players
//		if ((attackRadius)^2 <= Math.abs(this.getX() - player.getX()) + Math.abs(this.getY() - player.getY()) 
//				&& player.getX() > this.getX()) {
//			x += speed;
//		}
//		else if ((attackRadius)^2 <= Math.abs(this.getX() - player.getX()) + Math.abs(this.getY() - player.getY())  
//				&& player.getX() < this.getX()) {
//			x -= speed;
//		}
//		
//		//patrol pattern
//		else {
//			for(int i = 0; i < idleRadius; i++) {
//				x+= speed;
//			}
//			Thread.sleep(25);
//			for(int i = 0; i < idleRadius; i++) {
//				x-= speed;
//			}
//			Thread.sleep(25);
//		}
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
    		g2.drawImage(sprite, x, y, width, height, null);
    	}
    	else {
        g2.setColor(color);
        g2.fillRect(x, y, width, height);
    	}
    }

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}
	}
	
