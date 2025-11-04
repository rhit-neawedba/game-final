package helloWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Platform {

	int x, y;
	int width,height;
    Color color = Color.RED;
    private BufferedImage sprite;
    private boolean spritecreated = false;
    
    public Platform(int x, int y, int width, int height, GamePanel panel) {
        this.x = x;
        this.y = y;
        try {
			sprite = ImageIO.read(Platform.class.getResource("platform.png"));
			spritecreated = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			spritecreated = false;
			//e.printStackTrace();
		}
        
    }


    public void draw(Graphics2D g2) {
    	if (spritecreated) {
    		int drawX = x - (width/2);
    		int drawY = y - (height/2);
    		
    		g2.drawImage(sprite, drawX, drawY, width, height, null);
    	}
    	
    	else {
        g2.setColor(color);
        g2.fillRect(x - (width/2), y - (height/2), width, height);
    	}
    }


    
}

