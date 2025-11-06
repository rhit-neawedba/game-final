package helloWorld;

import java.awt.Color;
	import java.awt.Graphics2D;
	import java.awt.image.BufferedImage;
	import java.io.IOException;
	import javax.imageio.ImageIO;

public class Collectible extends Collision{
//	    int dx = 3, dy = 2;
	    private int radius = 15;
	    private Color color = Color.RED;
	    private BufferedImage sprite;
	    private boolean spritecreated = false;
	    private boolean collected;
	    
	    public Collectible(double x, double y) {
	        this.x = x;
	        this.y = y;
	        this.width = radius*2;
	        this.height = radius*2;
	        this.collected = false;
	        try {
				sprite = ImageIO.read(Collectible.class.getResource("collectible.png"));
				spritecreated = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				spritecreated = false;
				//e.printStackTrace();
			}
	        
	    }


	    public void draw(Graphics2D g2) {
	    	if (collected) return;
	    	if (spritecreated) {
//	    		int drawX = x - radius;
//	    		int drawY = y - radius;
	    		int size = radius*2;
	    		
	    		g2.drawImage(sprite, getX(), getY(), size, size, null);
	    	}
	    	
	    	else {
	        g2.setColor(color);
	        g2.fillRect(getX(), getY(), radius * 2, radius * 2);
	    	}
	    }
	    
	    public boolean hasBeenCollected() {
	    	return collected;	
	    }
	    
	    public void collect() {
	    	collected = true;
	    }

	    /*public void move(int width, int height) {
	        x += dx;
	        y += dy;

	        // Left/right
	        if (x - radius < 0) { x = radius; dx = -dx; }
	        else if (x + radius > width) { x = width - radius; dx = -dx; }

	        // Top/bottom
	        if (y - radius < 0) { y = radius; dy = -dy; }
	        else if (y + radius > height) { y = height - radius; dy = -dy; }
	    }

	    
	}
	*/
}