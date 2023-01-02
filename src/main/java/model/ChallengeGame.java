package model;

import java.util.ArrayList;

public final class ChallengeGame extends Game {

    public ChallengeGame(String pathToData, ArrayList<Player> players) {
        super(pathToData, players, GameType.CHALLENGE);
        init();
    }

    @Override
    public void init() {
        Infos infos = getInfos();
        infos.setLifes(super.getActualPlayer().getLifes());
        infos.setType(GameType.CHALLENGE);
    }

}