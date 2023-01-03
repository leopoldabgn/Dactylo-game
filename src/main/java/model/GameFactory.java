package model;

import java.util.ArrayList;

import model.Game.GameType;

public final class GameFactory {
  /**
   * @param type
   * @param data
   * @param players
   * @return Game
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
