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
	
		public GamePanel() {
			player = new Player(0, 0, this);
			testEnemy = new Enemy(500, 0, 50, 50, this);
			
			setPreferredSize(new Dimension(800,600));
			setBackground(Color.white);
			setFocusable(true);
			
			timer = new Timer(8, e -> {
				player.applyPhysics(null);
				testEnemy.tick(null, player);
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
			player.draw(g2);
			testEnemy.draw(g2);
			
			//the enemy deletion from: https://stackoverflow.com/questions/24692682/deleting-a-class-object-in-java
			if (testEnemy.hasDied) {
				testEnemy = null;
				System.gc();
			}
		}
	}