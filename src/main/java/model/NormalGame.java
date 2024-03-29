package model;

import java.util.ArrayList;
/**
 * A subclass of Game representing a normal game mode.
 *
 * @see Game
 */
public final class NormalGame extends Game {
  /**
   * Constructs a new `NormalGame` with the given data file path and list of players.
   *
   * @param pathToData the path to the data file for this game
   * @param players the list of players participating in this game
  */
  public NormalGame(ArrayList<Player> players) {
    super(players, GameType.NORMAL);
    this.setWordQueue(WordQueue.normalQueue());
    setActualWord(getWordQueue().getQueue().peek());
    init();
    
  }
  /**
   * Initializes this game by setting the duration to 60 seconds.
   */
  @Override
  public void init() {
    Infos infos = getInfos();
    infos.setDuration(60);
  }

}