package model;

public abstract sealed class Game permits NormalMode, GameMode, MultiplayerMode { // sealed
  private WordQueue text;
  public abstract void init();

  public Game(String pathToData) {
    this.text = new WordQueue(pathToData);
  }
  
}