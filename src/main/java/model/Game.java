package model;

import java.util.ArrayList;

public abstract sealed class Game permits NormalGame, ChallengeGame, MultiplayerGame { // sealed
  private final WordQueue wordQueue;
  private Word actualWord;
  private ArrayList<Player> players;

  public enum GameType {
    NORMAL("Normal"), CHALLENGE("Challenge"), MP("Multiplayer");
    private String custom;
    private GameType(String str) {
      this.custom = str;
    }
    public String toString() {
      return this.custom;
    }
  }

  public abstract void init();

  public Game(String pathToData, ArrayList<Player> players) {
    this.wordQueue = new WordQueue(pathToData);
    deepCopy(players);
    actualWord = wordQueue.getQueue().peek();
  }
  
  private void deepCopy(ArrayList<Player> ls) {
    this.players = new ArrayList<>();
    for (Player player : ls) {
        this.players.add(new Player(player.getName(), player.getPoints()));
    }
  }

  public WordQueue getWordQueue() {
    return wordQueue;
  }

  public Word nextWord() {
    actualWord = wordQueue.getQueue().poll();
    return actualWord;
  }

  public Word getActualWord() {
    return actualWord;
  }

}