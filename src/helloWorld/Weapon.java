package helloWorld;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Weapon extends Collision{

	private boolean pickedup;
	private int x;
	private int y;
	private Projectile[] bullets;
	private BufferedImage gun;
	
	boolean spritecreated;
	
	
	public Weapon() { 
		
	try {
		gun = ImageIO.read(Player.class.getResource("rosieLeft.png"));
		spritecreated = true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		spritecreated = false;
	}
}
	
	
}
