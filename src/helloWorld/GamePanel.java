package helloWorld;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {

	Player player;
//	Enemy testEnemy;
	Timer timer;
	Level level;
	HudModel hudModel;
	HudViewer hudViewer;
	Weapon gun;
	
	
	private int currentLevel = 1;
    private int scoreThreshold = 30;
	
		public GamePanel() {
			//Panel set up
			setPreferredSize(new Dimension(800,600));
			setBackground(Color.white);
			setFocusable(true);
			setLayout(null);
			//HUD Setup
			hudModel = new HudModel();
			hudModel.setLives(3);
			hudViewer = new HudViewer();
			hudViewer.refresh(hudModel);
			int hudWidth = 150;
			int hudHeight = 75;
			hudViewer.setBounds(getPreferredSize().width - hudWidth - 10, 10, hudWidth, hudHeight+10);;
			add(hudViewer);
			//Player and world setup
			player = new Player(0, 0, this);
			gun = new Weapon(0,0,player);
			level = new Level(currentLevel,this, hudModel, hudViewer);


//			testEnemy = new Enemy(500, 0, 50, 50, this);
			
			//this term from: https://stackoverflow.com/questions/16535475/how-to-delete-a-class-object?noredirect=1&lq=1
//			if (testEnemy.hasDied) {
//				testEnemy = null;
//				System.gc();
//			}
			
 
			this.addKeyListener(new Controller(player));
			
			timer = new Timer(8, e -> {
//				player.applyPhysics(level.platforms);
//				testEnemy.tick(level.platforms, player);
				level.update(player);
				checkLevelProgression();
				repaint();
				
			});
			timer.start();
			 	
		}
		
		private void checkLevelProgression() {
	        if (hudModel.getScore() >= scoreThreshold) {
	            System.out.println("Threshold reached! Current score = " + hudModel.getScore());
	            currentLevel++;
	            hudModel.addLevel(1);
	            scoreThreshold += 30;
	            loadNextLevel();
	        }
	    }
		
	    private void loadNextLevel() {
	        System.out.println("Loading level " + currentLevel);
	        
	        level = new Level(currentLevel, this, hudModel, hudViewer);
	        
	        player.setX(0);
	        player.setY(getHeight() - player.getHeight());
	        
	        hudViewer.refresh(hudModel);
	        
	        repaint();
	    }

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.WHITE);
			
			
			Graphics2D g2 = (Graphics2D) g;
			level.draw(g2);
			player.draw(g2);
			gun.draw(g2);
//			testEnemy.draw(g2);
		}
	}