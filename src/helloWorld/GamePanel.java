package helloWorld;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

		
		public GamePanel() {
			setPreferredSize(new Dimension(800,600));
			setBackground(Color.white);
			setFocusable(true);
			
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.WHITE);
		}	
	}