package helloWorld;

public class HudModel {

	
	private int score = 0;
    private int lives = 0;

    public int getScore() { return score; }
    public int getLives() { return lives; }
    
    public void addScore(int delta) { 
    	this.score +=delta; 
    	}
    public void setLives(int count) { 
    	this.lives = count; 
    	}
}
    

