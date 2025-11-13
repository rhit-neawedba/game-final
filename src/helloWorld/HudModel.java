package helloWorld;

public class HudModel {

	
	private int score = 0;
    private int lives = 0;
    private int level = 1;

    public int getScore() { return score; }
    public int getLives() { return lives; }
    public int getLevel() { return level; }
    
    public void addScore(int delta) { 
    	this.score +=delta; 
    	}
    public void setLives(int count) { 
    	this.lives = count; 
    	}
    public void addLevel(int level) {
    	this.level += level;
    }
}
    

