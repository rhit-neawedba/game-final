package helloWorld;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class HudViewer extends JLabel {
	
	public HudViewer() {
		setOpaque(false);
		setForeground(new Color(230, 240, 255));
        setFont(getFont().deriveFont(Font.BOLD, 13f));
	}
	
	public void refresh(HudModel hud) {
        StringBuilder html = new StringBuilder(
            "<html><h2 style='margin:0;padding:0;'>Score</h2><ol>"
        );
        html.append("<li>Current: ").append(hud.getScore()).append("</li>");
        html.append("<li>Lives: ").append(hud.getLives()).append("</li>");
        html.append("</ol></html>");
        setText(html.toString());
    }
}
