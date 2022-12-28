package model;

public class Player {
  private String name;
  private int points;
  private Game currentGame;
  private Stats playerStats;
  
  public Player(String name, int points) {
    this.name = name;
    this.points = points;
    updatePlayerStats();
  }

  /** 
   * @return String
   */
  public String getName() {
    return name;
  }
  
  /** 
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /** 
   * @return int
   */
  public int getPoints() {
    return points;
  }
  
  /** 
   * @param points
   */
  public void setPoints(int points) {
    this.points = points;
  }


  /**
   * @return Game
   */
  public Game getCurrentGame() {
    return currentGame;
  }

  /**
   * @param currentGame
   */
  public void setCurrentGame(Game currentGame) {
    this.currentGame = currentGame;
  }

  public void updatePlayerStats() {
    this.playerStats = Stats.createStats(this);
  }

  public Stats getPlayerStats() {
    return playerStats;
  }
  
  /** 
   * @return String
   */
  public String toString() {
    return String.format("Name: %s, Points: %d", this.getName(), this.getPoints());
  }
    
}