package model;

import java.util.ArrayList;

import model.Game.GameType;
/**
 * A factory class for creating games of different types.
 *
 * @see Game
 */
public final class GameFactory {
  /**
   * Returns a new game of the specified type, with the given data file path and list of players.
   *
   * @param type the type of game to create (normal, challenge, or multiplayer)
   * @param data the path to the data file for the game
   * @param players the list of players participating in the game
   * @return a new game of the specified type
   */
  public static Game getGame(GameType type, ArrayList<Player> players) {
    switch (type) {
      case MP:
        return new MultiplayerGame(players);
      case CHALLENGE:
        return new ChallengeGame(players);
      default:
        return new NormalGame(players);
    }
  }
}
