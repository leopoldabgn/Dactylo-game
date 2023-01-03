package model;

import java.util.ArrayList;

public final class NormalGame extends Game {

  public NormalGame(ArrayList<Player> players) {
    super(players, GameType.NORMAL);
    init();
    
  }

  @Override
  public void init() {
    Infos infos = getInfos();
    infos.setDuration(60);
  }

}