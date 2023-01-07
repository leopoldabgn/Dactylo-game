package model;

import java.util.ArrayList;
/**
 * Represents a game in the game application.
 * This is an abstract class that serves as a base for different types of games, such as
 * normal, challenge, and multiplayer.
 *
 * @see NormalGame
 * @see ChallengeGame
 * @see MultiplayerGame
 */
public abstract sealed class Game permits NormalGame, ChallengeGame, MultiplayerGame {
  private WordQueue wordQueue;
  private Word actualWord;
  private Infos infos;
  private ArrayList<Player> players;
  private Player actualPlayer;
  private GameType type;
  private static int gameCounter = 0;
  private final int gameId;
  private int level = 1;
  // private String pathToData;

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
   * Constructs a new game with the specified data file path, list of players, and type.
   *
   * @param pathToData the path to the data file for this game
   * @param players the list of players participating in this game
   * @param type the type of game (normal, challenge, or multiplayer)
   */
  public Game(ArrayList<Player> players, GameType type) {
    // this.pathToData = pathToData;
    if(type == GameType.CHALLENGE)
      wordQueue = WordQueue.challengeQueue();
    else
      wordQueue = WordQueue.normalQueue();
    deepCopy(players);
    actualWord = wordQueue.getQueue().peek();
    this.infos = Infos.empty();
    this.type = type;
    this.gameId = Game.gameCounter++;
    this.actualPlayer = players.get(0); 
    for(Player p : players) {
      p.setCurrentGame(this);
    }
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
   * @return int
   */
  public int getLevel() {
    return level;
  }

  
  /** 
   * @param level
   */
  public void setLevel(int level) {
    this.level = level;
  }

  public void updateLevel() {
    if(this.infos.getWordLevelRef() == 100)
      this.level += 1;
  }

  /** 
   * @return String
   */
  public String toString() {
    return String.format("\u001b[32mGame id:\u001b[0m %d - \u001b[34mGame Mode:\u001b[0m %s - \u001b[31mPlayers:\u001b[0m %s",this.getGameId(),  this.getType(), this.getPlayers());
  }

  
  /** 
   * @return Player
   */
  public Player getActualPlayer() {
    return actualPlayer;
  }

  
  /** 
   * @return Infos
   */
  public Infos getInfos() {
    return infos;
  }

}