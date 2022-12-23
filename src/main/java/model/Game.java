package model;

import java.util.ArrayList;

public abstract sealed class Game permits NormalGame, ChallengeGame, MultiplayerGame {
  private final WordQueue wordQueue;
  private Word actualWord;
  private Stats stats;
  private ArrayList<Player> players;
  private GameType type;
  private static int gameCounter = 0;
  private final int gameId;

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

  /**
   * @param pathToData
   * @param players
   * @param type
   */
  public Game(String pathToData, ArrayList<Player> players, GameType type) {
    this.wordQueue = new WordQueue(pathToData);
    deepCopy(players);
    actualWord = wordQueue.getQueue().peek();
    this.stats = Stats.empty();
    this.type = type;
    this.gameId = Game.gameCounter++;
  }
  
  
  /** 
   * @param ls
   */
  private void deepCopy(ArrayList<Player> ls) {
    this.players = new ArrayList<>();
    for (Player player : ls) {
        Player copy = new Player(player.getName(), player.getPoints());
        copy.setCurrentGame(this);
        this.players.add(copy);
    }
  }

  
  /** 
   * @return WordQueue
   */
  public WordQueue getWordQueue() {
    return wordQueue;
  }

  
  /** 
   * @return Word
   */
  public Word nextWord() {
    actualWord = wordQueue.getQueue().poll();
    return actualWord;
  }

  
  /** 
   * @return Word
   */
  public Word getActualWord() {
    return actualWord;
  }

  
  /** 
   * @return GameType
   */
  public GameType getType() {
    return type;
  }

  
  /**
   * @return ArrayList<Player>
   */
  public ArrayList<Player> getPlayers() {
    return players;
  }

  /**
   * @return gameId
   */
  public int getGameId() {
    return gameId;
  }

  /** 
   * @return String
   */
  public String toString() {
    return String.format("\u001b[32mGame id:\u001b[0m %d - \u001b[34mGame Mode:\u001b[0m %s - \u001b[31mPlayers:\u001b[0m %s",this.getGameId(),  this.getType(), this.getPlayers());
  }

  public Stats getStats() {
    return stats;
  }

}