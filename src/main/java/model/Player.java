package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private String name;
  private int points;
  private Game currentGame;
  private ArrayList<Word.CharStats> goodChars; // Les caractères utiles écrit dans currentGame

  public Player(String name, int points) {
    this.name = name;
    this.points = points;
    this.goodChars = new ArrayList<>();
  }

  public boolean concatToGoodChars(List<Word.CharStats> chars) {
    System.out.println(chars);
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


  
  /** 
   * @return String
   */
  public String toString() {
    return String.format("Name: %s, Points: %d", this.getName(), this.getPoints());
  }
    
}