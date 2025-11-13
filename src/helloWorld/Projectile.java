package helloWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile extends Collision{

	public boolean shot = false;
	private int count = 0;
	//private int x;
	//private int y;
	private BufferedImage bullet;
	
	private boolean right;
	
	boolean spritecreated;
	
	public Projectile(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 15;
		this.height = 10;
	
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
	
	private void update() {
		if (shot && (count < 40)) {
			if (right) {
				this.x = x + 5;
				count++;
			}
			else {
				this.x = x - 5;
				count++;
			}
		}
		else {
			shot = false;
			count = 0;
		}
		
	}
	
	public void draw (Graphics2D g2) {
		update();
		//drawHitbox(g2);		
		g2.drawImage(bullet,(int) x,(int) y, width, height, null);
		
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
