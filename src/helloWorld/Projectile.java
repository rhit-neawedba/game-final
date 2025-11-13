package helloWorld;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile extends Collision{

	public boolean shot = false;
	private boolean isMovingRight = false;
	private int count = 0;
	private static int WIDTH = 15;
	private static int HEIGHT = 10;
	private BufferedImage bullet;
	
	boolean spritecreated;
	
	public Projectile(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = WIDTH;
		this.height = HEIGHT;
		
		try {
			bullet = ImageIO.read(Projectile.class.getResource("bullet.png"));
			spritecreated = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			spritecreated = false;
		}
	}
	
	public void setShot(boolean isShootingRight) {
		this.shot = !shot;
		this.isMovingRight = isShootingRight;
	}
	
	public void updatePosition(){
		if (isMovingRight) this.x += 5;
		else this.x -= 5;
	}
	
	public void draw (Graphics2D g2) {
		if(spritecreated && !shot) {
			g2.drawImage(bullet, getX(), getY(), width, height, null);
		}
		else if (shot && (count < 30)) {
			g2.drawImage(bullet, getX(), getY(), width, height, null);
			count++;
		}
		else {
			shot = false;
			count = 0;
		}
		
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
