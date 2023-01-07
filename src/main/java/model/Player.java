package model;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a player in the game.
 */
public final class Player {
  private String name;
  private int points;
  private Game currentGame;
  private Stats playerStats;
  private ArrayList<Word.CharStats> goodChars; // Les caractères utiles écrit dans currentGame
  private long nbKeysPressed = 0;
  private int lifes = 20;
  
  /**
   * Constructs a new `Player` with the given name and points.
   *
   * @param name the name of the player
   * @param points the points of the player
   */
  public Player(String name, int points) {
    this.name = name;
    this.points = points; // Ceci on va remplacer par "vies" après dans mode challenge
    this.goodChars = new ArrayList<>();
    //updatePlayerStats();
  }
  /**
   * Appends the given list of characters to the list of good characters for this player.
   *
   * @param chars the list of characters to append
   * @return true if the operation was successful, false otherwise
   */
  public boolean concatToGoodChars(List<Word.CharStats> chars) {
    // System.out.println(chars); // Pratique pour voir les caractères utiles validés
    // System.out.println(Stats.getDurationsBetween2Chars(chars)); // Calcul les durées entre chaque paires de caractères
    return goodChars.addAll(chars);
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
    this.playerStats = Stats.createStats(currentGame, this);
  }

  
  /** 
   * @return Stats
   */
  public Stats getPlayerStats() {
    return playerStats;
  }
  
  public void keyPressed() {
    nbKeysPressed++;
  }

  
  /** 
   * @return long
   */
  public long nbKeysPressed() {
    return nbKeysPressed;
  }


  
  /** 
   * @return int
   */
  public int getLifes() {
    return lifes;
  }

  
  /** 
   * @param number
   * @param add
   */
  public void updateLife(int number, boolean add) {
    if(add) {
      this.lifes += number;
    }else {
      this.lifes -= number;
    }
  }


  /** 
   * @return String
   */
  public String toString() {
    return String.format("Name: %s, Points: %d", this.getName(), this.getPoints());
  }
    
  
  /** 
   * @return ArrayList<CharStats>
   */
  public ArrayList<Word.CharStats> getGoodChars() {
    return new ArrayList<>(goodChars);
  }

  
  /** 
   * @return int
   */
  public int nbGoodChars() {
    return goodChars.size();
  }

  public void resetPlayerData() {
    this.setPoints(0);
    this.goodChars = new ArrayList<>();
    this.nbKeysPressed = 0;
    this.lifes = 20;
    this.updatePlayerStats();
    
  }

}