package model;

import java.util.ArrayList;

import utils.Utils;

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
   * @param pathToData
   * @param players
   * @param type
   */
  public Game(String pathToData, ArrayList<Player> players, GameType type) {
    // this.pathToData = pathToData;
    this.wordQueue = new WordQueue(pathToData);
    deepCopy(players);
    actualWord = wordQueue.getQueue().peek();
    this.infos = Infos.empty();
    this.type = type;
    this.gameId = Game.gameCounter++;
    this.actualPlayer = players.get(0); // Par défault c'est le premier joueur de la liste.
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


  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void updateLevel() {
    // Utils.log(""+this.infos.getWordLevelRef());
    if(this.infos.getWordLevelRef() == 5) {
      this.level += 1;
      // this.infos.setLevel(this.level);
      // Utils.log("Update level: "+this.level);
    }
  }

  // public void reset() {
  //   // [TODO]: reset player stats - wait for leo merge
  //   for (Player player : this.getPlayers()) {
  //     player.updatePlayerStats();
  //   }
  //   // [TODO]: reset game meta data
  //   this.wordQueue = new WordQueue(this.pathToData);
  //   this.infos = Infos.empty();
  // }

  /** 
   * @return String
   */
  public String toString() {
    return String.format("\u001b[32mGame id:\u001b[0m %d - \u001b[34mGame Mode:\u001b[0m %s - \u001b[31mPlayers:\u001b[0m %s",this.getGameId(),  this.getType(), this.getPlayers());
  }

  public Player getActualPlayer() {
    return actualPlayer;
  }

  public Infos getInfos() {
    return infos;
  }

}