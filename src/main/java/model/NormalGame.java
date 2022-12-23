package model;

import java.util.ArrayList;

public final class NormalGame extends Game {

  public NormalGame(String pathToData, ArrayList<Player> players, GameType type) {
    super(pathToData, players, type);
    init();
  }

  @Override
  public void init() {
    Stats stats = getStats();
    stats.setTime(60);
  }

}