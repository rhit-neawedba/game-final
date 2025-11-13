package helloWorld;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile extends Collision{

	public boolean shot = false;
	private int count = 0;
	private int x;
	private int y;
	private int width = 15;
	private int height = 10;
	private BufferedImage bullet;
	
	private boolean right;
	
	boolean spritecreated;
	
	public Projectile(int x, int y) {
		this.x = x;
		this.y = y;
		
		try {
			bullet = ImageIO.read(Projectile.class.getResource("bullet.png"));
			spritecreated = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			spritecreated = false;
		}
	}
	
	public void setShot() {
		this.shot = !shot;
	}
	
	public void draw (Graphics2D g2) {
		if(spritecreated && !shot) {
				g2.drawImage(bullet, x, y, width, height, null);
		}
		else if (shot && (count < 40)) {
			if (right) {
				g2.drawImage(bullet, x + count*5, y, width, height, null);
				count++;
			}
			else {
				g2.drawImage(bullet, x - count*5, y, width, height, null);
				count++;
			}
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

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
}
