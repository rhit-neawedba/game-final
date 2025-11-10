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
	private int width;
	private int height;
	private BufferedImage bullet;
	
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
		this.shot = true;
	}
	
	public void draw (Graphics2D g2) {
		if(spritecreated && !shot) {
			g2.drawImage(bullet, x, y, width, height, null);
		}
		else if (shot) {
			g2.drawImage(bullet, x + getCount()*5, y, width, height, null);
			setCount(getCount() + 1);
		}
		else {
			g2.drawRect( x, y, width, height);
		}
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
