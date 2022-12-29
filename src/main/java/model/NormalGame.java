package model;

import java.util.ArrayList;

public final class NormalGame extends Game {

  public NormalGame(String pathToData, ArrayList<Player> players) {
    super(pathToData, players, GameType.NORMAL);
    init();
  }

  @Override
  public void init() {
    Infos infos = getInfos();
    infos.setDuration(10);
  }

}