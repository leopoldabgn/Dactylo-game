package model;

import java.util.ArrayList;

import model.Game.GameType;

public class GameFactory {
  /**
   * @param type
   * @param data
   * @param players
   * @return Game
   */
  public static Game getGame(GameType type, String data, ArrayList<Player> players) {
    switch (type) {
      case MP:
        return new MultiplayerGame(data, players);
      case CHALLENGE:
        return new ChallengeGame(data, players);
      default:
        return new NormalGame(data, players);
    }
  }
}
