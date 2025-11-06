package helloWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Weapon extends Collision{

	private boolean pickedup = false;
	private int x;
	private int y;
	private int width;
	private int height;
	private Player owner;
	private ArrayList<Projectile> bullets;
	private BufferedImage gun;
	
	boolean spritecreated;
	
	
	public Weapon(int x, int y,Player owner) { 
	this.x = x;
	this.y = y;
	this.owner = owner;
	
	try {
		gun = ImageIO.read(Weapon.class.getResource("weapon.png"));
		spritecreated = true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		spritecreated = false;
	}
}
	private void collisionCheck() {
		pickedup = collidesWith(owner);
	}
	public void shoot() {
		if (pickedup) {
		Projectile bullet = new Projectile(this.x, this.y/2);
		bullets.add(bullet);
		bullet.setShot();
	
		}
	}
	public void draw(Graphics2D g2) {
		collisionCheck();
		if(spritecreated && !pickedup) {
			g2.drawImage(gun, x, y, width, height, null);
		}
		else if (pickedup) {
			int gunx = (int) (owner.width - 10);
			int guny = (int) (owner.x + (owner.height/2));
			
			g2.drawImage(gun, gunx, guny, width, height, null);
		}
		else {
			Color orig = g2.getColor();
			g2.drawRect(x, y, width, height);
			g2.setColor(orig);
		}
	}
	
}
