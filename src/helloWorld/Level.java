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
    private int levelNumber; 
    
    public Level(int levelNumber, GamePanel panel, HudModel hudModel, HudViewer hudViewer) {
    	System.out.println("Creating Level " + levelNumber);
    	this.levelNumber = levelNumber;
    	this.hudModel = hudModel;
    	this.hudViewer = hudViewer;
        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
        collectibles = new ArrayList<>();
        
        
        if (levelNumber == 1) {
            // Adds platforms
            platforms.add(new Platform(100, 200, 500, 50, panel));
            platforms.add(new Platform(100, 475, 700, 50, panel));
            platforms.add(new Platform(600, 350, 100, 50, panel));
            // Adds enemies
            enemies.add(new Enemy(450, 0, 50, 50, panel));
            enemies.add(new Enemy(200, 550, 50, 50, panel));
            
            //Adds collectibles
            collectibles.add(new Collectible(100, 350));
            collectibles.add(new Collectible(700, 550));
            collectibles.add(new Collectible(100, 150));

            
        } else if (levelNumber == 2) {
        	// Adds platforms
            platforms.add(new Platform(100, 525, 100, 75, panel)); // small corner one
            platforms.add(new Platform(100, 475, 500, 50, panel)); // long corner one
            platforms.add(new Platform(600, 350, 100, 50, panel)); // small middle side one
            platforms.add(new Platform(100,150, 100, 50, panel)); // left small one
            platforms.add(new Platform(450,150, 150, 50, panel)); // right side top one
            platforms.add(new Platform(300, 175, 100, 50, panel)); // middle lower platform
            
            // Adds enemies
            enemies.add(new Enemy(375, 550, 50, 50, panel));  // under platform
            enemies.add(new Enemy(300, 450, 50, 50, panel)); // on lower platform
            enemies.add(new Enemy(475, 150, 50, 50, panel)); // on upper platform
            
            //Adds collectibles
            collectibles.add(new Collectible(250, 550)); // under cover
            collectibles.add(new Collectible(200, 425)); // above short platform
            collectibles.add(new Collectible(50, 125)); // fall catch
        } else if (levelNumber == 3) {
        	//Adds platforms
            platforms.add(new Platform(250, 400, 300, 50, panel)); // dead center platform
            platforms.add(new Platform(50, 200, 200, 50, panel)); // upper left platform
            platforms.add(new Platform(550, 200, 200, 50, panel)); // upper right platform
            
            //Adds enemies
            enemies.add(new Enemy(350, 300, 50, 50, panel)); // center platform
            enemies.add(new Enemy(125,150, 50, 50, panel)); // left platform
            enemies.add(new Enemy(635,150, 50, 50, panel)); // right platform

            
            //Adds collectibles
            collectibles.add(new Collectible(375, 340)); // center platform
            collectibles.add(new Collectible(125, 150)); // left platform
            collectibles.add(new Collectible(635, 150)); // right platform


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
        hudModel.setLives(player.health);
        hudViewer.refresh(hudModel);
    	
    	for (Enemy e : enemies) {
            e.tick(platforms, player);
        }
        
        player.tick(platforms, enemies);
        
        // https://www.w3schools.com/java/java_iterator.asp Used to help with iterator code 
        Iterator<Collectible> it = collectibles.iterator();
        while (it.hasNext()) {
            Collectible c = it.next();

            if (!c.hasBeenCollected() && player.collidesWith(c)) {
                if (player.isPressingDown()) {
                    c.collect();
                    hudModel.addScore(10);
                    System.out.println("Collected! Score = " + hudModel.getScore());
                    hudViewer.refresh(hudModel);
                    it.remove();
                }
            }
        }


    }  
}