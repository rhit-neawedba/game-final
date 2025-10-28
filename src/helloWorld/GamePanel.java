package helloWorld;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
		
		private final Game canvas = new Game();
		
		public GamePanel() {
			this.setLayout(new BorderLayout(8,8));
//			this.setBackground(canvas.BG);
//			this.add(canvas, BorderLayout.CENTER);
			setFocusable(true);
			requestFocusInWindow();
			this.add(buildControls(), BorderLayout.SOUTH);
			this.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                switch (e.getKeyCode()) {
//	                    case KeyEvent.VK_LEFT  -> canvas.moveDelta(-10);
//	                    case KeyEvent.VK_RIGHT -> canvas.moveDelta(10);
//	                    case KeyEvent.VK_SPACE -> {
//	                        if (canvas.timer.isRunning()) canvas.stop();
//	                        else canvas.start();
	                    }
	                }
//	                canvas.repaint();
//	            }
	        });
			
			
		}
		
		private JComponent buildControls() {
	        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 6));
	        JButton left   = new JButton("◀ Left");
	        JButton right  = new JButton("Right ▶");
	        JButton center = new JButton("Center");



//	        left.addActionListener(e -> { canvas.moveDelta(-10); requestFocusInWindow(); });
//	        right.addActionListener(e -> { canvas.moveDelta(+10); requestFocusInWindow(); });
//	        center.addActionListener(e -> { canvas.setX(canvas.WIDTH/2); requestFocusInWindow(); });

	        controls.add(left); 
	        controls.add(right);
	        controls.add(center);
	        return controls;
	    }

	}