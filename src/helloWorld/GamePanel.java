package helloWorld;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {

	Player player;
	Enemy testEnemy;
	Timer timer;
	Level level;
	HudViewer hudView;
	HudModel hud;

	
		public GamePanel() {
			player = new Player(0, 0, this);
			testEnemy = new Enemy(500, 0, 50, 50, this);
			level = new Level(1,this);
			//this.add(hudView);
		
			
			//this term from: https://stackoverflow.com/questions/16535475/how-to-delete-a-class-object?noredirect=1&lq=1
			if (testEnemy.hasDied) {
				testEnemy = null;
				System.gc();
			}
			
			setPreferredSize(new Dimension(800,600));
			setBackground(Color.white);
			setFocusable(true);
			
			timer = new Timer(8, e -> {
				level.applyPhysics(player);
				repaint();
			});
			timer.start();
			
			this.addKeyListener(new Controller(player));
				
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.WHITE);
			
			Graphics2D g2 = (Graphics2D) g;
			level.draw(g2);
			player.draw(g2);
			testEnemy.draw(g2);
		}
	}