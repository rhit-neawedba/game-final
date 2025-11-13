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
	
	
	//instance variables for weapon and bullet @Anthony
	private Weapon gun;
	private Projectile bullet;
	private boolean shooting = false;
	
	boolean spritecreated;
	private boolean facingRight = false;
	
	private static final int MAX_IFRAMES = 30;
	private int iframes = 0;
	public int score;
		
	
	public Player(int x, int y, GamePanel canvas) {
		super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT, canvas);
		this.health = 100;
		score = 0;
		
		try {
			leftSprite = ImageIO.read(Player.class.getResource("rosieLeft.png"));
			rightSprite = ImageIO.read(Player.class.getResource("rosieRight.png"));
			jumpSprite = ImageIO.read(Player.class.getResource("rosieJump.png"));
			spritecreated = true;
		}
		
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void move(int dirx) {
		this.vx = dirx * this.speed;
		if (dirx > 0) setFacingRight(true);
		else if (dirx < 0 ) setFacingRight(false);
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
		if (!isDead) {
		isDead = true;
		System.out.println("GAME OVER");
		}
	}
	
	/*
	 * @param Weapon gun - stores a reference to gun object in player class
	 * @param Projectile bullet - same as gun but for the bullet
	 * Needed to use shoot method and check bullet collisions
	 */
	public void addGun(Weapon gun, Projectile bullet) {
		this.gun = gun;
		this.bullet = bullet;
	}
	
	/*
	 * Allows gun method to be accessed by player so it can be accessed in controller
	 */
	public void shoot() {
		if (!shooting) {
			this.gun.shoot();
		}
		shooting = true;
	}
	
	public void setX(int x) {
	    this.x = x;
	}

	public void setY(int y) {
	    this.y = y;
	}
	
	public void setPosition(int x, int y) {
	    this.x = x;
	    this.y = y;
	}
	
	private void enemyCollision(List<Enemy> enemies, Projectile bullet) {
		for (Enemy e : enemies) {
			if (collidesWith(e) && this.vy <= e.getVy() && iframes == 0) {
				this.health -= e.damage;
				//System.out.println("hit");
				iframes = MAX_IFRAMES;
			}
			//System.out.println(bullet);
			if (bullet.shot && bullet.collidesWith(e)) {
				bullet.setShot();
				e.health -= 5;
				//System.out.println(e.health);
				
			}
		}
		if (health < 0) die();
		if (iframes > 0) iframes -= 1;
	}

	@Deprecated
	private void checkCollection(List<Collectible> collectibles) {
		for (Collectible c : collectibles) {
			if (collidesWith(c) && !c.hasBeenCollected()) collect(c);
		}
	}
	
	
	private void collect(Collectible c) {
		score += 1;
		c.collect();
	}

	
	public void tick(List<Platform> staticColliders, List<Enemy> enemies) {
		super.applyPhysics(staticColliders);
		enemyCollision(enemies,bullet);
	}
	
		
	public void draw(Graphics2D g2) {
		Color c = g2.getColor();
		offscreenCheck();
		if(spritecreated) {
    		if(!isFacingRight()) {
					g2.drawImage(leftSprite, getX(), getY(), width, height, null);
    		}
    		else if (isFacingRight()){
					g2.drawImage(rightSprite, getX(), getY(), width, height, null);				
    		}
    		else if (Math.abs(vy) > 0){
					g2.drawImage(jumpSprite, getX(), getY(), width, height, null);
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
	
	
	public boolean isFacingRight() {
		return facingRight;
	}
	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}
	
	private boolean pressingDown = false;

	public void setPressingDown(boolean pressingDown) {
	    this.pressingDown = pressingDown;
	}

	public boolean isPressingDown() {
	    return pressingDown;
	}
	public boolean isShooting() {
		return shooting;
	}
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

}