package helloWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Skye Willis
 */
public class Player extends Entity {
	private static final int PLAYER_HEIGHT=50, PLAYER_WIDTH=30;
	private static final double JUMP_VELOCITY = -800;
	
	private BufferedImage sprite;
	private BufferedImage leftsprite;
	private BufferedImage rightsprite;
	private BufferedImage jumpsprite;
	
	boolean spritecreated;
	
	
	public Player(int x, int y, GamePanel canvas) {
		super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT, canvas);
		
		//Player Sprite Creation @Anthony Dunn
		try {
			sprite = ImageIO.read(Player.class.getResource("rosie.png"));
			spritecreated = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			spritecreated = false;
			//e.printStackTrace();
		}
	}
	
	public void move(int dirx) {
		this.vx = dirx * this.speed;
		
	}
	
	public void jump() {
		if (grounded) {
			this.vy = JUMP_VELOCITY;
			this.grounded = false;
			this.y -= 5;
		}
	}
	

	@Override
	public void die() {
		// TODO Auto-generated method stub
	}
	// Sprite modification based on input @AnthonyDunn
	public void draw(Graphics2D g2) {
		Color c = g2.getColor();
		if(spritecreated) {
    		if(vx < 0) {
    			try {
					leftsprite = ImageIO.read(Player.class.getResource("rosieLeft.png"));
					g2.drawImage(leftsprite, getX(), getY(), width, height, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    		}
    		else if (vx > 0){
    			try {
					rightsprite = ImageIO.read(Player.class.getResource("rosieRight.png"));
					g2.drawImage(rightsprite, getX(), getY(), width, height, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		else if (Math.abs(vy) > 0){
    			try {
					jumpsprite = ImageIO.read(Player.class.getResource("rosieJump.png"));
					g2.drawImage(jumpsprite, getX(), getY(), width, height, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		else g2.drawImage(sprite, getX(), getY(), width, height, null);
		}
		else {
		g2.setColor(Color.RED);
		g2.fillRect(getX(), getY(), width, height);
//		g2.drawString(Boolean.toString(grounded), 100, 100);
		}
		// reset color
		g2.setColor(c);		
	}

}
