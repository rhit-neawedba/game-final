package helloWorld;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
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
    
    public List<Platform> platforms;
    private List<Enemy> enemies;
    private List<Collectible> collectibles;
    private HudModel hudModel;
    private HudViewer hudViewer;
    
    public Level(int levelNumber, GamePanel panel, HudModel hudModel, HudViewer hudViewer) {
    	this.hudModel = hudModel;
    	this.hudViewer = hudViewer;
        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
        collectibles = new ArrayList<>();
        
        
        if (levelNumber == 1) {
            // Adds platforms
            platforms.add(new Platform(100, 400, 500, 50, panel));
            platforms.add(new Platform(200, 500, 600, 16, panel));
            
            // Adds enemies
            enemies.add(new Enemy(500, 0, 50, 50, panel));
            enemies.add(new Enemy(200, 550, 50, 50, panel));
            
            //Adds collectibles
            collectibles.add(new Collectible(100, 350));
            collectibles.add(new Collectible(200, 450));
            
        } else if (levelNumber == 2) {
            platforms.add(new Platform(50, 300, 400, 50, panel));
            
            enemies.add(new Enemy(300, 250, 50, 50, panel));
            
        } else if (levelNumber == 3) {
            platforms.add(new Platform(150, 350, 300, 50, panel));
            
            enemies.add(new Enemy(250, 300, 50, 50, panel));
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
    
    public void update(Player player) {
        for (Enemy e : enemies) {
            e.tick(platforms, player);

        }
        
        // https://www.w3schools.com/java/java_iterator.asp Used to help with iterator code 
        Iterator<Collectible> it = collectibles.iterator();
        while (it.hasNext()) {
            Collectible c = it.next();

            if (!c.hasBeenCollected() && player.collidesWith(c)) {
                if (player.isPressingDown()) {
                    c.collect();
                    hudModel.addScore(10);
                    hudViewer.refresh(hudModel);
                    it.remove();
                }
            }
        }


    }  
}