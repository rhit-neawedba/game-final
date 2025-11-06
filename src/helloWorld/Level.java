package helloWorld;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;



/**
 * Level
 * creates hard coded levels that are painted on the game panel
 * @author nelsonmp
 * 
 * Resources used:
 * https://longbaonguyen.github.io/courses/platformer/platformer.html
 * 
 */
public class Level {
    
    private List<Platform> platforms;
    private List<Enemy> enemies;
    private List<Collectible> collectibles;
    
    public Level(int levelNumber, GamePanel panel) {
        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
        collectibles = new ArrayList<Collectible>();
        
        
        if (levelNumber == 1) {
            // Adds platforms
            platforms.add(new Platform(100, 400, 500, 50, panel));
            platforms.add(new Platform(200, 500, 600, 16, panel));
            collectibles.add(new Collectible(100, 100));
            
            // Adds enemies
            enemies.add(new Enemy(500, 0, 50, 50, panel));
            enemies.add(new Enemy(200, 550, 50, 50, panel));
            
        } else if (levelNumber == 2) {
            platforms.add(new Platform(50, 300, 400, 50, panel));
            
            enemies.add(new Enemy(300, 250, 50, 50, panel));
            
        } else if (levelNumber == 3) {
            platforms.add(new Platform(150, 350, 300, 50, panel));
            
            enemies.add(new Enemy(250, 300, 50, 50, panel));
        }
    }
    
    public void applyPhysics(Player player) {
    	player.tick(platforms, collectibles);
    	for (Enemy e : enemies) {
    		e.tick(platforms, player);
    	}
    }
    
    public void draw(Graphics2D g2) {
        for (Platform p : platforms) {
            p.draw(g2);
        }
        for (Enemy e : enemies) {
            e.draw(g2);
        }
        for (Collectible c : collectibles) {
    		c.draw(g2);
    	}
    }
    
    
}