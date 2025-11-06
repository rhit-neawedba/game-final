package helloWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * @author Skye Willis
 */
public class Player extends Entity {
	private static final int PLAYER_HEIGHT=50, PLAYER_WIDTH=30;
	private static final double JUMP_VELOCITY = -1000;
	public boolean playerDied = false;

	private BufferedImage sprite;
	private BufferedImage leftSprite;
	private BufferedImage rightSprite;
	private BufferedImage jumpSprite;
	
	boolean spritecreated;
	private boolean facingRight = false;
	
	public int score;
	
	
	public Player(int x, int y, GamePanel canvas) {
		super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT, canvas);
		super.health = 100;
		score = 0;
		
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
		if (dirx > 0) facingRight = true;
		else if (dirx < 0 ) facingRight = false;
	}
	
	public void jump() {
		if (grounded) {
			this.vy = JUMP_VELOCITY;
			this.grounded = false;
			this.y -= 5;
			
			pauseFootCollision(1);
		}
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
	}
	
	private void checkCollection(List<Collectible> collectibles) {
		for (Collectible c : collectibles) {
			if (collidesWith(c) && !c.hasBeenCollected()) collect(c);
		}
	}
	
	private void collect(Collectible c) {
		score += 1;
		c.collect();
	}
	
	public void tick(List<Platform> staticColliders, List<Collectible> collectibles) {
		super.applyPhysics(staticColliders);
		checkCollection(collectibles);
	}

	public void draw(Graphics2D g2) {
		Color c = g2.getColor();
		
		if(spritecreated) {
    		if(!facingRight) {
    			try {
					leftSprite = ImageIO.read(Player.class.getResource("rosieLeft.png"));
					g2.drawImage(leftSprite, getX(), getY(), width, height, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		else if (facingRight){
    			try {
					rightSprite = ImageIO.read(Player.class.getResource("rosieRight.png"));
					g2.drawImage(rightSprite, getX(), getY(), width, height, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		else if (Math.abs(vy) > 0){
    			try {
					jumpSprite = ImageIO.read(Player.class.getResource("rosieJump.png"));
					g2.drawImage(jumpSprite, getX(), getY(), width, height, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
		}
		else {
		g2.setColor(Color.RED);
		g2.fillRect(getX(), getY(), width, height);
//		g2.drawString(Boolean.toString(grounded), 100, 100);

		// reset color
		g2.setColor(c);
//		drawHitbox(g2);
//		d(g2);
		}
    }

}