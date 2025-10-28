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
	Color color = new Color(100,200,200);
	private BufferedImage sprite;
    private boolean spriteLoaded = false;
	
	public Enemy(int attackRadius) {
		this.attackRadius = attackRadius;
		this.idleRadius = idleRadius;
		this.damage = damage;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
	public void trackPlayer(Player player) {
//		if (player.getPosition() <= attackRadius && player.getX() < Enemy.getX()) {
//			x += speed;
//		}
//		else if (player.getPosition() <= attackRadius && player.getX() < Enemy.getX()) {
//			x -= speed;
//		}
//		else {
//			for(int i = 0; i < idleRadius; i++) {
//				x+= speed;
//			}
//			//delay
//			for(int i = 0; i < idleRadius; i++) {
//				x-= speed;
//			}
//			//delay
//		}
	}
	
	public void attack(Player player) {
//		if (player.getY() == this.getY() && Math.abs(this.getX() - player.getX()) <= this.attackRadius) {
//			player.loseHealth(damage);
//		}
	}
	
	public void draw(Graphics2D g2) {
		if (spriteLoaded) {
    		g2.drawImage(sprite, x, y, width, height, null);
    	}
    	else {
        g2.setColor(color);
        g2.fillRect(x, y, width, height);
    	}
    }
	}
	
