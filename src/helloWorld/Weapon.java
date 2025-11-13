package helloWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.util.ArrayList;

import javax.imageio.ImageIO;



public class Weapon {
	
	private boolean attached;
	private boolean pickedup = false;
	private int x;
	private int y;
	private int width = 25;
	private int height = 20;
	private Player owner;
	private Projectile bullet;
	//private ArrayList<Projectile> bullets;
	private BufferedImage rightgun;
	private BufferedImage leftgun;
	private BufferedImage gun;
	
	boolean spritecreated;
	
	
	public Weapon(int x, int y, Player owner) { 
	this.x = x;
	this.y = y;
	attachTo(owner);
	
	try {
		rightgun = ImageIO.read(Weapon.class.getResource("weapon.png"));
		leftgun = ImageIO.read(Weapon.class.getResource("weaponLeft.png"));
		spritecreated = true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		spritecreated = false;
	}
}
	
	public void shoot() {
		if (isAttached()) {
		bullet.setShot();
		}
	}
	
	
	

	public void attachTo(Player player) {
	    if (!isAttached()) setAttached(true);
		if (owner == null) this.owner = player;
		this.bullet = new Projectile(this.x, this.y);
		player.addGun(this, this.bullet);
	}

	public boolean isAttached() {
	    return this.attached;
	}

	public void update() {
	    if (attached && owner != null) {
	    	if (owner.isFacingRight()) {
	        // Follow player's position with an offset
	    	gun = rightgun;
	        this.x = owner.getX() + (owner.getWidth() - 5);
	        this.y = owner.getY() + (owner.getHeight()/2 - 10);
	    	}
	    	else {
	    	gun = leftgun;
	        this.x = owner.getX() - (width - 5);
	        this.y = owner.getY() + (owner.getHeight()/2 - 10);
	    	}
	    if (!bullet.shot) {
	    	if (bullet.getCount() == 0) {
	    		owner.setShooting(false);
	    	}
	    	if (owner.isFacingRight()) {
	    		bullet.setRight(true);
	    		bullet.setX(this.x + this.width - 8);
	    		bullet.setY(this.y + height/2 - 5);
	    	}
	    	else if (!owner.isFacingRight()){
	    		//System.out.println(" test");
	    		bullet.setRight(false);
	    		bullet.setX(this.x - 8);
		    	bullet.setY(this.y + height/2 - 5);
	    	}
	    }
	    }
	}
	
	public void draw(Graphics2D g2) {

		if(spritecreated) {
			update();
			g2.drawImage(gun, x, y, width, height, null);
		}	
			
			if ((bullet != null)) {
				bullet.draw(g2);
				
			}
	
		else {
			Color orig = g2.getColor();
			g2.drawRect(x, y, width, height);
			g2.setColor(orig);
		}
	}


	public void setAttached(boolean attached) {
		this.attached = attached;
	}
	
	public Player getOwner() {
		return this.owner;
	}
	
}
